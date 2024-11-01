package com.github.frizzy.poedbc.Connector;

public abstract class PoeDBParser {

    PoeDBParser ( String name ) {

    }

    private String getURL ( ) {
        return "";
    }

    public boolean parse ( ) {
        return false;
    }

    public boolean parseNew ( String newName ) {
        return false;
    }
}
