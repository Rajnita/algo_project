import java.io.IOException;

public class Efficient_7845693237_1370432160_9189701934 {
    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            System.err.println("Please specify input.txt file");
            System.exit(1);
        }
        long startTime = System.currentTimeMillis();
        FileHandler fileHandler = new FileHandler();
        Input input = fileHandler.readFile(args[0]);
        if (input.firstBase == null || input.secondBase == null || input.firstBase.length() <= 0 || input.secondBase.length() <= 0) {
            System.err.println("Please give valid inputs. One or both of the base string is null or empty");
            System.exit(1);
        }
        //Calling Input Generator
        String[] inputSequences = InputGenerator.getInputSequence(input.firstBase, input.secondBase, input.firstIndices, input.secondIndices);

        //Calling Sequence Alignment Efficient Algorithm
        SequenceAlignmentDnCAlgorithm sequenceAlignmentDnCAlgorithm = new SequenceAlignmentDnCAlgorithm();
        Output output = sequenceAlignmentDnCAlgorithm.alignSequences(inputSequences[0], inputSequences[1]);
        long estimatedTimeInMilliSeconds = System.currentTimeMillis() - startTime;
        double seconds = estimatedTimeInMilliSeconds / 1000.0;
        //this is in bytes
        long memoryInBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //convert to kilobytes
        double memoryInKilobytes = (memoryInBytes / 1024.0);

        fileHandler.writeToOutputFile(output.firstSequenceResult, output.secondSequenceResult, output.minCost, seconds, memoryInKilobytes);

    }
}
