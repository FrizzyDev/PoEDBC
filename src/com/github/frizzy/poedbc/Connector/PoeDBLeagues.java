package com.github.frizzy.poedbc.Connector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Frizzy
 * @version 0.1-Settlers
 * @since 0.1-Settlers
 */
public class PoeDBLeagues {

    /**
     *
     */
    private static final Logger log = Logger.getLogger ( PoeDBLeagues.class.getName ());

    /**
     * The poedb link we retrieve the league information from.
     */
    private static final String LEAGUES_URL = "https://poedb.tw/us/League#LeaguesList";

    /**
     * The JSoup HTML document.
     */
    private Document soupDoc;


    /**
     * <p>
     * Pares the poe.db web page for the supplied currency name.
     * </p>
     * <p>
     * Returns true if the parsing was successful, false otherwise. Generally a false
     * return means an error occurred.
     * </p>
     *
     */
    public boolean parse ( ) {
        try {
            soupDoc = Jsoup.connect ( LEAGUES_URL ).get ();
            return true;
        } catch ( IOException e ) {
            log.log ( Level.SEVERE, e.getMessage (), e );
        }

        return false;
    }

    /**
     * Returns a list of all leagues that PoE has had.
     */
    public Collection < String > getLeagues ( ) {
        Collection < String > leagues = new ArrayList <> (  );
        Element tbody = soupDoc.select ( "tbody" ).get ( 1 );
        Elements trEles = tbody.select ( "tr" );

        for ( Element trEle : trEles ) {
            Elements tdEles = trEle.select ( "td" );

            //Quick check to make sure the first league in the list is not a placeholder version for an unannounced league
            if ( !tdEles.get ( 0 ).text ().matches(".*\\d+.*") ) {
                leagues.add ( tdEles.get ( 1 ).text () );
            }
        }

        return leagues;
    }

    /**
     * Returns a map separating end game bosses introduced by specific leagues.
     */
    public Map < String, Collection < String > > getLeagueBosses ( ) {
        Map < String, Collection < String > > leagueBosses = new LinkedHashMap <> (  );
        Element tBody = soupDoc.select ( "tbody" ).get ( 2 );
        Elements trEles = tBody.select ( "tr" );

        for ( Element trEle : trEles ) {
            Elements tdEles = trEle.select ( "td" );
            List<String> list = Arrays.stream( tdEles.get ( 2 ).text ().split ( "·" ) ).toList ();
            List < String > trimmed = new ArrayList <> (  );

            for ( String s : list ) {
                trimmed.add ( s.trim () );
            }

            leagueBosses.put ( tdEles.get ( 1 ).text (), trimmed );
        }

        return leagueBosses;
    }
}
