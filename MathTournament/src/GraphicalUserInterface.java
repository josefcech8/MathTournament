import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicalUserInterface extends JFrame {

    /*devTools*/
    private JLabel labelMatfyz, labelBrezinky;
    /*end*/


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

        /*
        initLoginPanel();
        initRulesPanel();
        initTestPanel();
        initStartCompetition();*/
        initTaskPanel();

        setVisible(true);
    }

    //region 1| login
    JLabel labelTitle;
    JTextField textUsername;
    JPasswordField passwordPassword;
    JButton buttonLogin;
    JPanel panelLogin;

    private void initLoginPanel() {
        panelLogin = new JPanel();
        getContentPane().add(panelLogin);
        panelLogin.setLayout(null);
        panelLogin.setBackground(Color.decode("#00A08A"));

        labelTitle = new JLabel("Matematický turnaj", SwingConstants.CENTER);
        labelTitle.setBounds(0, 20, frameSizeX, 20);
        panelLogin.add(labelTitle);

        textUsername = new JTextField("username");
        textUsername.setHorizontalAlignment(JTextField.CENTER);
        textUsername.setBounds(445, 300, 110, 20);
        panelLogin.add(textUsername);

        passwordPassword = new JPasswordField();
        passwordPassword.setBounds(445, 340, 110, 20);
        panelLogin.add(passwordPassword);

        buttonLogin = new JButton("login");
        buttonLogin.setBounds(445, 390, 110, 20);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLogin.setVisible(false);
                panelRules.setVisible(true);
            }
        });
        panelLogin.add(buttonLogin);

        add(panelLogin);
    }
    //endregion

    //region 2| rules
    JLabel labelRulesTitle, labelRulesContent;
    JScrollPane scrollPaneRulesContent;
    JCheckBox checkBoxAgreement;
    JButton buttonRulesProceed;
    JPanel panelRules;

    private void initRulesPanel() {
        panelRules = new JPanel();
        getContentPane().add(panelRules);
        panelRules.setLayout(null);

        labelRulesTitle = new JLabel("Pravidla");
        labelRulesTitle.setBounds(445, 20, 50, 20);
        panelRules.add(labelRulesTitle);

        labelRulesContent = new JLabel("<html><h1>1. HRACÍ PLOCHA</h1>" +
                "<h2>101  Rozměry hřiště</h2>" +
                "1. Hřiště má rozměry 40 m x 20 m a je ohraničeno mantinely se zaoblenými rohy.<br>" +
                "Mantinely musí být schváleny IFF a příslušně označeny.<br>" +
                "Hřiště je obdélníkové, rozměry udávají délku x šířku.<br>" +
                "Nejmenší povolené rozměry jsou 36 m x 18 m, největší pak 44 m x 22 m.<br>" +
                "<h2>102  Značky na hřišti</h2>" +
                "1. Veškeré označení je provedeno čarami, 4 – 5 cm širokými, jasně viditelnou barvou.<br>" +
                "2. Středová čára a středový bod jsou vyznačeny.<br>" +
                "Středová čára je rovnoběžná s kratšími stranami hřiště a rozděluje hřiště na dvě stejné poloviny.<br>" +
                "3. Velké brankoviště s rozměry 4 m x 5 m je vyznačeno 2,85 m od kratší strany hřiště.<br>" +
                "Velké brankoviště je obdélníkové, rozměry udávají délku x šířku včetně čar.<br>" +
                "Velké brankoviště je umístěno centrálně vzhledem k dlouhým stranám hřiště.<br>" +
                "4. Malé brankoviště s rozměry 1 m x 2,5 m je vyznačeno 0,65 m před zadní čarou velkého brankoviště.</html>");

        scrollPaneRulesContent = new JScrollPane();
        scrollPaneRulesContent.setViewportView(labelRulesContent);
        scrollPaneRulesContent.setBounds(80, 100, 600, 200);
        panelRules.add(scrollPaneRulesContent);

        checkBoxAgreement = new JCheckBox("Souhlasím s pravidly soutěže");
        checkBoxAgreement.setBounds(200, 400, 200, 20);
        panelRules.add(checkBoxAgreement);

        buttonRulesProceed = new JButton("Pokračovat");
        buttonRulesProceed.setBounds(400, 400, 150, 20);
        buttonRulesProceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBoxAgreement.isSelected()) {
                    panelRules.setVisible(false);
                    panelTest.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Musíte souhlasit s pravidly soutěže", "Upozornění", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        panelRules.add(buttonRulesProceed);

        add(panelRules);
    }
    //endregion

    //region 3| test
    JLabel labelTestTitle;
    JButton buttonTestProceed;
    JPanel panelTest;

    private void initTestPanel() {
        panelTest = new JPanel();
        getContentPane().add(panelTest);
        panelTest.setLayout(null);

        labelTestTitle = new JLabel("Test");
        labelTestTitle.setBounds(445, 20, 100, 20);
        panelTest.add(labelTestTitle);

        buttonTestProceed = new JButton("Pokračovat ...");
        buttonTestProceed.setBounds(200, 200, 100, 20);
        buttonTestProceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTest.setVisible(false);
                panelStartCompetition.setVisible(true);
            }
        });
        panelTest.add(buttonTestProceed);

        add(panelTest);
    }
    //endregion

    //region 4| startCompetition
    JLabel labelStartTitle, labelStartInfo;
    JButton buttonStartCompetition;
    JPanel panelStartCompetition;

    private void initStartCompetition() {
        panelStartCompetition = new JPanel();
        getContentPane().add(panelStartCompetition);
        panelStartCompetition.setLayout(null);

        labelStartTitle = new JLabel("Zahájení");
        labelStartTitle.setBounds(445, 20, 100, 20);
        panelStartCompetition.add(labelStartTitle);

        labelStartInfo = new JLabel("<html>Po stisknutí tlačítka zahájíte<br>" +
                                    "soutěž a spustíte časomíru</html>", SwingConstants.CENTER);
        labelStartInfo.setVerticalAlignment(SwingConstants.TOP);
        labelStartInfo.setBounds(100, 200, 200, 200);
        panelStartCompetition.add(labelStartInfo);

        buttonStartCompetition = new JButton("Zahájit soutěž");
        buttonStartCompetition.setBounds(400, 200, 150, 20);
        buttonStartCompetition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelStartCompetition.setVisible(false);
                panelTask.setVisible(true);
            }
        });
        panelStartCompetition.add(buttonStartCompetition);

        add(panelStartCompetition);
    }
    //endregion

    //region 5| task
    boolean[] taskIndexes = {true, true, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    int totalPoints, taskCoefficient, taskPoints;
    String[] taskTitles = {"1. úloha: Meziprostorová", "2. úloha", "3. úloha", "4. úloha", "5. úloha", "6. úloha", "7. úloha"};
    String[] tasks = {"<html>Paťo se náhodou dostal do paralelního vesmíru. Kolik sekund tam trvá jeden rok,<br>" +
                      "pokud má 6 měsíců, měsíc má 8 týdnů, týden má 5 dnů, den má 30 hodin, hodina má 16 minut a minuta 45 sekund?</html>"};
    JLabel labelTaskTitle, labelTaskContent, labelTaskCoefficient, labelTaskPoints, labelTotalPoints, labelTime;
    JTextField textResult, textUnits;
    JButton buttonTask, buttonRank, buttonRules, buttonSubmit;
    JScrollPane scrollPaneTasks;
    JList listTasks;
    JPanel panelTask;

    private void initTaskPanel() {
        panelTask = new JPanel();
        getContentPane().add(panelTask);
        panelTask.setLayout(null);
        panelTask.setBackground(Color.decode("#00A08A"));

        totalPoints = 0;

        buttonTask = new JButton("Úlohy");
        buttonTask.setBounds(20, 20, 100, 25);
        //buttonTask.addActionListener();
        panelTask.add(buttonTask);

        buttonRank = new JButton("Pořadí");
        buttonRank.setBounds(120, 20, 100, 25);
        panelTask.add(buttonRank);

        buttonRules = new JButton("Pravidla");
        buttonRules.setBounds(220, 20, 100, 25);
        panelTask.add(buttonRules);

        listTasks = new JList(taskTitles);
        listTasks.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listTasks.setLayoutOrientation(JList.VERTICAL);
        listTasks.setVisibleRowCount(-1);
        listTasks.setSelectedIndex(0);
        listTasks.setCellRenderer(new SelectedListCellRenderer());
        listTasks.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(taskIndexes[listTasks.getSelectedIndex()]) {
                    labelTaskTitle.setText(taskTitles[listTasks.getSelectedIndex()]);
                    labelTaskContent.setText(tasks[0]);
                } else {
                    labelTaskContent.setText("zámek");
                }
            }
        });

        scrollPaneTasks = new JScrollPane(listTasks);
        scrollPaneTasks.setBounds(20, 70, 220, 350);
        scrollPaneTasks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelTask.add(scrollPaneTasks);

        labelTaskTitle = new JLabel(taskTitles[listTasks.getSelectedIndex()]);
        labelTaskTitle.setBounds(445, 70, 220, 20);
        panelTask.add(labelTaskTitle);

        labelTaskContent = new JLabel(tasks[0]);
        labelTaskContent.setBounds(300, 200, 300, 150);
        panelTask.add(labelTaskContent);

        labelTaskCoefficient = new JLabel("násobeno 5x");
        labelTaskCoefficient.setBounds(300, 110, 80, 20);
        panelTask.add(labelTaskCoefficient);

        labelTaskPoints = new JLabel("3 body");
        labelTaskPoints.setBounds(600, 110, 80, 20);
        panelTask.add(labelTaskPoints);

        textResult = new JTextField("Zadejte výsledek");
        textResult.setBounds(300, 400, 200, 20);
        textResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Vzorové zadání odpovědi");
                textResult.setText("");
            }
        });
        panelTask.add(textResult);

        textUnits = new JTextField("km");
        textUnits.setBounds(500, 400, 50, 20);
        textUnits.setEditable(false);
        panelTask.add(textUnits);

        buttonSubmit = new JButton("Potvrdit");
        buttonSubmit.setBounds(570, 400, 100, 20);
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(textResult.getText()) == 5184000) {
                    totalPoints += taskCoefficient * taskPoints;

                    labelTotalPoints.setText(String.valueOf(totalPoints) + " bodů");
                } else {
                    if(taskCoefficient != 0) {
                        taskCoefficient--;
                    }
                }
            }
        });
        panelTask.add(buttonSubmit);

        labelTotalPoints = new JLabel("0 bodů");
        labelTotalPoints.setBounds(850, 20, 200, 20);
        panelTask.add(labelTotalPoints);

        labelTime = new JLabel("2:40");
        labelTime.setBounds(850, 60, 200, 20);
        panelTask.add(labelTime);

        labelMatfyz = new JLabel("logo MFF UK");
        labelMatfyz.setBounds(850, 150, 200, 20);
        panelTask.add(labelMatfyz);

        labelBrezinky = new JLabel("logo Brezinky");
        labelBrezinky.setBounds(850, 300, 200, 20);
        panelTask.add(labelBrezinky);

        add(panelTask);
    }

    public class SelectedListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if(!taskIndexes[index]) {
                c.setEnabled(false);
            }
            return c;
        }
    }
    //endregion
}
