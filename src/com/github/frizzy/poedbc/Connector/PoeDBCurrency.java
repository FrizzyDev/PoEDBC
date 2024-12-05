package com.github.frizzy.poedbc.Connector;

import com.github.frizzy.poedbc.Utilities.DocFinder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Retrieves data about a currency item from poe.db.
 * Currently, this does not parse the modifiers of certain currency items such as Essences. This is planned for the future.
 *
 * @author Frizzy
 * @version 0.1-Settlers
 * @since 0.1-Settlers
 */
public class PoeDBCurrency {

    /**
     * Logger for PoeDBCurrency.
     */
    private static final Logger log = Logger.getLogger ( PoeDBCurrency.class.getName () );

    /**
     * The name of the currency data is being retrieved for.
     */
    private String currencyName;

    /**
     * The poe.db url for the currency item.
     */
    private String currencyUrl;

    /**
     * The Jsoup document of the parse poe.db page
     * for the currency item.
     */
    private Document soupDoc;

    /**
     * Creates a PoeDBCurrency instance to parse the poe.db page
     * for the provided currency item.
     */
    public PoeDBCurrency ( String currencyName ) {
        this.currencyName = currencyName;
        this.currencyUrl = buildUrl ();
    }

    /**
     * Retrieves the URL for the currency item.
     */
    private String buildUrl ( ) {
        return URLResourceHandler.getInstance ( ).getCurrencyLinkFor ( currencyName );
    }

    /**
     * Pares the poe.db web page for the supplied currency name.
     */
    public boolean parse ( ) {
        try {
            soupDoc = Jsoup.connect ( currencyUrl ).get ();
            return true;
        } catch ( IOException e ) {
            log.log ( Level.SEVERE, e.getMessage (), e );
        }

        return false;
    }

    /**
     * Parses the poe.db web page for the new currency name.
     */
    public boolean parseNew ( String currencyName ) {
        this.currencyName = currencyName;
        this.currencyUrl = buildUrl ();

        return parse ( );
    }

    public String getCurrencyName ( ) {
        return currencyName;
    }

    public String getCurrencyUrl ( ) {
        return currencyUrl;
    }

    public String getCost ( ) {
        return DocFinder.getStringValue ( soupDoc , "Cost" );
    }

    public String getDropLevel ( ) {
        return DocFinder.getStringValue ( soupDoc , "DropLevel" );
    }

    public String getBaseType ( ) {
        return DocFinder.getStringValue ( soupDoc , "BaseType" );
    }

    public String getItemClass ( ) {
        return DocFinder.getStringValue ( soupDoc , "Class" );
    }

    public String getType ( ) {
        return DocFinder.getStringValue ( soupDoc , "Type" );
    }

    public String getSound ( ) {
        return DocFinder.getStringValue ( soupDoc , "Sound" );
    }

    public String getIcon ( ) {
        return DocFinder.getStringValue ( soupDoc , "Icon" );
    }

    public String getMTXTabStacks ( ) {
        return DocFinder.getStringValue ( soupDoc , "MTX Tab Stacks" );
    }

    public String getReference ( ) {
        return DocFinder.getHrefValue ( soupDoc , "Reference" );
    }

    public String getStackSize ( ) {
        return DocFinder.getProperty ( soupDoc , "Stack Size:" );
    }

    public String getEssenceTier ( ) {
        return DocFinder.getProperty ( soupDoc , "Essence Tier:" );
    }

    public Collection < String > getEssencePrefixes ( ) {
        return DocFinder.getEssenceModifiers ( soupDoc , "Prefix" );
    }

    public Collection < String > getEssenceSuffixes ( ) {
        return DocFinder.getEssenceModifiers ( soupDoc , "Suffix" );
    }

    public Collection < String > getFossilModifiers ( ) {
        return DocFinder.getFossilModifiers ( soupDoc );
    }

    public Collection < String > getFlags ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Flags" ).split ( "," ) ).toList ( );
    }

    public Collection < String > getTags ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc , "Tags" ).split ( "," ) ).toList ( );
    }
}
