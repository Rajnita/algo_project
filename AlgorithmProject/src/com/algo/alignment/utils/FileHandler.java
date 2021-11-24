package com.algo.alignment.utils;

import com.algo.alignment.pojo.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    public Input readFile(String path) throws IOException {
        Input input = new Input();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isFirstBaseStringRead = false;
            while ((line = br.readLine()) != null) {
                // process the line.
                if (!isFirstBaseStringRead) {
                    input.firstBase = line;
                    isFirstBaseStringRead = true;
                    continue;
                }
                if (Character.isDigit(line.toCharArray()[0])) {
                    input.firstIndices.add(Integer.valueOf(line));
                } else {
                    input.secondBase = line;
                    break;
                }
            }

            //putting rest of the indices for the second base String
            while ((line = br.readLine()) != null) {
                input.secondIndices.add(Integer.valueOf(line));
            }
        }
        return input;
    }

    public void writeToOutputFile() {
        //TODO
    }
}
