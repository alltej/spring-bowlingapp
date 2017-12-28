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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class BowlingApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(BowlingApplication.class);

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BowlingApplication.class);
        app.setBannerMode( Banner.Mode.OFF);
        app.run(args);
	}

	@Override public void run( String... args ) throws IllegalArgumentException {
		if (args.length <= 0)
			throw new IllegalArgumentException( "Please provide input file" );

		try {
            String filePath = new File(args[0]).getAbsolutePath();
			List<String[]> scores = Files.readAllLines( Paths.get( filePath ), StandardCharsets.UTF_8 )
				.stream().map( l -> l.split( "\\s+" ) )
				.collect( Collectors.toList() );
            CreateBowlingMatch( scores );
        } catch ( IOException e ) {
            logger.error( String.valueOf( e ) );
        }

	}

	private void CreateBowlingMatch( List<String[]> list ) {
		try {
			BowlingMatch g = BowlingMatch.create();
			list.forEach( s -> g.roll( s[0], s[1] ) );
			System.out.println(g.getPrintString());
		} catch ( Exception e ) {
			logger.error( e.getMessage() );
		}
	}
}
