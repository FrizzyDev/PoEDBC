package com.github.frizzy.poedbc.Connector;

import com.github.frizzy.poedbc.Utilities.DocFinder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Allows a user to connect to poe.db and retrieve the information on any unique item.
 *
 * @author Frizzy
 * @version 0.1-Settlers
 * @since 0.1-Settlers
 */
public class PoeDBUniques {

    private static final Logger log = Logger.getLogger ( PoeDBUniques.class.getName () ) ;

    /**
     *
     */
    private String uniqueName;

    /**
     *
     */
    private String uniqueUrl;

    /**
     *
     */
    private Document doc;

    /**
     * Creates a PoeDBUniques instance to parse a web page for the provided
     * unique item name.
     */
    public PoeDBUniques ( String uniqueName ) {
        this.uniqueName = uniqueName;
        this.uniqueUrl = buildUrl ();
    }

    /**
     * Calls URLResourceHandler to build a URL for the unique item name.
     */
    private String buildUrl ( ) {
        return URLResourceHandler.getInstance ( ).getUniqueLinkFor ( uniqueName );
    }

    /**
     * Parses the poe.db web page. Returns true if successful, false otherwise.
     * A failure likely means the item name did not yield a valid URL.
     */
    public boolean parse ( ) {
        try {
            doc = Jsoup.connect ( uniqueUrl ).get ();

            return true;
        } catch ( IOException  e) {
            log.log ( Level.SEVERE, e.getMessage (), e );
        }

        return false;
    }

    /**
     * Parses the poe.db web page of the new unique item name. Subsequent get functions will
     * return the correct data.
     */
    public boolean parseNew ( String newUniqueName ) {
        this.uniqueName = newUniqueName;
        this.uniqueUrl = buildUrl ();

        return parse ();
    }

    /**
     * Returns the unique item name.
     */
    public String getUniqueName ( ) {
        return uniqueName;
    }

    /**
     * Returns the poe.db url for the item.
     */
    public String getUniqueUrl ( ) {
        return uniqueUrl;
    }

    /**
     *
     */
    public String getIcon ( ) {
        return DocFinder.getStringValue ( doc, "Icon" );
    }

    /**
     *
     */
    public String getBaseType ( ) {
        return DocFinder.getStringValue ( doc, "BaseType" );
    }

    /**
     *
     */
    public String getReference ( ) {
        return DocFinder.getHrefValue ( doc, "Reference" );
    }

    /**
     *
     */
    public Collection < String > getAcronym ( ) {
        return Arrays.stream( DocFinder.getStringValue ( doc, "Acronym" ).split ( "," ) ).toList ();
    }

    /**
     *
     */
    public String getMarket ( ) {
        return DocFinder.getHrefValue ( doc, "The Market" );
    }

    public String getItemIconLink ( ) {
        if ( doc == null )
            return "Unavailable";

        Elements trElements = doc.select ( "tr" );

        try {
            for ( Element trElement : trElements ) {
                Elements tdElements = trElement.select ( "td" );

                for ( Element tdElement : tdElements ) {
                    String tdStr = tdElement.html ();
                    if ( tdStr.contains ( "src=\"https://web.poecdn.com/" ) ) {
                        int index = tdStr.indexOf ( "src=" ) + 5;
                        int lastIndex = tdStr.lastIndexOf ( "\"" );
                        return tdStr.substring ( index , lastIndex );
                    }
                }
            }
        } catch ( IndexOutOfBoundsException e ) {
            log.log ( Level.SEVERE, "Exception occurred searching for item icon link.", e );
        }

        return "Unavailable";
    }

    /**
     * Retrieves the JSON in the poe.db web page of unique items. The retrieved JSON
     * can be parsed into a JsonObject of a JSON library of your choice and the data can be utilized.
     *
     */
    public String getUniqueJson ( ) {
        Elements div = doc.getElementsByClass ( "card-body" );

        for ( Element d : div ) {
            String str = d.toString ();
            if ( str.contains ( "{" ) ) {
                int index = str.indexOf ( "{" );
                int lastIndex = str.lastIndexOf ( "}" );
                return str.substring ( index, lastIndex ).replace ( "<br>", "" );
            }
        }

        return "Unavailable";
    }
}
