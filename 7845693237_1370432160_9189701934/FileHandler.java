import java.io.*;

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

    public void writeToOutputFile(String firstSequence, String secondSequence, long cost, double time, double memory) throws IOException {
        //TODO
        File fout = new File("output.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        bw.write(firstSequence);
        bw.newLine();
        bw.write(secondSequence);
        bw.newLine();
        bw.write(String.valueOf(cost));
        bw.newLine();
        bw.write(String.valueOf(time));
        bw.newLine();
        bw.write(String.valueOf(memory));
        bw.newLine();

        bw.close();
    }
}
