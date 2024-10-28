package com.github.frizzy.poedbc.Connector;

import com.github.frizzy.poedbc.Data.BossStats;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
     * Returned when certain data is unavailable.
     */
    private static final String UNAVAILABLE = "Unavailable";

    /**
     * The base poe.db url.
     */
    private static final String POEDB_URL = "https://poedb.tw/us/";

    /**
     * The name of the map an instance of PoeDBMaps will retrieve
     * data for.
     */
    private final String mapName;

    /**
     *
     */
    private final String mapUrl;

    /**
     * The Document the map data is retrieved from.
     */
    private Document doc;

    /**
     * Constructs a PoeDBMaps instance to parse the poe.db
     * webpage for the provided name.
     */
    public PoeDBMaps ( final String mapName ) {
        this.mapName = mapName;
        this.mapUrl = buildUrl ( );
    }

    /**
     * Builds the URL.
     * This is temporary.
     * TODO: Pull the URL from a static constant map based off of the map name provided.
     */
    private String buildUrl ( ) {
        final String linkMapName = mapName.replace ( " " , "_" ) + "_Map#";
        final String linkMapName2 = mapName.replace ( " " , "" ) + "MapMapWorlds" + mapName.replace ( " " , "" );
        return POEDB_URL + linkMapName + linkMapName2;
    }

    /**
     * <p>
     * Connects to the poe.db page of the provided map and parses it into memory.
     * </p>
     * <p>
     * Subsequent calls to the various PoeDBMaps methods will retrieve the data from
     * the parsed document.
     * </p>
     */
    public void parse ( ) {
        try {
            doc = Jsoup.connect ( mapUrl ).get ( );
        } catch ( IOException e ) {
            log.log ( Level.SEVERE, "JSoup parsing failed.", e );
        }
    }

    /**
     * <p>
     * Retrieves the item image from the poe.db webpage.
     * </p>
     * <p>If this fails, null is returned.</p>
     */
    public final BufferedImage getItemImage ( ) {

        return null;
    }

    /**
     * Retrieves the drop level of the map name passed to PoeDBMaps.
     */
    public final int getDropLevel ( ) {
        return getIntegerValue ( "DropLevel" );
    }

    /**
     * Returns the monster level of the map.
     */
    public final int getMonsterLevel ( ) {
        return getIntegerValue ( "Monster Level" );
    }

    /**
     * Returns the act the map is out of.
     */
    public final int getAct ( ) {
        return getIntegerValue ( "Act" );
    }

    /**
     * Returns the clearing ability rating.
     */
    public final int getClearingAbility ( ) {
        return getIntegerValue ( "Clearing Ability" );
    }

    /**
     * Returns the mob count rating.
     */
    public final int getMobCount ( ) {
        return getIntegerValue ( "Mob Count" );
    }

    /**
     * Returns the boss difficulty rating.
     */
    public final int getBossDifficulty ( ) {
        return getIntegerValue ( "Boss Difficulty" );
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
        return getStringValue ( "Vaal Area" );
    }

    /**
     * Returns the base type of the map.
     */
    public final String getBaseType ( ) {
        return getStringValue ( "BaseType" );

    }

    /**
     * <p>
     * Returns the class of the item.
     * </p>
     */
    public final String getItemClass ( ) {
        return getStringValue ( "Class" );
    }

    /**
     * Returns the metadata type of the map.
     */
    public final String getType ( ) {
        return getStringValue ( "Type" );
    }

    /**
     * Returns the internal icon path.
     */
    public final String getIcon ( ) {
        return getStringValue ( "Icon" );
    }

    /**
     * Returns the tile set of the map.
     */
    public final String getTileSet ( ) {
        return getStringValue ( "Tileset" );
    }

    /**
     * Returns the name of the boss that map boss is based off of.
     */
    public final String getBossBasedOn ( ) {
        return getStringValue ( "Boss Based On" );
    }

    /**
     * Returns boss notes.
     */
    public final String getBossNotes ( ) {
        return getStringValue ( "Boss notes" );
    }

    /**
     * Returns additional notes for the map.
     */
    public final String getAdditionalNotes ( ) {
        return getStringValue ( "Additional Notes" );
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
        return getHrefValue ( "Reference" );
    }

    /**
     * Returns the obstacles rating.
     */
    public final String getFewObstacles ( ) {
        return getStringValue ( "Few Obstacles" );
    }

    /**
     * Returns the boss room rating.
     */
    public final String getBossNotInOwnRoom ( ) {
        return getStringValue ( "Boss not in own room" );
    }

    /**
     * Returns the outdoors rating.
     */
    public final String getOutdoors ( ) {
        return getStringValue ( "Outdoors" );
    }

    /**
     * Returns the linearity rating.
     */
    public final String getLinear ( ) {
        return getStringValue ( "Linear" );
    }

    /**
     * Returns the loading image link, if there is one.
     */
    public final String getLoading ( ) {
        return getHrefValue ( "Loading" );
    }

    /**
     * @return
     */
    public Collection < String > getPantheon ( ) {

        return Collections.emptyList ( );
    }

    /**
     * Returns Internal map flags.
     */
    public Collection < String > getFlags ( ) {
        return Arrays.stream ( getStringValue ( "Flags" ).split ( "," ) ).toList ( );
    }

    /**
     * Returns Internal map tags.
     */
    public Collection < String > getTags ( ) {
        return Arrays.stream ( getStringValue ( "Tags" ).split ( "," ) ).toList ( );
    }

    /**
     * Returns additional cards the map can drop.
     */
    public Collection < String > getCardTags ( ) {
        return Arrays.stream ( getStringValue ( "Card Tags" ).split ( "\n" ) ).toList ( );
    }

    /**
     * Returns the Divination Cards the map can drop.
     */
    public Collection < String > getDivinationCards ( ) {
        return Arrays.stream ( getStringValue ( "Divination Card" ).split ( "\n" ) ).toList ( );
    }

    /**
     * Returns the bosses of the map.
     */
    public Collection < String > getBosses ( ) {
        return Arrays.stream ( getStringValue ( "Boss" ).split ( "\n" ) ).toList ( );
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

        try {
            /*
             * From my testing, I have determined the html of the poedb map pages have two <table> tags and the
             * second one contains the information needed for this method. We can assume the second table is the 2nd
             * index.
             */
            Element tableEles = doc.select ( "table" ).get ( 1 ); //Second table is needed

            /*
             * Here we retrieve all <tbody> elements.
             */
            Elements tbodyEles = tableEles.select ( "tbody" );

            /*
             * Iterate over all <tbody> elements to get the <tr> tags.
             */
            for ( Element ele : tbodyEles ) {
                Elements trEles = ele.select ( "tr" );

                /*
                 * Every <tr> element should contain the different boss stats/information.
                 */
                for ( Element trEle : trEles ) {
                    Elements tdEles = trEle.select ( "td" );

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

        return bossStats;
    }

    /**
     * @return
     */
    public Collection < String > getElderBosses ( ) {
        return Arrays.stream ( getStringValue ( "Elder Map Boss" ).split ( "\n" ) ).toList ( );
    }

    /**
     * @return
     */
    public Collection < String > getLinkedMaps ( ) {
        return Arrays.stream ( getStringValue ( "Atlas Linked" ).split ( "\n" ) ).toList ( );
    }

    /**
     * @return
     */
    public Collection < String > getUpgradedFrom ( ) {
        return Arrays.stream ( getStringValue ( "Upgraded From" ).split ( "\n" ) ).toList ( );
    }

    /**
     * @return
     */
    public Collection < String > getTopologies ( ) {
        return Arrays.stream ( getStringValue ( "Topologies" ).split ( "\n" ) ).toList ( );
    }

    /**
     * <p>
     * Returns the href value for loading or reference. No promises this will work elsewhere.
     * </p>
     */
    private String getHrefValue ( String title ) {
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

        return UNKNOWN;
    }

    /**
     *
     */
    private String getStringValue ( String title ) {
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

        return UNKNOWN;
    }

    private int getIntegerValue ( String title ) {
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
}
