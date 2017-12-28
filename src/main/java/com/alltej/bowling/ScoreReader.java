package com.alltej.bowling;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScoreReader {

    public ArrayList<String[]> getScores(String inputFile) throws IOException {
        ArrayList<String[]> inputList = new ArrayList<>();

        List<String> lines = Files.readAllLines( Paths.get(inputFile),
                StandardCharsets.UTF_8);

        for (String line : lines) {
            String[] fields = line.trim().split("\\s+");
            inputList.add( fields );
        }

        return inputList;
    }
}
