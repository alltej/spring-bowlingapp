package com.alltej.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlingMatch {

    final int NUMBER_OF_FRAMES = 10;
    private HashMap<String, PlayerGame> playerGames;

    public static BowlingMatch create(){
        return new BowlingMatch();
    }

    private BowlingMatch() {
        playerGames = new HashMap<>();
    }

    public List<String> getPlayers() {
        return new ArrayList<>( playerGames.keySet() );
    }

    public void roll( String name, int pins ) {
        PlayerGame pg = tryGetAddPlayer( name );//playerGames.get( name );
        pg.roll(pins);
    }

    private PlayerGame tryGetAddPlayer( String name ) {
        String key = name.trim().toUpperCase();
        if (playerGames.containsKey( key )){
            return playerGames.get( key );
        }
        playerGames.put( key, new PlayerGame(name ) );
        return playerGames.get( key );
    }

    public void roll( String name, String pins ) {
        PlayerGame pg = tryGetAddPlayer( name );//playerGames.get( name );
        pg.roll(pins);
    }

    public String getPrintString(){
        StringBuilder sb = new StringBuilder( getPrintHeaderString());
        for (Map.Entry<String,PlayerGame> pg : playerGames.entrySet()){
            sb.append( "\n" );
            sb.append(pg.getValue().print()  );
        }
        return sb.toString();
    }

    public String getPrintHeaderString() {
        StringBuilder sb = new StringBuilder( "Frame" );
        for ( int i = 1; i <= NUMBER_OF_FRAMES; i++) {
            sb.append("\t\t").append( i );
        }
        return sb.toString();
    }
}
