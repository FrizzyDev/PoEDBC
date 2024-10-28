package com.github.frizzy.poedbc.Connector;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLResourceHandler {

    private static final Logger log = Logger.getLogger ( URLResourceHandler.class.getName () );

    /**
     *
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

        return "Unavailable";
    }
}
