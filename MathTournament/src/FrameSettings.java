import javax.swing.*;
import java.awt.*;

public class FrameSettings extends JFrame {

    private int screenSizeX, screenSizeY, frameSizeX, frameSizeY;
    private Dimension screenSize;

    public FrameSettings() {
        frameSizeX = 800;
        frameSizeY = 600;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSizeX = screenSize.width;
        screenSizeY = screenSize.height;
        setBounds((screenSizeX/2)-frameSizeX/2, (screenSizeY/2)-frameSizeY/2, frameSizeX, frameSizeY);

        setVisible(true);
    }
}
