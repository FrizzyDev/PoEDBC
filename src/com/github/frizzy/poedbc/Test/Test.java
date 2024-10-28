package com.github.frizzy.poedbc.Test;

import com.github.frizzy.poedbc.Connector.PoeDBMaps;
import com.github.frizzy.poedbc.Connector.URLResourceHandler;
import com.github.frizzy.poedbc.Data.BossStats;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Test {

    public Test ( ) {

    }

    public static void main ( String[] args ) {
        URLResourceHandler handler = URLResourceHandler.getInstance ();
        String link = handler.getMapLinkFor ( "Gardens" );
        System.out.println ( link );
    }

    private static void testAllValues ( ) {
        long startTime = System.currentTimeMillis ();

        PoeDBMaps maps = new PoeDBMaps ( "Sepulchre" );
        maps.parse ( );

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
    }
}
