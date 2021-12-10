package com.algo.alignment;

import com.algo.alignment.core.SequenceAlignmentBasicAlgorithm;
import com.algo.alignment.core.SequenceAlignmentDnCAlgorithm;
import com.algo.alignment.pojo.Input;
import com.algo.alignment.utils.FileHandler;
import com.algo.alignment.utils.InputGenerator;

import java.io.IOException;
import java.util.Arrays;

public class DncSequenceAlignmentMain {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        String path = "./AlgorithmProject/resources/input.txt";

        FileHandler fileHandler = new FileHandler();
        System.out.println();
        Input input = fileHandler.readFile(path);

        //TODO: input file name needs to be taken as a command line argument as mentioned here https://piazza.com/class/ksmlgkord5752e?cid=2325
        //Calling Input Generator
        InputGenerator inputGenerator = new InputGenerator();
        String[] inputSequences = inputGenerator.getInputSequence(input.firstBase, input.secondBase, input.firstIndices, input.secondIndices);

        SequenceAlignmentDnCAlgorithm sequenceAlignmentBasicAlgorithm = new SequenceAlignmentDnCAlgorithm();
        String[] alignment = sequenceAlignmentBasicAlgorithm.getAlignment(inputSequences[0], inputSequences[1]);
        int cost = sequenceAlignmentBasicAlgorithm.minCost(alignment[0], alignment[1]);
        long memoryInBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //convert to kilobytes
        double memoryInKilobytes = (memoryInBytes / 1024.0);

        long estimatedTimeInMilliSeconds = System.currentTimeMillis() - startTime;
        double seconds = estimatedTimeInMilliSeconds / 1000.0;

        fileHandler.writeToOutputFile(alignment[0], alignment[1], cost, seconds, memoryInKilobytes);
    }
}
