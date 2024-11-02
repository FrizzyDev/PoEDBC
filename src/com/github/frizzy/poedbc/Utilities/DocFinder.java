package com.github.frizzy.poedbc.Utilities;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A utility class that locates and extracts specific data from a JSoup document. Specifically, documents
 * parsed from a poe.db web page.
 *
 * @author Frizzy
 * @version 0.1-Settlers
 * @since 0.1-Settlers
 */
public class DocFinder {

    private static final Logger log = Logger.getLogger ( DocFinder.class.getName ( ) );

    private DocFinder ( ) {

    }

    /**
     * <p>
     * Returns a value related to a property class.
     * </p>
     * <p>
     * This is currently used for Stack Size and Essence tier. It may or may not work
     * for other data.
     * </p>
     */
    public static String getProperty ( Document doc, String title ) {
        Elements els = doc.getElementsByClass ( "property" );

        for ( Element e : els ) {
            String text = e.text ();

            if ( text.startsWith ( title ) ) {
                int index = text.indexOf ( ":" ) + 1;
                return text.substring ( index );
            }
        }

        return "Unavailable";
    }

    /**
     * <p>
     * Retrieves the modifiers for a fossil item.
     * </p>
     * <p>
     * The returned list will be empty if the document does not have the data or an
     * exception as thrown.
     * </p>
     *
     */
    public static Collection < String > getFossilModifiers ( Document doc ) {
        Collection < String > modifiers = new ArrayList<> ();
        Elements tables = doc.select ( "table" );

        firstLoop : {
            for ( Element table : tables ) {
                try {
                    Element tableBody = table.selectFirst ( "tbody" );
                    Elements trElements = tableBody.select ( "tr" );

                    for ( Element trElement : trElements ) {
                        Elements tdElement = trElement.select ( "td" );
                        Elements ulElements = tdElement.select ( "ul" );

                        if ( ulElements.size () == 1 ) {
                            Elements liElements = ulElements.getFirst ().select ( "li" );

                            for ( Element liElement : liElements ) {
                                modifiers.add ( liElement.text () );
                            }

                            break firstLoop;
                        }
                    }
                } catch ( Exception e ) {
                    log.log ( Level.SEVERE, e.getMessage (), e );
                }
            }
        }

        return modifiers;
    }

    /**
     * <p>
     * Retrieves modifiers for an Essence currency item.
     * </p>
     * <p>
     * The returned Collection can be empty if no modifiers were found, or an exception occurred
     * parsing the document.
     * </p>
     */
    public static Collection < String > getEssenceModifiers ( Document doc, String affix ) {
        final Collection < String > affixes = new ArrayList <> (  );
        Elements tables = doc.select ( "table" );

        for ( Element table : tables ) {
            try {
                Element tableBody = table.selectFirst ( "tbody" );
                Elements trElements = tableBody.select ( "tr" );

                for ( Element trElement : trElements ) {
                    Elements tdElements = trElement.select ( "td" );

                    indexLoop : {
                        for ( int i = 0; i < tdElements.size (); i++ ) {
                            Element tdElement = tdElements.get ( i );

                            if ( tdElement.text ().equals ( affix ) ) {
                                affixes.add ( tdElements.get ( i + 1 ).text () );
                                break indexLoop;
                            }
                        }
                    }
                }

            } catch ( Exception e ) {
                log.log ( Level.SEVERE, e.getMessage (), e );
            }
        }

        return affixes;
    }

    /**
     * <p>
     * Returns a String value related to the title provided. The title can be the name
     * of a data point you want to retrieve, such as "BaseType" or "Icon".
     * </p>
     * <p>
     * If the document provided is null, or the data was not found this will return "Unavailable".
     * If an exception occurs, "Unknown" will be returned.
     * </p>
     */
    public static String getStringValue ( Document doc , String title ) {
        if ( doc == null )
            return "Unavailable";

        Elements trEles = doc.select ( "tr" );

        try {
            for ( Element trElement : trEles ) {
                Elements tdEles = trElement.select ( "td" );

                for ( int i = 0; i < tdEles.size ( ); i++ ) {
                    Element tdElement = tdEles.get ( i );

                    if ( tdElement.text ( ).equals ( title ) ) {
                        Element value = tdEles.get ( i + 1 );
                        return value.wholeText ( );
                    }
                }
            }
        } catch ( IndexOutOfBoundsException e ) {
            log.log ( Level.SEVERE , "Exception occurred searching for title: " + title , e );
            return "Unknown";
        }

        return "Unavailable";
    }

    /**
     * <p>
     * Retrieves an integer value from the provided document, related to the title. This
     * could be something such as "DropLevel" or "Act.
     * </p>
     * <p>
     * If the document is null, a value could not be found, or an exception occurred, -1 is returned.
     * </p>
     */
    public static int getIntegerValue ( Document doc, String title ) {
        if ( doc == null)
            return -1;

        Elements tdEles = doc.select ( "tr" );

        try {
            for ( Element ele : tdEles ) {
                Elements es = ele.select ( "td" );

                for ( int i = 0; i < es.size ( ); i++ ) {
                    Element subEle = es.get ( i );

                    if ( subEle.toString ( ).contains ( title ) ) {
                        Element value = es.get ( i + 1 );
                        return Integer.parseInt ( value.text ( ) );
                    }
                }
            }
        } catch ( IndexOutOfBoundsException e ) {
            log.log ( Level.SEVERE , "Exception occurred searching for title: " + title , e );
        }

        return -1;
    }

    /**
     * <p>
     * Returns the href value for a few different document values.
     * </p>
     * <p>
     * This may not work on other values. For now, I recommend only use it for
     * The Market, Loading, or Reference values.
     * </p>
     *
     */
    public static String getHrefValue ( Document doc, String title ) {
        if ( doc == null )
            return "Unavailable";
        Elements trEles = doc.select ( "tr" );

        try {
            for ( Element trElement : trEles ) {
                Elements tdEles = trElement.select ( "td" );

                for ( int i = 0; i < tdEles.size ( ); i++ ) {
                    Element tdElement = tdEles.get ( i );

                    if ( tdElement.text ( ).equals ( title ) ) {
                        Element value = tdEles.get ( i + 1 );
                        Element aTag = value.getElementsByTag ( "a" ).get ( 0 );
                        return aTag.attr ( "abs:href" );
                    }
                }
            }
        } catch ( IndexOutOfBoundsException e ) {
            log.log ( Level.SEVERE , "Exception occurred searching for title: Loading" , e );
            return "Unknown";
        }

        return "Unavailable";
    }
}
