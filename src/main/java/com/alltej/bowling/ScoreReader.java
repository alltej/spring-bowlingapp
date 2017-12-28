package com.alltej.bowling;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ScoreReader {

    public ArrayList<String[]> getScores(String inputFile) throws IOException {
        //ArrayList<String[]> inputList = new ArrayList<>();

        List<String> lines = Files.readAllLines( Paths.get(inputFile),
                StandardCharsets.UTF_8);

        List<String[]> inputList = Files.readAllLines( Paths.get( inputFile ), StandardCharsets.UTF_8 )
                .stream().map( l -> l.split( "\\s+" ) )
                .collect( Collectors.toList() );
//        for (String line : lines) {
//            String[] fields = line.trim().split("\\s+");
//            inputList.add( fields );
//        }

        return ( ArrayList<String[]> ) inputList;
    }
}
