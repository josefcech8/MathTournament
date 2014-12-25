import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

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


        /*initLoginPanel();
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
    boolean pointArraysEqual;
    boolean[] taskIndexes = {true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    int totalPoints, taskCoefficient, taskPoints, i, pointsHolder = 0;
    int[] points = {13, 5, 30}, pointsClone;
    String teamNamesHolder;
    String[] columnNames = {"POŘADÍ", "TEAM", "BODY"};
    String[] teamNames = {"Cmyk", "Havíři", "Tutor"};

    String[] taskTitles = {"1. úloha: Meziprostorová",
                           "2. úloha",
                           "3. úloha", "4. úloha", "5. úloha", "6. úloha", "7. úloha"};
    String[] tasks = {"<html><p align=\"justify\">Paťo se náhodou dostal do paralelního vesmíru. Kolik sekund tam trvá jeden rok, pokud má 6 měsíců, měsíc má 8 týdnů, týden má 5 dnů, den má 30 hodin, hodina má 16 minut a minuta 45 sekund?</p></html>",
                      "<html><p align=\"justify\">Při zuřivém zápase jsme zničili pingpongový míček, a proto jsme šli koupit nový. V obchodě nám prodavačka řekla, že pálka je o 1000 korun dražší než míček, a navíc, že míček a pálka stojí celkem 1100 korun. Poněvadž chceme jen míček, kolik musíme zaplatit?</p></html>",
                      "<html><p align=\"justify\">Mějme dvě čísla: A a B. Čislo A získáme tak, že seřadíme následující stavy vody o stejné hmotnosti podle objemu (za normálního tlaku) od největšího po nejmenší:<br>" +
                              "1 . . . vodní pára,<br>" +
                              "2 . . . voda při teplotě 80 °C,<br>" +
                              "3 . . . led.<br>" +
                              "Čislo B dostaneme seřazením těchto délkových jednotek od nejmenši po největší:<br>" +
                              "4 . . . míle,<br>" +
                              "5 . . . palec,<br>" +
                              "6 . . . světelný rok.<br>" +
                              "Určete největši společný dělitel čísel A a B.</p></html>",
                      "<html><p align=\"justify\">Kuba s Honzou trénují na kolech na stejné trase. Kuba jezdí první kilometr, který je do kopce, obvykle rychlostí 10km/h, zatímco Honza ho zvládá rychlostí 12km/h. Druhý kilometr je už pro oba snazší, Kuba ho jezdí rychlostí 40km/h a Honza rychlostí 24km/h. Který z chlapců má vyšší průměrnou rychlost na celé trase?",

    };
    Object[][] dataRank;
    Font fontTitle, fontTotalPoints, fontTeam, fontTime;
    JLabel labelTaskTitle, labelTaskContent, labelTaskCoefficient, labelTaskPoints, labelTotalPoints, labelTime, labelTeamName;
    JTextField textResult, textUnits;
    JButton buttonTask, buttonRank, buttonRules, buttonSubmit;
    JScrollPane scrollPaneTasks, scrollPaneTableRank;
    JList listTasks;
    JTable tableRank;
    DefaultTableCellRenderer centerRenderer;
    JPanel panelTask;

    private void initTaskPanel() {
        panelTask = new JPanel();
        getContentPane().add(panelTask);
        panelTask.setLayout(null);
        panelTask.setBackground(Color.decode("#00A08A"));

        totalPoints = 0;
        taskPoints = 3;
        taskCoefficient = 5;

        buttonTask = new JButton("Úlohy");
        buttonTask.setBounds(20, 20, 100, 25);
        buttonTask.setBackground(Color.CYAN);
        buttonTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonTask.setBackground(Color.CYAN);
                buttonRank.setBackground(Color.LIGHT_GRAY);
                buttonRules.setBackground(Color.LIGHT_GRAY);

                setTaskVisibility(true);
                scrollPaneTableRank.setVisible(false);
                scrollPaneRulesContent.setVisible(false);
            }
        });
        panelTask.add(buttonTask);

        buttonRank = new JButton("Pořadí");
        buttonRank.setBounds(120, 20, 100, 25);
        buttonRank.setBackground(Color.LIGHT_GRAY);
        buttonRank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonTask.setBackground(Color.LIGHT_GRAY);
                buttonRank.setBackground(Color.CYAN);
                buttonRules.setBackground(Color.LIGHT_GRAY);

                setTaskVisibility(false);
                scrollPaneTableRank.setVisible(true);
                scrollPaneRulesContent.setVisible(false);
            }
        });
        panelTask.add(buttonRank);

        buttonRules = new JButton("Pravidla");
        buttonRules.setBounds(220, 20, 100, 25);
        buttonRules.setBackground(Color.LIGHT_GRAY);
        buttonRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonTask.setBackground(Color.LIGHT_GRAY);
                buttonRank.setBackground(Color.LIGHT_GRAY);
                buttonRules.setBackground(Color.CYAN);

                setTaskVisibility(false);
                scrollPaneTableRank.setVisible(false);
                scrollPaneRulesContent.setVisible(true);
            }
        });
        panelTask.add(buttonRules);

        fontTeam = new Font("Serif", Font.PLAIN, 20);

        labelTeamName = new JLabel("Tým: CMYK");
        labelTeamName.setBounds(390, 20, 200, 25);
        labelTeamName.setHorizontalAlignment(SwingConstants.CENTER);
        labelTeamName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelTeamName.setFont(fontTeam);
        panelTask.add(labelTeamName);

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
                    labelTaskContent.setText(tasks[listTasks.getSelectedIndex()]);
                } else {
                    labelTaskContent.setText("zámek");
                }
            }
        });

        scrollPaneTasks = new JScrollPane(listTasks);
        scrollPaneTasks.setBounds(20, 70, 220, 350);
        scrollPaneTasks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelTask.add(scrollPaneTasks);

        fontTitle = new Font("Serif", Font.BOLD, 20);

        labelTaskTitle = new JLabel(taskTitles[listTasks.getSelectedIndex()]);
        labelTaskTitle.setBounds(300, 70, 350, 30);
        labelTaskTitle.setFont(fontTitle);
        panelTask.add(labelTaskTitle);

        labelTaskCoefficient = new JLabel("násobeno 5x");
        labelTaskCoefficient.setBounds(200, 500, 80, 20);
        panelTask.add(labelTaskCoefficient);

        labelTaskPoints = new JLabel("3 body");
        labelTaskPoints.setBounds(300, 500, 80, 20);
        panelTask.add(labelTaskPoints);

        labelTaskContent = new JLabel(tasks[listTasks.getSelectedIndex()]);
        labelTaskContent.setBounds(300, 120, 370, 220);
        labelTaskContent.setVerticalAlignment(JLabel.TOP);
        panelTask.add(labelTaskContent);

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
                if(textResult.getText().equals("5184000")) {
                    totalPoints += taskCoefficient * taskPoints;
                    labelTotalPoints.setText(String.valueOf(totalPoints) + " bodů");
                    textResult.setText("");
                } else {
                    if(taskCoefficient != 0) {
                        taskCoefficient--;
                        labelTaskCoefficient.setText("násobeno " + taskCoefficient + "x");
                        textResult.setText("");
                    }
                }
            }
        });
        panelTask.add(buttonSubmit);

        fontTotalPoints = new Font("Serif", Font.BOLD, 30);

        labelTotalPoints = new JLabel("0 bodů");
        labelTotalPoints.setBounds(850, 20, 200, 30);
        labelTotalPoints.setFont(fontTotalPoints);
        panelTask.add(labelTotalPoints);

        fontTime = new Font("Serif", Font.BOLD, 23);

        labelTime = new JLabel("2:40");
        labelTime.setBounds(850, 60, 200, 30);
        labelTime.setFont(fontTime);
        panelTask.add(labelTime);

        labelMatfyz = new JLabel(new ImageIcon("mff.gif"));
        labelMatfyz.setBounds(800, 120, 150, 150);
        panelTask.add(labelMatfyz);

        /*devTool*/
        /*cleancode*/
        /*rename class*/
        /*center pictures || layout*/
        paintComponent paint1 = new paintComponent();
        paint1.setBounds(770, 290, 230, 3);
        paint1.setBackground(Color.LIGHT_GRAY);
        panelTask.add(paint1);
        paintComponent paint2 = new paintComponent();
        paint2.setBounds(770, 0, 3, 600);
        paint2.setBackground(Color.LIGHT_GRAY);
        panelTask.add(paint2);
        /*end*/

        labelBrezinky = new JLabel(new ImageIcon("brezinky.gif"));
        labelBrezinky.setBounds(800, 320, 150, 211);
        panelTask.add(labelBrezinky);

        dataRank = new Object[teamNames.length][teamNames.length];
        for(int i = 0; i < teamNames.length; i++) {
            dataRank[i][0] = i+1;
            dataRank[i][1] = teamNames[i];
            dataRank[i][2] = points[i];
        }

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        tableRank = new JTable(dataRank, columnNames) {
            public boolean isCellEditable(int data, int column) {
                return false;
            }
        };
        tableRank.setFillsViewportHeight(true);
        tableRank.setRowHeight(30);
        for(i = 0; i < teamNames.length; i++) {
            tableRank.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tableRank.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableRank.getColumnModel().getColumn(2).setPreferredWidth(20);

        sortTableRank();

        scrollPaneTableRank = new JScrollPane(tableRank);
        scrollPaneTableRank.setBounds(20, 100, 400, 300);
        scrollPaneTableRank.setVisible(false);
        panelTask.add(scrollPaneTableRank);


        labelRulesContent = new JLabel("<html><h1 align=\"center\">1. HRACÍ PLOCHA</h1>" +
                "<h2 align=\"center\">101  Rozměry hřiště</h2>" +
                "<p align=\"center\">1. Hřiště má rozměry 40 m x 20 m a je ohraničeno mantinely se zaoblenými rohy.<br>" +
                "Mantinely musí být schváleny IFF a příslušně označeny.<br>" +
                "Hřiště je obdélníkové, rozměry udávají délku x šířku.<br>" +
                "Nejmenší povolené rozměry jsou 36 m x 18 m, největší pak 44 m x 22 m.<br></p>" +
                "<h2 align=\"center\">102  Značky na hřišti</h2>" +
                "<p align=\"center\">1. Veškeré označení je provedeno čarami, 4 – 5 cm širokými, jasně viditelnou barvou.<br>" +
                "2. Středová čára a středový bod jsou vyznačeny.<br>" +
                "Středová čára je rovnoběžná s kratšími stranami hřiště a rozděluje hřiště na dvě stejné poloviny.<br>" +
                "3. Velké brankoviště s rozměry 4 m x 5 m je vyznačeno 2,85 m od kratší strany hřiště.<br>" +
                "Velké brankoviště je obdélníkové, rozměry udávají délku x šířku včetně čar.<br>" +
                "Velké brankoviště je umístěno centrálně vzhledem k dlouhým stranám hřiště.<br>" +
                "4. Malé brankoviště s rozměry 1 m x 2,5 m je vyznačeno 0,65 m před zadní čarou velkého brankoviště.</p></html>");

        scrollPaneRulesContent = new JScrollPane();
        scrollPaneRulesContent.setViewportView(labelRulesContent);
        scrollPaneRulesContent.setBounds(20, 70, 600, 400);
        scrollPaneRulesContent.setVisible(false);
        panelTask.add(scrollPaneRulesContent);

        add(panelTask);
    }

    private void sortTableRank() {
        pointArraysEqual = false;
        pointsHolder = 0;
        while(!pointArraysEqual) {
            pointArraysEqual = true;
            pointsClone = points.clone();

            for (i = 0; i < teamNames.length; i++) {
                if (i != 0 && points[i] > pointsHolder) {
                    teamNamesHolder = teamNames[i];
                    teamNames[i] = teamNames[i - 1];
                    teamNames[i - 1] = teamNamesHolder;
                    points[i - 1] = points[i];
                    points[i] = pointsHolder;
                }
                pointsHolder = points[i];
            }
            for (i = 0; i < points.length; i++) {
                if (pointsClone[i] != points[i]) {
                    pointArraysEqual = false;
                    break;
                }
            }

            for(int i = 0; i < teamNames.length; i++) {
                dataRank[i][0] = i+1;
                dataRank[i][1] = teamNames[i];
                dataRank[i][2] = points[i];
            }
        }
    }

    private void setTaskVisibility(boolean visibility) {
        scrollPaneTasks.setVisible(visibility);
        labelTaskTitle.setVisible(visibility);
        labelTaskCoefficient.setVisible(visibility);
        labelTaskPoints.setVisible(visibility);
        labelTaskContent.setVisible(visibility);
        textResult.setVisible(visibility);
        textUnits.setVisible(visibility);
        buttonSubmit.setVisible(visibility);
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

    public class paintComponent extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);
        }
    }
    //endregion

    //region 3| test
    JLabel labelTestTitle;
    JPanel panelTest;

    private void initTestPanel() {
        panelTest = new JPanel();
        getContentPane().add(panelTest);
        panelTest.setLayout(null);

        labelTestTitle = new JLabel("Test");
        labelTestTitle.setBounds(445, 20, 100, 20);
        panelTest.add(labelTestTitle);

        totalPoints = 0;
        taskPoints = 3;
        taskCoefficient = 5;

        buttonTask = new JButton("Úlohy");
        buttonTask.setBounds(20, 20, 100, 25);
        buttonTask.setBackground(Color.CYAN);
        panelTest.add(buttonTask);

        buttonRank = new JButton("Pořadí");
        buttonRank.setBounds(120, 20, 100, 25);
        buttonRank.setBackground(Color.LIGHT_GRAY);
        panelTest.add(buttonRank);

        buttonRules = new JButton("Pravidla");
        buttonRules.setBounds(220, 20, 100, 25);
        buttonRules.setBackground(Color.LIGHT_GRAY);
        panelTest.add(buttonRules);

        labelTeamName = new JLabel("Tým: CMYK");
        labelTeamName.setBounds(445, 20, 100, 20);
        panelTest.add(labelTeamName);

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
                    labelTaskContent.setText(tasks[listTasks.getSelectedIndex()]);
                } else {
                    labelTaskContent.setText("zámek");
                }
            }
        });

        scrollPaneTasks = new JScrollPane(listTasks);
        scrollPaneTasks.setBounds(20, 70, 220, 350);
        scrollPaneTasks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelTest.add(scrollPaneTasks);

        fontTitle = new Font("Serif", Font.BOLD, 20);

        labelTaskTitle = new JLabel(taskTitles[listTasks.getSelectedIndex()]);
        labelTaskTitle.setBounds(300, 70, 350, 30);
        labelTaskTitle.setFont(fontTitle);
        panelTest.add(labelTaskTitle);

        labelTaskCoefficient = new JLabel("násobeno 5x");
        labelTaskCoefficient.setBounds(200, 500, 80, 20);
        panelTest.add(labelTaskCoefficient);

        labelTaskPoints = new JLabel("3 body");
        labelTaskPoints.setBounds(300, 500, 80, 20);
        panelTest.add(labelTaskPoints);

        labelTaskContent = new JLabel(tasks[listTasks.getSelectedIndex()]);
        labelTaskContent.setBounds(300, 120, 370, 220);
        labelTaskContent.setVerticalAlignment(JLabel.TOP);
        panelTest.add(labelTaskContent);

        textResult = new JTextField("Zadejte výsledek");
        textResult.setBounds(300, 400, 200, 20);
        textResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Vzorové zadání odpovědi");
                textResult.setText("");
            }
        });
        panelTest.add(textResult);

        textUnits = new JTextField("km");
        textUnits.setBounds(500, 400, 50, 20);
        textUnits.setEditable(false);
        panelTest.add(textUnits);

        buttonSubmit = new JButton("Potvrdit");
        buttonSubmit.setBounds(570, 400, 100, 20);
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textResult.getText().equals("5184000")) {
                    totalPoints += taskCoefficient * taskPoints;
                    labelTotalPoints.setText(String.valueOf(totalPoints) + " bodů");
                    textResult.setText("");
                } else {
                    if(taskCoefficient != 0) {
                        taskCoefficient--;
                        labelTaskCoefficient.setText("násobeno " + taskCoefficient + "x");
                        textResult.setText("");
                    }
                }
            }
        });
        panelTest.add(buttonSubmit);

        fontTotalPoints = new Font("Serif", Font.BOLD, 30);

        labelTotalPoints = new JLabel("0 bodů");
        labelTotalPoints.setBounds(850, 20, 200, 30);
        labelTotalPoints.setFont(fontTotalPoints);
        panelTest.add(labelTotalPoints);

        labelTime = new JLabel("2:40");
        labelTime.setBounds(850, 60, 200, 20);
        panelTest.add(labelTime);

        labelMatfyz = new JLabel("logo MFF UK");
        labelMatfyz.setBounds(850, 150, 200, 20);
        panelTest.add(labelMatfyz);

        labelBrezinky = new JLabel("logo Brezinky");
        labelBrezinky.setBounds(850, 300, 200, 20);
        panelTest.add(labelBrezinky);

        add(panelTest);
    }
    //endregion
}
