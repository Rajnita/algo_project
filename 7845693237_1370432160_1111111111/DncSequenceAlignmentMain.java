import java.io.IOException;

public class DncSequenceAlignmentMain {
    public static void main(String[] args) throws IOException {
        String path = "/Users/rajnitaleichombam/Downloads/project_570/algo_project/AlgorithmProject/resources/input.txt";

        FileHandler fileHandler = new FileHandler();
        System.out.println();
        Input input = fileHandler.readFile(path);

        //TODO: input file name needs to be taken as a command line argument as mentioned here https://piazza.com/class/ksmlgkord5752e?cid=2325
        //Calling Input Generator
        String[] inputSequences = InputGenerator.getInputSequence(input.firstBase, input.secondBase, input.firstIndices, input.secondIndices);

        SequenceAlignmentDnCAlgorithm sequenceAlignmentBasicAlgorithm = new SequenceAlignmentDnCAlgorithm();
        sequenceAlignmentBasicAlgorithm.getAlignment(inputSequences[0], inputSequences[1]);
        System.out.println(sequenceAlignmentBasicAlgorithm.arrowPath);
    }
}
