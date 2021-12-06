package com.algo.alignment;

import com.algo.alignment.core.SequenceAlignmentBasicAlgorithm;
import com.algo.alignment.pojo.Input;
import com.algo.alignment.pojo.Output;
import com.algo.alignment.utils.FileHandler;
import com.algo.alignment.utils.InputGenerator;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        String path = "/Users/rajnitaleichombam/Downloads/project_570/algo_project/AlgorithmProject/resources/input.txt";

        FileHandler fileHandler = new FileHandler();
        Input input = fileHandler.readFile(path);

        //TODO: input file name needs to be taken as a command line argument as mentioned here https://piazza.com/class/ksmlgkord5752e?cid=2325
        //Calling Input Generator
        String[] inputSequences = InputGenerator.getInputSequence(input.firstBase, input.secondBase, input.firstIndices, input.secondIndices);

        //Calling Sequence Alignment Basic Algorithm
        SequenceAlignmentBasicAlgorithm sequenceAlignmentBasicAlgorithm = new SequenceAlignmentBasicAlgorithm(30);
        Output output = sequenceAlignmentBasicAlgorithm.alignSequences(inputSequences[0], inputSequences[1]);
        //TODO: Append minimum cost , sequences, etc to an output file
        long estimatedTimeInMilliSeconds = System.currentTimeMillis() - startTime;
        System.out.println(estimatedTimeInMilliSeconds);
        double seconds = estimatedTimeInMilliSeconds / 1000.0;
        System.out.println(seconds);
        //this is in bytes
        long memoryInBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //convert to kilobytes
        double memoryInKilobytes = (memoryInBytes / 1024.0);
        System.out.println(memoryInKilobytes);
        fileHandler.writeToOutputFile(output.firstSequenceResult, output.secondSequenceResult, output.minCost, seconds, memoryInKilobytes);
    }
}
