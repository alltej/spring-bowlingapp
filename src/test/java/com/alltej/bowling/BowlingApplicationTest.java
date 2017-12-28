package com.alltej.bowling;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class BowlingApplicationTest {

    @Test public void file_in_current_dir() {
        BowlingApplication g = new BowlingApplication();
        String basePath = new File("").getAbsolutePath();
        String filePath = g.getFullPath( "input.txt" );
        assertEquals( basePath + "\\input.txt",filePath );
    }

    @Test public void file_in_relative_path_forwardslash() {
        BowlingApplication g = new BowlingApplication();
        String basePath = new File("").getAbsolutePath();
        String filename = "target/input.txt";
        String filePath = g.getFullPath( filename );
        assertEquals( basePath + "\\target\\input.txt" ,filePath );
    }

    @Test public void file_in_relative_path_backslash() {
        BowlingApplication g = new BowlingApplication();
        String basePath = new File("").getAbsolutePath();
        String filename = "target\\input.txt";
        String filePath = g.getFullPath( filename );
        assertEquals( basePath + "\\target\\input.txt" ,filePath );
    }

}
