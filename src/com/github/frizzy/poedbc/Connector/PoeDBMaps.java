package com.github.frizzy.poedbc.Connector;

import com.github.frizzy.poedbc.Data.BossStats;
import com.github.frizzy.poedbc.Utilities.DocFinder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * PoeDBMaps provides an API to retrieve all information regarding maps from poe.db.
 * </p>
 *
 * @author Frizzy
 * @version 0.1-Settlers
 * @since 0.1-Settlers
 */
public class PoeDBMaps {

    /**
     * The logger for PoeDBMaps.
     */
    private static final Logger log = Logger.getLogger ( PoeDBMaps.class.getName ( ) );

    /**
     * Returned when certain data is unknown.
     */
    private static final String UNKNOWN = "Unknown";

    /**
     * The name of the map an instance of PoeDBMaps will retrieve
     * data for.
     */
    private String mapName;

    /**
     * The URL of the parsed map.
     */
    private String mapUrl;

    /**
     * The Document the map data is retrieved from.
     */
    private Document soupDoc;

    /**
     * Constructs a PoeDBMaps instance to parse the poe.db
     * webpage for the provided name.
     */
    public PoeDBMaps ( final String mapName ) {
        this.mapName = mapName;
        this.mapUrl = getURL ( );
    }

    /**
     * Retrieves the map URL from the URLResourceHandler.
     */
    private String getURL ( ) {
        return URLResourceHandler.getInstance ( ).getMapLinkFor ( mapName);
    }

    /**
     * <p>
     * Attempts to parse the poe.db web page for the provided mapName. All data retrievable
     * by the instance of PoeDBMaps will be in relation to the new mapName.
     * </p>
     * <p>
     * This allows you to reuse PoeDBMaps instances.
     * </p>
     */
    public boolean parseNew ( final String mapName ) {
        this.mapName = mapName;
        this.mapUrl = getMapUrl ();

        return parse ();
    }

    /**
     * <p>
     * Connects to the poe.db page of the provided map and parses it into memory.
     * </p>
     * <p>
     * Subsequent calls to the various PoeDBMaps methods will retrieve the data from
     * the parsed document.
     * </p>
     * <p>
     * If false returns, that mean an error occurred during parsing and that the document is not
     * available to retrieve data from.
     * </p>
     */
    public boolean parse ( ) {
        if ( !mapUrl.equals ( "Unavailable" ) ) {
            try {
                soupDoc = Jsoup.connect ( mapUrl ).get ( );
                return true;
            } catch ( IOException e ) {
                log.log ( Level.SEVERE, "JSoup parsing failed.", e );
            }
        } else {
            log.warning ( "URL provided was Unavailable. No link to process a document from." );
        }

        return false;
    }

    /**
     * Returns the name of the currently parsed map.
     */
    public final String getMapName ( ) {
        return mapName;
    }

    /**
     * Returns the URL of the currently parsed map.
     */
    public final String getMapUrl ( ) {
        return mapUrl;
    }

    /**
     * Retrieves the drop level of the map name passed to PoeDBMaps.
     */
    public final int getDropLevel ( ) {
        return DocFinder.getIntegerValue ( soupDoc ,"DropLevel" );
    }

    /**
     * Returns the monster level of the map.
     */
    public final int getMonsterLevel ( ) {
        return DocFinder.getIntegerValue ( soupDoc ,"Monster Level" );
    }

    /**
     * Returns the act the map is out of.
     */
    public final int getAct ( ) {
        return DocFinder.getIntegerValue ( soupDoc , "Act" );
    }

    /**
     * Returns the clearing ability rating.
     */
    public final int getClearingAbility ( ) {
        return DocFinder.getIntegerValue ( soupDoc , "Clearing Ability" );
    }

    /**
     * Returns the mob count rating.
     */
    public final int getMobCount ( ) {
        return DocFinder.getIntegerValue ( soupDoc , "Mob Count" );
    }

    /**
     * Returns the boss difficulty rating.
     */
    public final int getBossDifficulty ( ) {
        return DocFinder.getIntegerValue ( soupDoc , "Boss Difficulty" );
    }

