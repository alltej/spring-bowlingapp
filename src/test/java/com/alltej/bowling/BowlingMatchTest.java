package com.alltej.bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BowlingMatchTest {

    private BowlingMatch g;
    @Before
    public void runBeforeTestMethod() {
        g = BowlingMatch.create();
    }

    @Test public void bowlingGame_first_roll_player_size_contains_one() {

        g.roll( "Jeff", 10 );
        assertEquals( 1,g.getPlayers().size() );
    }

    @Test public void bowlingGame_multiple_players_returns_number_of_players() {
        g.roll( "Jeff", 10 );
        g.roll( "John", 10 );
        g.roll( "Michael", 10 );
        g.roll( "Josh", 10 );
        assertEquals( 4,g.getPlayers().size() );
    }

    @Test public void bowlingGame_getFrameHeaderString_has_correct_format() {
        g.roll( "Jeff", 10 );
        StringBuilder sb = new StringBuilder( "Frame" );
        for(int i=1; i<=10;i++) {
            sb.append("\t\t").append( i );
        }
        assertEquals( sb.toString(), g.getPrintHeaderString() );
        System.out.println(sb.toString());
    }
}
