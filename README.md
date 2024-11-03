## Path of Exile poe.db Connector
PoEDBC is a connector library to retrieve data from poe.db. By using JSoup, data from web pages on poe.db can be parsed into useable values for an application. Currently, nearly all data that is available for a map on poe.db can be retrieved via the parser.

## Usage

```java
PoeDBMaps pdm = new PoeDBMaps ( "Gardens" );
pdm.parse ();

String mapName = pdm.getMapName ( );
String mapUrl = pdm.getMapUrl ( );
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
```
## Additional Usage
You can connect to poe.db and retrieve data for unique and currency items as well. The process is similar to retrieving maps.

```java
PoeDBCurrency pdc = new PoeDBCurrency ( "Divine Orb" );
pdc.parse ( );
String name = pdc.getCurrencyName ( );
String url = pdc.getCurrencyUrl ( );
String cost = pdc.getCost ( );
//and so forth...
PoeDBUnique pdu = new PoeDBUnique ( "Arborix" );
pdu.parse ( );
//Retrieve data here
```

## Reuse
PoeDBMaps instances can be reused. Additionally, PoeDBCurrency and PoeDBUniques can be reused following the same method.
```java
PoeDBMaps pdm = new PoeDBMaps ( "Gardens" );
//code here
pdm.parseNew ( "Foundry" );
```
All values will now be reset and retrievable in regards to the new map.

## Speed
In my testing, it takes around 1 second to parse a page from poe.db. In the future, I plan on attempting to identify opportunities in improving performance.

## Future plans
Any other data that is available for a map on poe.db will be implemented so it can be useable. Additionally, other item categories such as uniques and skill gems, will eventually be retrievable as well.

## Contributing
If you find data missing or have suggestions for improving performance, submit an issue or pull request. I'm more than happy to accept help. I am not a professional programmer, this is a hobby, so I am sure some of my code is questionable.
