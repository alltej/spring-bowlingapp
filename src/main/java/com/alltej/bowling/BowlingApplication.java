package com.alltej.bowling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	@Bean(name = "bowlingMatch")
	public BowlingMatch getBowlingMatch(){
		return BowlingMatch.create();
	}

	@Override public void run( String... args ) throws IllegalArgumentException {
		if (args.length <= 0)
			throw new IllegalArgumentException( "Please provide input file" );

		try {
			List<String[]> scores = Files.readAllLines( Paths.get( new File(args[0]).getAbsolutePath() ), StandardCharsets.UTF_8 )
				.stream().map( l -> l.split( "\\s+" ) )
				.collect( Collectors.toList() );
			scores.forEach( s -> getBowlingMatch().roll( s[0], s[1] ) );
			System.out.println(getBowlingMatch().getPrintString());
        } catch ( IOException e ) {
            logger.error( String.valueOf( e ) );
        }

	}
}
