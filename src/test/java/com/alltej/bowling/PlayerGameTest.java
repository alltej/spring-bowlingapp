package com.alltej.bowling;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerGameTest {

    @Test public void create_player_game_returns_name() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        assertEquals( "Jeff", pg.getName() );
    }

    @Test public void getScore_roll_is_strike() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        pg.roll( 10 );
        assertEquals( 10, pg.getScore() );
    }

    @Test public void getScore_roll_is_strike_spare() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        pg.roll( 10 );
        pg.roll( 7 );
        pg.roll( 3 );
        int score = pg.getScore();
        assertEquals( 30, score );
        int s1 = pg.getScoreForFrame( 1 );
        assertEquals( 20, s1 );
    }

    @Test public void getScore_roll_is_strike_spare_pinfalls() {
        PlayerGame pg = PlayerGame.create( "Jeff" );

        //sequence under test
        pg.roll( 10 );
        pg.roll( 7 );
        pg.roll( 3 );
        pg.roll( 9 );
        pg.roll( 0 );
        int score = pg.getScore();
        assertEquals( 48, score );
        int s2 = pg.getScoreForFrame( 2 );
        assertEquals( 39, s2 );
        int s3 = pg.getScoreForFrame( 3 );
        assertEquals( 48, s3 );
    }

    @Test public void getScore_roll_is_strike_pinfalls() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        pg.roll( 10 );

        pg.roll( 7 );
        pg.roll( 3 );

        pg.roll( 9 );
        pg.roll( 0 );

        //sequence under test
        pg.roll( 10 );
        pg.roll( 0 );
        pg.roll( 8 );
        int score = pg.getScore();
        assertEquals( 74, score );
        int s5 = pg.getScoreForFrame( 5 );
        assertEquals( score, s5 );
    }

    @Test public void getScore_roll_is_spare_foul_pinfall() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        pg.roll( 10 );

        pg.roll( 7 );
        pg.roll( 3 );

        pg.roll( 9 );
        pg.roll( 0 );

        pg.roll( 10 );

        pg.roll( 0 );
        pg.roll( 8 );

        //sequence under test
        pg.roll( 8 );
        pg.roll( 2 );

        pg.roll( "F" ); //TODO: =="F"
        pg.roll( 6 );

        int score = pg.getScore();
        assertEquals( 90, score );
        int s6 = pg.getScoreForFrame( 6 );
        assertEquals( 84, s6 );
    }

    @Test public void getScore_roll_is_strike_strike_strike() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        pg.roll( 10 );

        pg.roll( 7 );
        pg.roll( 3 );

        pg.roll( 9 );
        pg.roll( 0 );

        pg.roll( 10 );

        pg.roll( 0 );
        pg.roll( 8 );

        pg.roll( 8 );
        pg.roll( 2 );

        pg.roll( "F" );
        pg.roll( 6 );

        //sequence under test
        pg.roll( 10 );
        pg.roll( 10);
        pg.roll( 10 );

        int score = pg.getScore();
        assertEquals( 150, score );
        int s8 = pg.getScoreForFrame( 8 );
        assertEquals( 120, s8 );
    }

    @Test public void getScore_roll_is_strike_strike_strike_pinfalls() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        pg.roll( 10 );

        pg.roll( 7 );
        pg.roll( 3 );

        pg.roll( 9 );
        pg.roll( 0 );

        pg.roll( 10 );

        pg.roll( 0 );
        pg.roll( 8 );

        pg.roll( 8 );
        pg.roll( 2 );

        pg.roll( "F" ); //TODO: =="F"
        pg.roll( 6 );

        //sequence under test
        pg.roll( 10 );
        pg.roll( 10);
        pg.roll( 10 );

        pg.roll( 8 );
        pg.roll( 1 );

        int score = pg.getScore();
        assertEquals( 167, score );
        int s9 = pg.getScoreForFrame( 9 );
        assertEquals( 148, s9 );

        //assert game score is same as frame 10 score
        int s10 = pg.getScoreForFrame( 10 );
        assertEquals( score, s10 );
    }

    @Test public void perfect_score_test() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.getScore();
        assertEquals( 300, pg.getScore() );
    }


    @Test public void print_score_test() {
        PlayerGame pg = PlayerGame.create( "Jeff" );
        pg.roll( 10 );
        pg.roll( 7 );
        pg.roll( 3 );
        pg.roll( 9 );
        pg.roll( 0 );
        pg.roll( 10 );
        pg.roll( 0 );
        pg.roll( 8 );
        pg.roll( 8 );
        pg.roll( 2 );
        pg.roll( "F" );
        pg.roll( 6 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 10 );
        pg.roll( 8);
        pg.roll( 1 );

        StringBuilder expected = new StringBuilder( "Jeff" );
        expected.append( "\n" )
                .append( "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1" )
                .append( "\n" )
                .append( "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167" );

        //System.out.println("====");
        String s = pg.print();
        //System.out.println( s );

        assertEquals( expected.toString(), s );
    }
}
