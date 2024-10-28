package com.github.frizzy.poedbc.Data;

/**
 *
 * @author Frizzy
 * @version 0.1-Settlers
 * @since 0.1-Settlers
 *
 * @param level
 * @param name
 * @param experience
 * @param damage
 * @param attackTime
 * @param life
 * @param armour
 * @param evasion
 * @param energyShield
 * @param fireResistance
 * @param coldResistance
 * @param lightningResistance
 * @param chaosResistance
 */
public record BossStats( int level, String name, int experience, int damage, double attackTime, int life, int armour, int evasion, int energyShield,
                         int fireResistance, int coldResistance, int lightningResistance, int chaosResistance) {

    @Override
    public int level ( ) {
        return level;
    }

    @Override
    public String name ( ) {
        return name;
    }

    @Override
    public int experience ( ) {
        return experience;
    }

    @Override
    public int damage ( ) {
        return damage;
    }

    @Override
    public double attackTime ( ) {
        return attackTime;
    }

    @Override
    public int life ( ) {
        return life;
    }

    @Override
    public int armour ( ) {
        return armour;
    }

    @Override
    public int evasion ( ) {
        return evasion;
    }

    @Override
    public int energyShield ( ) {
        return energyShield;
    }

    @Override
    public int fireResistance ( ) {
        return fireResistance;
    }

    @Override
    public int coldResistance ( ) {
        return coldResistance;
    }

    @Override
    public int lightningResistance ( ) {
        return lightningResistance;
    }

    @Override
    public int chaosResistance ( ) {
        return chaosResistance;
    }

    @Override
    public String toString ( ) {
        return "BossStats{" +
                "level=" + level +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", damage=" + damage +
                ", attackTime=" + attackTime +
                ", life=" + life +
                ", armour=" + armour +
                ", evasion=" + evasion +
                ", energyShield=" + energyShield +
                ", fireResistance=" + fireResistance +
                ", coldResistance=" + coldResistance +
                ", lightningResistance=" + lightningResistance +
                ", chaosResistance=" + chaosResistance +
                '}';
    }
}