    /**
     * <p>
     * Returns the vaal area of the map.
     * </p>
     * <p>
     * This can frequently be "Unknown".
     * </p>
     */
    public final String getVaalArea ( ) {
        return DocFinder.getStringValue ( soupDoc , "Vaal Area" );
    }

    /**
     * Returns the base type of the map.
     */
    public final String getBaseType ( ) {
        return DocFinder.getStringValue ( soupDoc , "BaseType" );

    }

    /**
     * <p>
     * Returns the class of the item.
     * </p>
     */
    public final String getItemClass ( ) {
        return DocFinder.getStringValue ( soupDoc ,"Class" );
    }

    /**
     * Returns the metadata type of the map.
     */
    public final String getType ( ) {
        return DocFinder.getStringValue ( soupDoc , "Type" );
    }

    /**
     * Returns the internal icon path.
     */
    public final String getIcon ( ) {
        return DocFinder.getStringValue ( soupDoc , "Icon" );
    }

    /**
     * Returns the tile set of the map.
     */
    public final String getTileSet ( ) {
        return DocFinder.getStringValue ( soupDoc ,"Tileset" );
    }

    /**
     * Returns the name of the boss that map boss is based off of.
     */
    public final String getBossBasedOn ( ) {
        return DocFinder.getStringValue ( soupDoc , "Boss Based On" );
    }

    /**
     * Returns boss notes.
     */
    public final String getBossNotes ( ) {
        return DocFinder.getStringValue ( soupDoc , "Boss notes" );
    }

    /**
     * Returns additional notes for the map.
     */
    public final String getAdditionalNotes ( ) {
        return DocFinder.getStringValue ( soupDoc , "Additional Notes" );
    }

    /**
     * <p>
     * Returns the reference link for the map.
     * </p>
     * <p>
     * Usually, this is a link the the poe.wiki page for the map.
     * </p>
     */
    public final String getReference ( ) {
        return DocFinder.getHrefValue ( soupDoc ,"Reference" );
    }

    /**
     * Returns the obstacles rating.
     */
    public final String getFewObstacles ( ) {
        return DocFinder.getStringValue ( soupDoc ,"Few Obstacles" );
    }

    /**
     * Returns the boss room rating.
     */
    public final String getBossNotInOwnRoom ( ) {
        return DocFinder.getStringValue ( soupDoc ,"Boss not in own room" );
    }

    /**
     * Returns the outdoors rating.
     */
    public final String getOutdoors ( ) {
        return DocFinder.getStringValue ( soupDoc , "Outdoors" );
    }

    /**
     * Returns the linearity rating.
     */
    public final String getLinear ( ) {
        return DocFinder.getStringValue ( soupDoc , "Linear" );
    }

    /**
     * Returns the loading image link, if there is one.
     */
    public final String getLoading ( ) {
        return DocFinder.getHrefValue ( soupDoc ,"Loading" );
    }

    /**
     * Returns the hideout.
     */
    public final String getHideout ( ) {
        return DocFinder.getStringValue ( soupDoc , "Hideout" );
    }

    /**
     * Returns the market link.
     */
    public final String getMarket ( ) {
        return DocFinder.getHrefValue ( soupDoc ,"Market" );
    }

