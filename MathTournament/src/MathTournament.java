/*
    (1) Make components in GraphicalUserInterface.class private
    (2) Change magic numbers in setBounds methods to constants or dynamic layout
        Center text in title JLabel
    (3) set visibility of panel to false
    (4) Add ActionListener to text fields when submitting the result
    (5) When you pass the task you will get the check sign
    (6) if there will be a lot of teams put rank table to JScrollPane
    (7) highlight your team in the table
    (8) put <sup> or <sub> and <sup>1</sup>&frasl;<sub>10</sub> tags in taskContent and in units
    (9) disable submit result with empty or incorrect textResult
    (10) type bod when 1
              body when 2, 3, 4
              bodů when more
    (11) Blbuvzodrnost u zadávání odpovědí
    (12) Přidat zapisování do souboru na každé akci, která mění body
 */

public class MathTournament {
    public static void main(String[] args) {
        new GraphicalUserInterface();

        /*
        FileHandler fileHandler = new FileHandler();
        fileHandler.readFile();
        */
    }
}