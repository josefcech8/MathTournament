import java.io.*;

public class FileHandler {

    /*
    HOME
    String pathHomeLogin = "C:\\Users\\Ronald.Pavel-PC\\IdeaProjects\\MathTournament.git\\MathTournament\\sources\\login\\login";
    String pathHomePoints = "C:\\Users\\Ronald.Pavel-PC\\IdeaProjects\\MathTournament.git\\MathTournament\\sources\\points\\";
    */

    /*
    POLNÍ
    String pathPolniLogin = "C:\\sources\\login\\login";
    String pathPolniPoints = "C:\\sources\\points\\";
    */

    /*
    MATH TOURNAMENT
    P:\\
    */

    String pathLogin = "C:\\Users\\Ronald.Pavel-PC\\IdeaProjects\\MathTournament.git\\MathTournament\\sources\\login\\login";
    String pathPoints = "C:\\Users\\Ronald.Pavel-PC\\IdeaProjects\\MathTournament.git\\MathTournament\\sources\\points\\";

    public String readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathPoints + "_rank.txt"));

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
            BufferedReader br = new BufferedReader(new FileReader(pathLogin + fileName.toLowerCase() + ".txt"));

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
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathLogin + fileName.toLowerCase() + ".txt", true)));
            out.println(1);
            out.close();
        } catch (IOException e) { }
    }

    public void addRecords(String fileName, int points, String fileMessage) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathPoints + fileName.toLowerCase() + ".txt", true)));
            out.println(points + " " + fileMessage);
            out.close();
        } catch (IOException e) { }
    }

}
