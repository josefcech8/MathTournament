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

    public boolean confirmTimeLimit(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            String everything = sb.toString();

            String[] splitEverything = everything.split("_");

            br.close();

            return splitEverything[splitEverything.length-1].equals("start") ? true : false;
        } catch(Exception e) {
            return false;
        }
    }

    public void addTimeLimit(String fileName, boolean TimeLimit) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".txt", true)));
            out.println(TimeLimit ? "_start" : "_stop");
            out.close();
        } catch (IOException e) { }
    }

    public void addRecords(String fileName, int points, String fileMessage) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".txt", true)));
            out.println(points + " " + fileMessage);
            out.close();
        } catch (IOException e) { }
    }

}
