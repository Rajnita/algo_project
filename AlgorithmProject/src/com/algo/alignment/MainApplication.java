package com.algo.alignment;

import com.algo.alignment.core.SequenceAlignmentBasicAlgorithm;
import com.algo.alignment.pojo.Input;
import com.algo.alignment.utils.FileHandler;
import com.algo.alignment.utils.InputGenerator;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        String path = "resources/input.txt";

        FileHandler fileHandler = new FileHandler();
        Input input = fileHandler.readFile(path);

        //Calling Input Generator
        InputGenerator inputGenerator = new InputGenerator();
        String[] inputSequences = inputGenerator.getInputSequence(input.firstBase, input.secondBase, input.firstIndices, input.secondIndices);

        //Calling Sequence Alignment Basic Algorithm
        SequenceAlignmentBasicAlgorithm sequenceAlignmentBasicAlgorithm= new SequenceAlignmentBasicAlgorithm(30);
        int minimumCost=sequenceAlignmentBasicAlgorithm.alignSequences(inputSequences[0], inputSequences[1]);
        //TODO: Append minimum cost , sequences, etc to an output file

    }
}
