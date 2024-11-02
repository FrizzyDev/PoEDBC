package com.github.frizzy.poedbc.Connector;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 *  URLResourceHandler provides the ability to retrieve URLs for maps, items, and other
 *  information on the poe.db website.
 * </p>
 *
 * @author Frizzy
 * @version 0.1-Settlers
 * @since 0.1-Settlers
 */
public class URLResourceHandler {

    /**
     * The logger for URLResourceHandler.
     */
    private static final Logger log = Logger.getLogger ( URLResourceHandler.class.getName () );

    /**
     * The singleton instance of URLResourceHandler.
     */
    private static URLResourceHandler handler;

    /**
     * The base poe.db URL.
     */
    private static final String POEDB_URL = "https://poedb.tw/us/";

    /**
     * The file containing URLs to map data on poe.db.
     */
    private File mapsLinksFile;

    /**
     *
     */
    private URLResourceHandler ( ) {
        try {
            mapsLinksFile = new File ( URLResourceHandler.class.getResource ( "/com/github/frizzy/poedbc/Resource/maps.links" ).getFile () );
        } catch ( NullPointerException e ) {
            log.log ( Level.SEVERE, e.getMessage (), e );
        }
    }

    /**
     * Returns the singleton instance of URLResourceHandler.
     */
    public static URLResourceHandler getInstance ( ) {
        if ( handler == null ) {
            handler = new URLResourceHandler ();
        }

        return handler;
    }

    /**
     * <p>
     * Attempts to normalize the currency name into a valid link structure.
     * </p>
     * <p>
     * It is not promised the returned link will be valid. You should add
     * additional validation on your end before attempting to parse the page
     * with JSoup.
     * </p>
     */
    public String getCurrencyLinkFor ( String currencyName ) {
        String normalized = normalizeName ( currencyName );
        return POEDB_URL + normalized;
    }

    /**
     * <p>
     * Retrieves a url for a map from the maps.links file.
     * </p>
     * <p>
     * If the provided map name did not match one in the links file, "Unavailable"
     * will return.
     * </p>
     */
    public String getMapLinkFor ( String mapName ) {
        if ( mapsLinksFile != null ) {
            String lower = mapName.toLowerCase ();

            try ( BufferedReader reader = new BufferedReader ( new FileReader ( mapsLinksFile ) ) ) {

                String line;
                while ( ( line = reader.readLine () ) != null ) {
                    if ( line.toLowerCase ().startsWith ( lower )  ) {
                        return line.substring ( line.indexOf ( "=" ) + 2 );
                    }
                }
            } catch ( IOException e ) {
                log.log ( Level.SEVERE, e.getMessage (), e );
            }
        }

        log.log ( Level.FINE, "Map: {0} was not found. Make sure the map is a valid map name.", mapName  );
        return "Unavailable";
    }

    /**
     * <p>
     * Returns a poe.db link based off of the name of the unique item returned. Note:
     * This does not necessarily mean the returned URL is valid. Verification steps should be
     * taken to ensure your users are not entering incorrect names or the returned link is
     * valid.
     * </p>
     */
    public String getUniqueLinkFor ( String uniqueName ) {
        String normalized = normalizeName ( uniqueName );

        return POEDB_URL + normalized;
    }

    /**
     * <p>
     * Removes apostrophes and replaces spaces with underscores to help build a valid link.
     * </p>
     */
    private String normalizeName ( final String uniqueName ) {
        String removedApost = uniqueName.replace ( "'", "" );

        return removedApost.replace ( " ", "_" );
    }
}
