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

    public String readLogin(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Ronald.Pavel-PC\\IdeaProjects\\MathTournament.git\\MathTournament\\sources\\login\\login" + fileName + ".txt"));

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

    public void addLogin(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Ronald.Pavel-PC\\IdeaProjects\\MathTournament.git\\MathTournament\\sources\\login\\login" + fileName + ".txt", true)));
            out.println(1);
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
