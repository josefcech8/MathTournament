import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalUserInterface extends JFrame {

    private int screenSizeX, screenSizeY, frameSizeX, frameSizeY;
    private Dimension screenSize;

    public GraphicalUserInterface() {
        super("Matematický turnaj");

        frameSizeX = 1000;
        frameSizeY = 600;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new CardLayout(0, 0));

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSizeX = screenSize.width;
        screenSizeY = screenSize.height;
        setBounds(screenSizeX / 2 - frameSizeX / 2, screenSizeY / 2 - frameSizeY / 2, frameSizeX, frameSizeY);

        initLoginPanel();

        setVisible(true);
    }

    //region login
    JLabel labelTitle;
    JPanel panelLogin;

    private void initLoginPanel() {
        panelLogin = new JPanel();
        getContentPane().add(panelLogin);
        panelLogin.setLayout(null);

        labelTitle = new JLabel("Matematický turnaj");
        labelTitle.setBounds(445, 20, 110, 20);
        panelLogin.add(labelTitle);

        add(panelLogin);
    }
    //endregion
}
