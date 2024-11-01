package com.github.frizzy.poedbc.Connector;

import com.github.frizzy.poedbc.Utilities.DocUtils;
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
     *
     */
    private static final Logger log = Logger.getLogger ( PoeDBCurrency.class.getName () );

    /**
     *
     */
    private String currencyName;

    /**
     *
     */
    private String currencyUrl;

    /**
     *
     */
    private Document doc;

    /**
     *
     * @param currencyName
     */
    public PoeDBCurrency ( String currencyName ) {
        this.currencyName = currencyName;
        this.currencyUrl = buildUrl ();
    }

    /**
     *
     */
    private String buildUrl ( ) {
        return URLResourceHandler.getInstance ( ).getCurrencyLinkFor ( currencyName );
    }

    /**
     * Pares the poe.db web page for the supplied currency name.
     */
    public boolean parse ( ) {
        try {
            doc = Jsoup.connect ( currencyUrl ).get ();
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

    public String getCost ( ) {
        return DocUtils.getStringValue ( doc, "Cost" );
    }

    public String getDropLevel ( ) {
        return DocUtils.getStringValue ( doc, "DropLevel" );
    }

    public String getBaseType ( ) {
        return DocUtils.getStringValue ( doc, "BaseType" );
    }

    public String getItemClass ( ) {
        return DocUtils.getStringValue ( doc, "Class" );
    }

    public String getType ( ) {
        return DocUtils.getStringValue ( doc, "Type" );
    }

    public String getSound ( ) {
        return DocUtils.getStringValue ( doc, "Sound" );
    }

    public String getIcon ( ) {
        return DocUtils.getStringValue ( doc, "Icon" );
    }

    public String getMTXTabStacks ( ) {
        return DocUtils.getStringValue ( doc, "MTX Tab Stacks" );
    }

    public String getReference ( ) {
        return DocUtils.getHrefValue ( doc, "Reference" );
    }

    public Collection < String > getFlags ( ) {
        return Arrays.stream ( DocUtils.getStringValue ( doc,"Flags" ).split ( "," ) ).toList ( );
    }

    public Collection < String > getTags ( ) {
        return Arrays.stream ( DocUtils.getStringValue ( doc, "Tags" ).split ( "," ) ).toList ( );
    }
}