    /**
     * Returns Internal map flags.
     */
    public Collection < String > getFlags ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Flags" ).split ( "," ) ).toList ( );
    }

    /**
     * Returns Internal map tags.
     */
    public Collection < String > getTags ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Tags" ).split ( "," ) ).toList ( );
    }

    /**
     * Returns additional cards the map can drop.
     */
    public Collection < String > getCardTags ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Card Tags" ).split ( "\n" ) ).toList ( );
    }

    /**
     * Returns the Divination Cards the map can drop.
     */
    public Collection < String > getDivinationCards ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Divination Card" ).split ( "\n" ) ).toList ( );
    }

    /**
     * Returns the bosses of the map.
     */
    public Collection < String > getBosses ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Boss" ).split ( "\n" ) ).toList ( );
    }

    /**
     * <p>
     * Returns the boss(es) stats for the map.
     * </p>
     * <p>
     * The performance for this method can probably be radically improved. I am still
     * learning JSoup and in all likely hood have plenty of opportunity in improving.
     * </p>
     */
    public final Collection < BossStats > getBossesStats ( ) {
        final Collection < BossStats > bossStats = new ArrayList <> ( );
        Elements tables = soupDoc.select ( "table" );

        for ( Element table : tables ) {
            try {
                /*
                 * Here we retrieve all <tbody> elements.
                 */
                Element tbody = table.selectFirst ( "tbody" );
                Elements trEles = tbody.select ( "tr" );

                /*
                 * Every <tr> element should contain the different boss stats/information.
                 */
                for ( Element trEle : trEles ) {
                    Elements tdEles = trEle.select ( "td" );

                    if ( tdEles.size () == 13 ) { //13 values are available at the bottom table regarding boss/enemy stats

                        /*
                         * Now that we are iterating the elements within the <tr> element, we can retrieve
                         * the information we need.
                         */
                        internalLoop:
                        //Labeled internal loop because we want to break this loop specifically once we have parsed the boss stats.
                        {
                            for ( int i = 0; i < tdEles.size ( ); i++ ) {
                                Element tdEle = tdEles.get ( i );

                                String toStr = tdEle.toString ( );
                                if ( toStr.contains ( "MonsterVarieties" ) ) {

                                    /*
                                     * TODO: At some point I need to remove the replaceAll calls and try something else for
                                     * better performance.
                                     */
                                    bossStats.add ( new BossStats (
                                            Integer.parseInt ( tdEles.get ( i - 1 ).text ( ) ) ,
                                            tdEles.get ( i ).text ( ) ,
                                            Integer.parseInt ( tdEles.get ( i + 1 ).text ( ).replaceAll ( "-?[^\\d]" , "" ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 2 ).text ( ).replaceAll ( "-?[^\\d]" , "" ) ) ,
                                            Double.parseDouble ( tdEles.get ( i + 3 ).text ( ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 4 ).text ( ).replaceAll ( "-?[^\\d]" , "" ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 5 ).text ( ).replaceAll ( "-?[^\\d]" , "" ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 6 ).text ( ).replaceAll ( "-?[^\\d]" , "" ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 7 ).text ( ).replaceAll ( "-?[^\\d]" , "" ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 8 ).text ( ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 9 ).text ( ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 10 ).text ( ) ) ,
                                            Integer.parseInt ( tdEles.get ( i + 11 ).text ( ) ) ) );
                                    break internalLoop;
                                }
                            }
                        }
                    }
                }
            } catch ( IndexOutOfBoundsException e ) {
                log.log ( Level.SEVERE , "An exception occurred retrieving boss stats." , e );
            }
        }



        return bossStats;
    }

    /**
     *
     * @return
     */
    public String getMods ( ) {
        return DocFinder.getStringValue ( soupDoc ,"Mods" );
    }

    /**
     * @return
     */
    public Collection < String > getElderBosses ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Elder Map Boss" ).split ( "\n" ) ).toList ( );
    }

    /**
     * @return
     */
    public Collection < String > getLinkedMaps ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Atlas Linked" ).split ( "\n" ) ).toList ( );
    }

    /**
     * @return
     */
    public Collection < String > getUpgradedFrom ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Upgraded From" ).split ( "\n" ) ).toList ( );
    }

    /**
     * @return
     */
    public Collection < String > getTopologies ( ) {
        return Arrays.stream ( DocFinder.getStringValue ( soupDoc ,"Topologies" ).split ( "\n" ) ).toList ( );
    }

    /**
     * <p>
     * Returns the href value for loading or reference. No promises this will work elsewhere.
     * </p>
     */
    private String getHrefValue ( String title ) {
        if ( soupDoc == null )
            return "null";
        Elements trEles = soupDoc.select ( "tr" );

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

        return UNKNOWN;
    }

    /**
     *
     */
    private String getStringValue ( String title ) {
        if ( soupDoc == null )
            return "Null";

        Elements trEles = soupDoc.select ( "tr" );

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

        return UNKNOWN;
    }

    private int getIntegerValue ( String title ) {
        if ( soupDoc == null)
            return -1;

        Elements tdEles = soupDoc.select ( "tr" );

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
}
