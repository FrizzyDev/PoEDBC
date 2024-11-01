package com.github.frizzy.poedbc.Utilities;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DocUtils {

    private static final Logger log = Logger.getLogger ( DocUtils.class.getName ( ) );

    private DocUtils ( ) {

    }

    /**
     *
     */
    public static String getStringValue ( Document doc , String title ) {
        if ( doc == null )
            return "Null";

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
        }

        return "Unknown";
    }

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
     * Returns the href value for loading or reference. No promises this will work elsewhere.
     * </p>
     */
    public static String getHrefValue ( Document doc, String title ) {
        if ( doc == null )
            return "null";
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
        }

        return "Unknown";
    }
}
