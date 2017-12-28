package com.alltej.bowling;

public class PlayerGame {
    private String[] rolls;
    private int[] scoreByFrame;
    private int currentRoll;

    public static final int MAX_NUMBER_OF_ROLL = 21;
    public static final int NUMBER_OF_FRAMES = 10;
    public static final String NEW_LINE_SEPARATOR = "\n";
    private static  String TAB_SINGLE = "\t";
    private static String TAB_DOUBLE = "\t\t";
    private static String STRIKE_CHAR = "X";
    private static String SPARE_CHAR = "/";
    
    private String name;

    public static PlayerGame create( String name ) {
        return new PlayerGame( name );
    }

    public PlayerGame( String name ) {
        this.name = name;
        this.rolls = new String[MAX_NUMBER_OF_ROLL];
        this.scoreByFrame = new int[NUMBER_OF_FRAMES];
    }

    public void roll(int p) {
        rolls[currentRoll++] = String.valueOf( p );
    }

    public void roll(String p) {
        rolls[currentRoll++] = p;
    }

    public int getScore() {
        int score = 0;
        int rollIndex = 0;

        for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
            if (isStrike(rollIndex)) {
                score += 10 + strikeBonus(rollIndex);
                rollIndex++;
            } else if (isSpare(rollIndex)) {
                score += 10 + spareBonus(rollIndex);
                rollIndex += 2;
            } else {
                score += sumOfRolls(rollIndex);
                rollIndex += 2;
            }
            scoreByFrame[i] = score;
        }

        return score;
    }

    private boolean isStrike(int rollIndex) {
        return getScoreForRoll(rollIndex) == 10;
    }

    private boolean isSpare(int rollIndex) {
        return sumOfRolls(rollIndex) == 10;
    }

    private int strikeBonus(int rollIndex) {
        return sumOfRolls(rollIndex+1);
    }

    private int spareBonus(int rollIndex) {
        return getScoreForRoll(rollIndex+2);
    }

    private int sumOfRolls(int rollIndex) {
        return getScoreForRoll(rollIndex) + getScoreForRoll(rollIndex+1);
    }

    private int getScoreForRoll( int rollIndex ) {
        try {
            return Integer.parseInt( rolls[rollIndex] );
        } catch ( NumberFormatException e ) {
            return 0;
        }
    }

    public int getScoreForFrame( int frame ) {
        //frame is 1 based index
        return scoreByFrame[frame-1];
    }

    public String print() {
        return getName() + NEW_LINE_SEPARATOR + getRowPinfallsString() + NEW_LINE_SEPARATOR + getRowScoreString();
    }

    private String getRowPinfallsString() {
        StringBuilder sb = new StringBuilder( "Pinfalls" );
        int rollIndex = 0;
        for (int i = 0; i <=NUMBER_OF_FRAMES; i++) {
            if (isStrike(rollIndex)) {
                if (i >=(NUMBER_OF_FRAMES-1)) { 
                    sb.append( TAB_SINGLE ).append( STRIKE_CHAR );
                }else{
                    sb.append( TAB_DOUBLE ).append( STRIKE_CHAR );
                }
                rollIndex++;
            } else if (isSpare(rollIndex)) {
                
                sb.append( TAB_SINGLE ).append( rolls[rollIndex] ).append( TAB_SINGLE ).append( SPARE_CHAR );
                rollIndex += 2;
            } else {
                sb.append( TAB_SINGLE ).append( rolls[rollIndex] ).append( TAB_SINGLE ).append( rolls[rollIndex+1] );
                rollIndex += 2;
            }
        }
        return sb.toString();
    }


    private String getRowScoreString() {
        StringBuilder sb = new StringBuilder( "Score" );
        int score = 0;
        int rollIndex = 0;

        for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
            //System.out.println( "frame==" + frame );
            if (isStrike(rollIndex)) {
                score += 10 + strikeBonus(rollIndex);
                sb.append(TAB_DOUBLE).append( score );
                rollIndex++;
            } else if (isSpare(rollIndex)) {
                score += 10 + spareBonus(rollIndex);
                sb.append(TAB_DOUBLE).append( score );
                rollIndex += 2;
            } else {
                score += sumOfRolls(rollIndex);
                sb.append(TAB_DOUBLE).append( score );
                rollIndex += 2;
            }
        }
        return sb.toString();
    }


    public String getName() {
        return name;
    }
}
