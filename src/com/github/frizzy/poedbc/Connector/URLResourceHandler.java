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
     *
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
     *
     * @return
     */
    public static URLResourceHandler getInstance ( ) {
        if ( handler == null ) {
            handler = new URLResourceHandler ();
        }

        return handler;
    }

    /**
     *
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
     * Removes any special characters contained in the mapName and the word "map",
     * then converts it to lower case.
     */
    private String normalizeMapName ( final String mapName ) {
        String lower = mapName.replaceAll ( "[^\\w\\s]","" ).toLowerCase ();

        if ( lower.contains ( "map" ) ) {
            lower = lower.replace ( "map", "" );
        }

        return lower;
    }
}
