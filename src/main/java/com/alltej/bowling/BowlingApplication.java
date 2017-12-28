package com.alltej.bowling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Component
@SpringBootApplication
public class BowlingApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(BowlingApplication.class);

	@Autowired
	private ScoreReader scoreRetriever;

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BowlingApplication.class);
        app.setBannerMode( Banner.Mode.OFF);
        app.run(args);
	}

	@Override public void run( String... args ) {
        if (args.length > 0) {
			ArrayList<String[]> scores;
			try {
				String filePath = getFullPath( args[0] );
				scores = scoreRetriever.getScores( filePath );
				CreateBowlingMatch( scores );
			} catch ( IOException e ) {
				logger.error( String.valueOf( e ) );
			}

        } else {
			logger.error( "Please provide input file" );
		}
	}

	public String getFullPath( String filename ) {
		String basePath = new File(filename).getAbsolutePath();
		return basePath;
	}

	private void CreateBowlingMatch( ArrayList<String[]> list ) {
		try {
			BowlingMatch g = BowlingMatch.create();
			for ( String[] s:
                   list) {
                g.roll( s[0], s[1] );
            }
			System.out.println(g.getPrintString());
		} catch ( Exception e ) {
			logger.error( e.getMessage() );
		}
	}
}
