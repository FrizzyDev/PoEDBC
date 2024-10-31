package com.github.frizzy.poedbc.Test;

import com.github.frizzy.poedbc.Connector.PoeDBMaps;
import com.github.frizzy.poedbc.Connector.URLResourceHandler;
import com.github.frizzy.poedbc.Data.BossStats;

import javax.swing.JOptionPane;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Test {

    public Test ( ) {

    }

    public static void main ( String[] args ) {
       String input = JOptionPane.showInputDialog ( "Enter in the map you want to retrieve" );

       if ( input != null && ( !input.isEmpty () || !input.isBlank () ) ) {
           testAllValues ( input );
       } else {
           System.out.println ( "Map name is null, empty, or blank. Exiting.");
       }
    }

    /**
     *
     */
    private static void testAllValues ( String map ) {
        long startTime = System.currentTimeMillis ();

        PoeDBMaps maps = new PoeDBMaps ( map );
        maps.parse ( );

        String mapName = maps.getMapName ();
        String mapUrl = maps.getMapUrl ();

        int dropLevel = maps.getDropLevel ( );
        int monsterLevel = maps.getMonsterLevel ();
        int act = maps.getAct ( );
        int clearingAbility = maps.getClearingAbility ( );
        int mobCount = maps.getMobCount ();
        int bossDifficulty = maps.getBossDifficulty ();
        String vaalArea = maps.getVaalArea ();
        String baseType = maps.getBaseType ();
        String itemClass = maps.getItemClass ();
        String type = maps.getType ();
        String icon = maps.getIcon ();
        String tileSet = maps.getTileSet ();
        String bossBasedOn = maps.getBossBasedOn ();
        String bossNotes = maps.getBossNotes ();
        String additionalNotes = maps.getAdditionalNotes ();
        String reference = maps.getReference ();
        String fewObstacles = maps.getFewObstacles ();
        String bossNotInOwnRoom = maps.getBossNotInOwnRoom ();
        String outdoors = maps.getOutdoors ();
        String linear = maps.getLinear ();
        String loading = maps.getLoading ();
        String hideout = maps.getHideout ();
        String mods = maps.getMods ();
        Collection < String > flags = maps.getFlags ( );
        Collection < String > tags = maps.getTags ();
        Collection < String > cardTags = maps.getCardTags ( );
        Collection < String > divCards = maps.getDivinationCards ( );
        Collection < String > elderBosses = maps.getElderBosses ( );
        Collection < String > bosses = maps.getBosses ( );
        Collection < String > linkedMaps = maps.getLinkedMaps ( );
        Collection < String > upgradedFromMaps = maps.getUpgradedFrom ( );
        Collection < String > topologies = maps.getTopologies ();
        Collection < BossStats > bossStats = maps.getBossesStats ();

        long stopTime = System.currentTimeMillis ();
        System.out.println ( "Time taken: " + ( stopTime - startTime )  + " milliseconds." );
        System.out.println ( "----------------------" );
        System.out.println ( "Map name: " + mapName );
        System.out.println ( "Map URL: " + mapUrl );
        System.out.println ( "----------------------" );
        System.out.println ( "DropLevel: " + dropLevel );
        System.out.println ( "Monster Level: " + monsterLevel );
        System.out.println ( "Act: " + act );
        System.out.println ( "Clearing Ability: " + clearingAbility );
        System.out.println ( "Mob Count: " + mobCount );
        System.out.println ( "Boss Difficulty: " + bossDifficulty );
        System.out.println ( "Vaal Area: " + vaalArea );
        System.out.println ( "Base Type: " + baseType );
        System.out.println ( "Item Class: " + itemClass );
        System.out.println ( "Type: " + type );
        System.out.println ( "Icon: " + icon );
        System.out.println ( "Tile Set: " + tileSet );
        System.out.println ( "Boss Based On: " + bossBasedOn );
        System.out.println ( "Boss Notes: " + bossNotes );
        System.out.println ( "Additional Notes: " + additionalNotes );
        System.out.println ( "Reference: " + reference );
        System.out.println ( "Few Obstacles: " + fewObstacles );
        System.out.println ( "Boss Not In Own Room: " + bossNotInOwnRoom );
        System.out.println ( "Outdoors: " + outdoors );
        System.out.println ( "Linear: " + linear );
        System.out.println ( "Loading: " + loading );
        System.out.println ( "Hideout: " + hideout );
        System.out.println ( "Flags: " + flags );
        System.out.println ( "Tags: " + tags );
        System.out.println ( "Card Tags: " + cardTags );
        System.out.println ( "Divination Cards: " + divCards );
        System.out.println ( "Elder Bosses: " + elderBosses );
        System.out.println ( "Bosses: " + bosses );
        System.out.println ( "Linked Atlas Maps: " + linkedMaps );
        System.out.println ( "Upgraded From Maps: " + upgradedFromMaps );
        System.out.println ( "Topologies: " + topologies );
        System.out.println ( "Boss Stats: " + bossStats );
        System.out.println ( "Mods: " + mods );
        System.out.println ( "----------------------" );
    }
}
