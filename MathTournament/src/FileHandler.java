import java.io.*;

public class FileHandler {

    public String readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("rank.txt"));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            String everything = sb.toString();

            br.close();

            return everything;
        } catch(Exception e) {
            return null;
        }
    }

    public void addRecords(String fileName, int points, String fileMessage) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".txt", true)));
            out.println(points + " " + fileMessage);
            out.close();
        } catch (IOException e) { }
    }

}
