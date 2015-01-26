import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;

public class GraphicalUserInterface extends JFrame {

    private int screenSizeX, screenSizeY, FRAME_SIZE_X, FRAME_SIZE_Y;
    private String BACKGROUND_COLOR = "#00A08A";
    private Dimension screenSize;

    public GraphicalUserInterface() {
        super("Matematický turnaj");

        FRAME_SIZE_X = 1000;
        FRAME_SIZE_Y = 600;

        /*setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);*/
        /*devTool*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*end*/
        setResizable(false);
        getContentPane().setLayout(new CardLayout(0, 0));

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSizeX = screenSize.width;
        screenSizeY = screenSize.height;
        setBounds(screenSizeX / 2 - FRAME_SIZE_X / 2, screenSizeY / 2 - FRAME_SIZE_Y / 2, FRAME_SIZE_X, FRAME_SIZE_Y);

        /*
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showOptionDialog(null, "Chcete zavřít okno a ukončit soutěž?", "Potvrdit konec", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Ano", "Ne"}, null) == 0) {
                    dispose();
                    System.exit(0);
                }

            }
        });*/

        /*initLoginPanel();*/
        /*initRulesPanel();*/
        /*initTestPanel();*/
        initStartCompetition();
        initTaskPanel();

        setVisible(true);
    }

    //region 1| login
    private String teamName = "U";
    private String[] registeredTeams = {"CMYK", "Kolodej", "Carodej", "Univeržál jšou"}, registeredTeamsPassword = {"student314", "student14", "student34", "0"};
    private Font fontLoginTitle;
    private paintComponent horizontalLineDownforLogin, verticalLineforLogin, horizontalLineUpforLogin;
    private JLabel labelTitle, labelMatfyzforLogin, labelBrezinkyforLogin;
    private JTextField textUsername;
    private JPasswordField passwordPassword;
    private JButton buttonLogin;
    private FileHandler fileHandler = new FileHandler();
    private JPanel panelLogin;

    private void initLoginPanel() {
        panelLogin = new JPanel();
        getContentPane().add(panelLogin);
        panelLogin.setLayout(null);
        panelLogin.setBackground(Color.decode(BACKGROUND_COLOR));

        fontLoginTitle = new Font("Serif", Font.BOLD, 40);

        labelTitle = new JLabel("<html><p align=\"center\">Matematický turnaj 2015<br>ZŠ O. Březiny</p></html>", SwingConstants.CENTER);
        labelTitle.setBounds(0, 60, FRAME_SIZE_X - 230, 110);
        labelTitle.setFont(fontLoginTitle);
        labelTitle.setFocusable(true);
        panelLogin.add(labelTitle);

        textUsername = new JTextField("username");
        textUsername.setHorizontalAlignment(JTextField.CENTER);
        textUsername.setBounds(285, 270, 200, 25);
        textUsername.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textUsername.setBackground(Color.WHITE);
                textUsername.setText("");
            }
        });
        panelLogin.add(textUsername);

        passwordPassword = new JPasswordField("password");
        passwordPassword.setHorizontalAlignment(JPasswordField.CENTER);
        passwordPassword.setBounds(285, 310, 200, 25);
        passwordPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textUsername.setBackground(Color.decode("#800000"));
                for (i = 0; i < registeredTeams.length; i++) {
                    if (textUsername.getText().toLowerCase().equals(registeredTeams[i].toLowerCase())) {
                        textUsername.setBackground(Color.GREEN);
                        break;
                    }
                }
                passwordPassword.setText("");
            }
        });
        passwordPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                for (i = 0; i < registeredTeams.length; i++) {
                    if (textUsername.getText().toLowerCase().equals(registeredTeams[i].toLowerCase())) {
                        if (String.valueOf(passwordPassword.getPassword()).equals(registeredTeamsPassword[i])) {
                            teamName = textUsername.getText();
                            if (!fileHandler.readLogin(textUsername.getText()).equals("1")) {
                                fileHandler.addLogin(textUsername.getText());
                                System.out.println("Login OK");

                                /*
                                panelLogin.setVisible(false);
                                panelRules.setVisible(true);
                                */
                            }
                            break;
                        }
                    }
                }
            }
        });
        panelLogin.add(passwordPassword);

        buttonLogin = new JButton("Přihlásit");
        buttonLogin.setBounds(285, 360, 200, 25);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(i = 0; i < registeredTeams.length; i++) {
                    if(textUsername.getText().toLowerCase().equals(registeredTeams[i].toLowerCase())) {
                        if(String.valueOf(passwordPassword.getPassword()).equals(registeredTeamsPassword[i])) {
                            teamName = textUsername.getText();
                            if(fileHandler.readLogin(textUsername.getText()).equals("1")) {
                                JOptionPane.showMessageDialog(null, "Tým je již přihlášen", "Přihlášení se nezdařilo", JOptionPane.ERROR_MESSAGE);
                                textUsername.setText("username");
                                textUsername.setBackground(Color.WHITE);
                                passwordPassword.setText("password");
                            } else {
                                fileHandler.addLogin(textUsername.getText());
                                System.out.println("Login OK");

                                /*
                                panelLogin.setVisible(false);
                                panelRules.setVisible(true);
                                */
                            }
                        } else {
                            passwordPassword.setText("");
                            JOptionPane.showMessageDialog(null, "Chybné heslo!", "Přihlášení se nezdařilo", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    } else if(i  == registeredTeams.length - 1) {
                        JOptionPane.showMessageDialog(null, "Uživatel neexistuje!", "Přihlášení se nezdařilo", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panelLogin.add(buttonLogin);

        labelMatfyzforLogin = new JLabel(new ImageIcon("mff.gif"));
        labelMatfyzforLogin.setBounds(810, 130, 150, 150);
        panelLogin.add(labelMatfyzforLogin);
        labelBrezinkyforLogin = new JLabel(new ImageIcon("brezinky.gif"));
        labelBrezinkyforLogin.setBounds(810, 330, 150, 211);
        panelLogin.add(labelBrezinkyforLogin);

        horizontalLineDownforLogin = new paintComponent();
        horizontalLineDownforLogin.setBounds(770, 300, 230, 3);
        horizontalLineDownforLogin.setBackground(Color.LIGHT_GRAY);
        panelLogin.add(horizontalLineDownforLogin);
        verticalLineforLogin = new paintComponent();
        verticalLineforLogin.setBounds(770, 0, 3, 600);
        verticalLineforLogin.setBackground(Color.LIGHT_GRAY);
        panelLogin.add(verticalLineforLogin);
        horizontalLineUpforLogin = new paintComponent();
        horizontalLineUpforLogin.setBounds(770, 110, 230, 3);
        horizontalLineUpforLogin.setBackground(Color.LIGHT_GRAY);
        panelLogin.add(horizontalLineUpforLogin);

        add(panelLogin);
    }
    //endregion

    //region 2| rules
    private String dashSeparation = "<p align=\"center\">------------------------------------------------------------------------------------------------------------------------------------------------</p>";
    private String rulesContent = "<html>" +
            dashSeparation +
            "<h2 align=\"center\">Pravidla<br>" +
                                 "Matematický turnaj 2015<br>" +
                                 "ZŠ O. Březiny</h2>" +
            dashSeparation +
            "<h3 align=\"center\">1. ÚČAST V SOUTĚŽI</h3>" +
            "<p align=\"center\">Matematický turnaj je týmová soutěž žáků druhého stupně základních škol.<br>" +
                                "Obtížnost soutěže je přizpůsobená žákům 8. a 9. tříd základní školy.<br>" +
                                "<br>" +
                                "Žáci soutěží v až tříčlenných týmech.<br>" +
                                "<br>" +
                                "Soutěž bude rozdělena na dvě kategorie B (8. třída, nebo nižší) a A (9. třída).<br>" +
                                "V případě smíšených týmů kategorii určuje člen z nejvyššího ročníku." +
                                "<br></p>" +
            dashSeparation +
            "<h3 align=\"center\">2. PŘIHLÁŠENÍ</h3>" +
            "<p align=\"center\">Do soutěže se týmy přihlašují zasláním emailu na adresu matematickyturnaj@gmail.com<br>" +
                                "[název týmu, jména všech soutěžících, ročník] do ukončení přihlašování nebo do naplnění kapacity." +
                                "<br></p>" +
            dashSeparation +
            "<h3 align=\"center\">3. ZAHÁJENÍ SOUTĚŽE</h3>" +
            "<p align=\"center\">Soutěžící jsou povinni dostavit se na místo konání soutěže včas a dbát pokynů organizátorů.<br>" +
                                "<br>" +
                                "Před vlastním zahájením soutěže dostane každý tým přihlašovací údaje k aplikaci<br>" +
                                "s úlohami a bude mít 15 minut na dotazy<br>" +
                                "a seznámení se s fungováním aplikace pomocí přiloženého tutoriálu." +
                                "<br></p>" +
            dashSeparation +
            "<h3 align=\"center\">4. ŘEŠENÍ ÚLOH</h3>" +
            "<p align=\"center\">Soutěžící v týmu řeší libovolnou ze sedmi úloh, které mají na začátku k dispozici,<br>" +
                                "výsledkem je většinou nějaké číslo.<br>" +
                                "<br>" +
                                "Jakmile tým dojde k závěru, že jejich výsledek je správný,<br>" +
                                "zapíše ho do textového pole ve správném formátu a odešle ke kontrole.<br>" +
                                "Je-li předložené řešení správné, týmu se přičítají body uvedené u hlavičky příkladu.<br>" +
                                "<br>" +
                                "Počet předložení každé úlohy není nikterak omezen, s každým špatným odevzdáním<br>" +
                                "se však snižuje počet bodů za příklad až do jedničky<br>" +
                                "a po každé špatné odpovědi není možné po dobu jedné minuty odevzdávat řešení.<br>" +
                                "<br>" +
                                "U vybraných úloh je možné si za určitý bodový obnos koupit cennou nápovědu." +
                                "<br></p>" +
            dashSeparation +
            "<h3 align=\"center\">5. ODEMKNUTÍ DALŠÍCH PŘÍKLADŮ</h3>" +
            "<p align=\"center\">Za správně vyřešenou úlohu obdrží tým úlohu novou,<br>" +
                                "a to až do vyčerpání všech připravených úloh.<br>" +
                                "Celkový počet úloh je 42.<br>" +
                                "<br>" +
                                "Za 2 body je možné si odemknout libovolnou úlohu ze seznamu.<br>" +
                                "<br>" +
                                "Kupováním nápověd a odemykáním dalších příkladů se můžete s body dostat i do záporných čísel." +
                                "<br></p>" +
            dashSeparation +
            "<h3 align=\"center\">6. UKONČENÍ SOUTĚŽE</h3>" +
            "<p align=\"center\">Soutěž končí uplynutím časového limitu tří hodin.<br>" +
                                "Od této chvíle již není možné odevzdávat úlohy.</p>" +
            dashSeparation +
            "<h3 align=\"center\">7. VÍTĚZ</h3>" +
            "<p align=\"center\">Během soutěže budou v aplikaci dostupné průběžné výsledky všech týmů.<br>" +
                                "<br>" +
                                "Ve své kategorii vítězí ten tým, který dosáhl nejvyššího počtu bodů.<br>" +
                                "Při stejném počtu záleží na nejvyšším čísle vyřešené úlohy,<br>" +
                                "při rovnosti se posuzuje druhé nejvyšší číslo atd. Pokud některé týmy vyřeší úplně stejné úlohy,<br>" +
                                "určí se vítěz losováním. Stejným postupem se určí pořadí zbývajících týmů." +
                                "<br></p>" +
            dashSeparation +
            "<h3 align=\"center\">8. POMŮCKY</h3>" +
            "<p align=\"center\">Povoleny jsou běžné psací a rýsovací potřeby, vlastní zápisky, literatura,<br>" +
                                "kalkulačky, mobilní telefony, vybavení počítačové učebny a internet.<br>" +
                                "<br>" +
                                "Spolupracovat je povoleno výhradně se členy vlastního týmu.<br>" +
                                "Jakákoli jiná komunikace povede k okamžité diskvalifikaci." +
                                "<br></p>" +
            dashSeparation +
            "<h3 align=\"center\">9. STATUT PRAVIDEL</h3>" +
            "<p align=\"center\">Tato pravidla soutěže platí pro všechny zúčastněné týmy.<br>" +
                                "<br>" +
                                "Svým přihlášením do soutěže tým potvrzuje, že se s pravidly seznámil,<br>" +
                                "souhlasí s jejich zněním a zavazuje se tato pravidla dodržovat.<br>" +
                                "<br>" +
                                "Jakékoliv prohřešky proti pravidlům soutěže mohou být potrestány diskvalifikací celého týmu.<br>" +
                                "<br>" +
                                "Organizátoři si vyhrazují právo na drobné změny v pravidlech." +
                                "<br></p>" +
            dashSeparation +
            "<html>";
    private Font fontRulesTitle;
    private paintComponent horizontalLineDownforRules, verticalLineforRules, horizontalLineUpforRules;
    private JLabel labelRulesTitle, labelRulesContent, labelMatfyzforRules, labelBrezinkyforRules;
    private JScrollPane scrollPaneRulesContent;
    private JCheckBox checkBoxAgreement;
    private JButton buttonRulesProceed;
    private JPanel panelRules;

    private void initRulesPanel() {
        panelRules = new JPanel();
        getContentPane().add(panelRules);
        panelRules.setLayout(null);
        panelRules.setBackground(Color.decode(BACKGROUND_COLOR));

        fontRulesTitle = new Font("Serif", Font.PLAIN, 30);

        labelRulesTitle = new JLabel("Pravidla", SwingConstants.CENTER);
        labelRulesTitle.setBounds(0, 20, FRAME_SIZE_X - 245, 30);
        labelRulesTitle.setFont(fontRulesTitle);
        panelRules.add(labelRulesTitle);

        labelRulesContent = new JLabel(rulesContent);

        scrollPaneRulesContent = new JScrollPane();
        scrollPaneRulesContent.setViewportView(labelRulesContent);
        scrollPaneRulesContent.setBounds(85, 85, 600, 350);
        scrollPaneRulesContent.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        scrollPaneRulesContent.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelRules.add(scrollPaneRulesContent);

        checkBoxAgreement = new JCheckBox("Souhlasím s pravidly soutěže");
        checkBoxAgreement.setBackground(Color.decode(BACKGROUND_COLOR));
        checkBoxAgreement.setBounds(328, 500, 200, 25);
        panelRules.add(checkBoxAgreement);

        buttonRulesProceed = new JButton("Pokračovat");
        buttonRulesProceed.setBounds(538, 500, 150, 25);
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

        labelMatfyzforRules = new JLabel(new ImageIcon("mff.gif"));
        labelMatfyzforRules.setBounds(810, 130, 150, 150);
        panelRules.add(labelMatfyzforRules);
        labelBrezinkyforRules = new JLabel(new ImageIcon("brezinky.gif"));
        labelBrezinkyforRules.setBounds(810, 330, 150, 211);
        panelRules.add(labelBrezinkyforRules);

        horizontalLineDownforRules = new paintComponent();
        horizontalLineDownforRules.setBounds(770, 300, 230, 3);
        horizontalLineDownforRules.setBackground(Color.LIGHT_GRAY);
        panelRules.add(horizontalLineDownforRules);
        verticalLineforRules = new paintComponent();
        verticalLineforRules.setBounds(770, 0, 3, 600);
        verticalLineforRules.setBackground(Color.LIGHT_GRAY);
        panelRules.add(verticalLineforRules);
        horizontalLineUpforRules = new paintComponent();
        horizontalLineUpforRules.setBounds(770, 110, 230, 3);
        horizontalLineUpforRules.setBackground(Color.LIGHT_GRAY);
        panelRules.add(horizontalLineUpforRules);

        add(panelRules);
    }
    //endregion

    //region 4| startCompetition
    private Font fontStartCompetitionTitle;
    private paintComponent horizontalLineDownforStartCompetition, verticalLineforStartCompetition, horizontalLineUpforStartCompetition;
    private JLabel labelStartTitle, labelStartInfo, labelMatfyzforStartCompetition, labelBrezinkyforStartCompetition;
    private JButton buttonStartCompetition;
    private JPanel panelStartCompetition;

    private void initStartCompetition() {
        panelStartCompetition = new JPanel();
        getContentPane().add(panelStartCompetition);
        panelStartCompetition.setLayout(null);
        panelStartCompetition.setBackground(Color.decode(BACKGROUND_COLOR));

        fontStartCompetitionTitle = new Font("Serif", Font.PLAIN, 30);

        labelStartTitle = new JLabel("Zahájení", SwingConstants.CENTER);
        labelStartTitle.setBounds(0, 20, FRAME_SIZE_X - 245, 40);
        labelStartTitle.setFont(fontStartCompetitionTitle);
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
                timer.start();
            }
        });
        panelStartCompetition.add(buttonStartCompetition);

        labelMatfyzforStartCompetition = new JLabel(new ImageIcon("mff.gif"));
        labelMatfyzforStartCompetition.setBounds(810, 130, 150, 150);
        panelStartCompetition.add(labelMatfyzforStartCompetition);
        labelBrezinkyforStartCompetition = new JLabel(new ImageIcon("brezinky.gif"));
        labelBrezinkyforStartCompetition.setBounds(810, 330, 150, 211);
        panelStartCompetition.add(labelBrezinkyforStartCompetition);

        horizontalLineDownforStartCompetition = new paintComponent();
        horizontalLineDownforStartCompetition.setBounds(770, 300, 230, 3);
        horizontalLineDownforStartCompetition.setBackground(Color.LIGHT_GRAY);
        panelStartCompetition.add(horizontalLineDownforStartCompetition);
        verticalLineforStartCompetition = new paintComponent();
        verticalLineforStartCompetition.setBounds(770, 0, 3, 600);
        verticalLineforStartCompetition.setBackground(Color.LIGHT_GRAY);
        panelStartCompetition.add(verticalLineforStartCompetition);
        horizontalLineUpforStartCompetition = new paintComponent();
        horizontalLineUpforStartCompetition.setBounds(770, 110, 230, 3);
        horizontalLineUpforStartCompetition.setBackground(Color.LIGHT_GRAY);
        panelStartCompetition.add(horizontalLineUpforStartCompetition);

        add(panelStartCompetition);
    }
    //endregion

    //region 5| task
    private boolean pointArraysEqual, endOfCompetition;
    private int totalPoints, i, pointsHolder = 0, seconds, minutes, hours, triangleMathTextSizeX, triangleMathTextSizeY, penaltyTime, teamNumber = 12;
    private int[] points = new int[teamNumber], pointsClone;
    private String teamNamesHolder, triangleMathResult, fileOutputBase, fileMessage;
    private String[] columnNames = {"POŘADÍ", "TEAM", "BODY"};
    private String[] teamNames = new String[teamNumber];
    private String[] fileTeam, fileOutput;
    private String[] taskTitles = {"1. úloha: Meziprostorová",
                           "2. úloha: Jdeme na ping-pong!",
                           "3. úloha: Velké D",
                           "4. úloha: Cyklisté",
                           "5. úloha: Rýsujeme",
                           "6. úloha: Trojúhelníková matematika",
                           "7. úloha: Těžcí manželé",
                           "8. úloha: Běh alejí",
                           "9. úloha: Napouštění vany",
                           "10. úloha: Jedničky a nuly",
                           "11. úloha: Sedačka",
                           "12. úloha: Dožeňte Asterixe!",
                           "13. úloha: Stavebnice",
                           "14. úloha: Hrom aby do toho praštil",
                           "15. úloha: Dobře fungující systém",
                           "16. úloha: Těžký příbor",
                           "17. úloha: Komplikovaný obvod",
                           "18. úloha: Čokoládová hvězda",
                           "19. úloha: Horolezec amatér",
                           "20. úloha: Lepicí páska",
                           "21. úloha: Zaháníme nudu",
                           "22. úloha: Houpačka",
                           "23. úloha: Ten nejmenší",
                           "24. úloha: Teplé mléko",
                           "25. úloha: Karimatka",
                           "26. úloha: Zrcadla",
                           "27. úloha: Dvojhvězda",
                           "28. úloha: Maturitní",
                           "29. úloha: Rezistory na sto způsobů",
                           "30. úloha: Zasolené moře",
                           "31. úloha: Jedeme na Náboj!",
                           "32. úloha: Pečeme dort!",
                           "33. úloha: Autohydraulika",
                           "34. úloha: Faktoriál",
                           "35. úloha: Slévání",
                           "36. úloha: Energeťák",
                           "37. úloha: Úhelná",
                           "38. úloha: Stěhování",
                           "39. úloha: Mocninná",
                           "40. úloha: Horská dráha",
                           "41. úloha: Obraz",
                           "42. úloha: Kámen"
    };
    private String[] tasks = {"<html><p align=\"justify\">Paťo se náhodou dostal do paralelního vesmíru. Kolik sekund tam trvá jeden rok, pokud má 6 měsíců, měsíc má 8 týdnů, týden má 5 dnů, den má 30 hodin, hodina má 16 minut a minuta 45 sekund?</p></html>",
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
                      "<html><p align=\"justify\">Kuba trénuje na kole. První kilometr, který je do kopce jezdí rychlostí 10 km/h. Druhý kilometr je už snazší. Kuba ho jezdí rychlostí 40 km/h. Jakou průmernou rychlostí jede Kuba na celé trase.</p>",
                      "<html><p align=\"justify\">Vyberte obrázek, ve kterém je výslednice sil dvou černých a jedné modré nulová.</p>",
                      "<html><p align=\"justify\">Doplňte do vrcholů trojúhelníku a na středy jeho stran čísla 1 až 6 tak, že součet čísel na každé straně bude vždy 9. Každé číslo použijte jenom jednou.</p>",
                      "<html><p align=\"justify\">Manželé Novákovi si na Nový rok řekli, že nejsou zrovna hubení a mohli by trochu shodit. Jejich cílem bylo zhubnout alespoň 10% své původní společné hmotnosti. Paní Zita Nováková měla na Nový rok hmotnost mZ = 80 kg a její manžel Jakub Novák mJ = 120 kg. Ke konci roku se opět zvážili a zjistili, že paní Nováková sice zhubla o 15%, ale její manžel jen o 5%. O kolik kilogramů více/méně zhubli oba dohromady oproti jejich novoročnímu slibu?</p>",
                      "<html><p align=\"justify\">Lenka si šla jako každé ráno zaběhat, až se dostala do aleje rovnoměrně vysázených stromů. Od prvního stromu k devátému doběhla za osmnáct sekund. Za jak dlouho s takovou rychlostí doběhne od prvního stromu k šedesátému pátému?</p>",
                      "<html><p align=\"justify\">Petrova vana má dva kohoutky, jeden na studenou a jeden na úplně horkou vodou. Vana se napustí studenou vodou za 4 minuty, ale horkou vodou až za 12 minut. Petr odpozoroval, že nejlepší teplotu na horkou koupel má vana tehdy, když nechá oba kohoutky – s horkou i studenou vodou – úplně otevřené. Za jak dlouho se Petrova vana napustí v tomto případě?</p>",
                      "<html><p align=\"justify\">Najděte nejmenší číslo zapsané jen číslicemi 0 a 1, které je beze zbytku dělitelné součinem tří nejmenších prvočísel.</p>",
                      "<html><p align=\"justify\">Na rohovou sedačku tvaru písmene „L“ položíme šest shodných čtvercových polštářů, a to tak, že na každém konci sedačky je jeden a též v rohu je jeden. Všech šest polštářů je umístěno těsně vedle sebe. Jaké poměry délek stran může mít sedačka?</p>",
                      "<html><p align=\"justify\">Z Břeclavi vyjíždí nákladní auto s obrázkem Asterixe stálou rychlostí 60km/h. O půl hodiny později za ním vyjíždí ve stejném směru rodinka s Fandou v autě, které řídí Fandův tatínek, rychlostí 80km/h. V jaké vzdálenosti od Břeclavi začne Fanda jásat, že právě předjíždí Asterixe?</p>",
                      "<html><p align=\"justify\">Arnošt má rád stavebnice. Posledně vzal svou nejnovější stavebnici sestávající ze 192 dřevěných kostek a postavil z nich mrakodrap, tedy kvádr s podstavou 4 × 6 kostek a výškou 8 kostek. Nelíbila se mu však barva jeho mrakodrapu, tak vzal fix a všechny stěny tohoto kvádru (kromě dolní podstavy) nabarvil na modro. Kolik stěn jednotlivých kostiček zůstane neobarvených?</p>",
                      "<html><p align=\"justify\">Jaká je rychlost zvuku, jestliže Lukáš slyšel hrom o 3 s později, než Terka, když stojí vůči bouřce jako na obrázku?</p>",
                      "<html><p align=\"justify\">Čtyři dělníci postupně přehazují lopatou písek z jámy na hromadu, z hromady do kolečka, z kolečka do kbelíku a z kbelíku do míchačky. Při prvním úkonu se během přehazování vysype 20% písku, při dalším opět 20%, při třetím 50% a do míchačky se poslední dělník trefí pouze se 40% úspěšností. Kolik procent písku z jámy tato čtveřice přepraví až do míchačky?</p>",
                      "<html><p align=\"justify\">Kolik váží celý příbor, jestliže vidlička váží 60 g? Hmotnosti pák a kloubů zanedbejte.</p>",
                      "<html><p align=\"justify\">Na obrázku vidíte elektrický obvod. Skládá se ze dvou rezistorů, z nichž každý má elektrický odpor R = 3Ω a jsou připojené ke zdroji stejnosměrného napětí o velikosti U = 2V. Jaký proud I prochází zdrojem napětí?</p>",
                      "<html><p align=\"justify\">Ve velmi vzdálené planetární soustavě létají okolo hvězdy Orion tři vesmírné lodě po kruhových drahách. Poloměr dráhy nejvnitřnější lodi je r1 = 150 000km, prostřední lodi r2 = 200 000km a nejvzdálenější lodi r3 = 250 000km. Všechny lodě letí díky svému pohonu na antihmotu shodnou rychlostí v = 50 000km/rok. Na začátku se všechny lodě spolu s centrální hvězdou nacházejí v jedné přímce. Kolikrát oběhne nejvzdálenější loď hvězdu Orion, než se lodě a hvězda opět setkají v jedné přímce?</p>",
                      "<html><p align=\"justify\">Dan se rozhodl, že si postaví v podkrovním pokojíčku, který je široký s = 6m a dlouhý l = 3m, na jednu ze šikmých stěn horolezeckou stěnu. Kolik metrů čtverečních má k dispozici, když řez střechou je má tvar rovnoramenného trojúhelníku a nejvyšší bod pokoje je v kolmé vzdálenosti od podlahy vzdálený h = 4m?</p>",
                      "<html><p align=\"justify\">Na stole máme nalepeno 0,5m lepicí pásky. Vezmeme jeden její konec a začneme ho strhávat rychlostí v = 4 cm/s (rychlost ruky). Za jakou dobu odlepíme celou pásku?</p>",
                      "<html><p align=\"justify\">Pavel se při hodině nudil, proto si prohledal školní tašku a objevil tam obyčejnou hrací kostku. Začal s ní házet a pokaždé si zapsal, kolik hodil. Všiml si, že jeho první hod byl dvakrát větší než druhý, který byl však třetinový vůči třetímu hodu. Počtvrté hodil tolik, kolik byl součet prvního a druhého hodu, a pátým pokusem hodil polovinu toho, co se mu povedlo třetím hodem. Nakonec všech pět hodů sečetl. Víte, jaké číslo mu vyšlo?</p>",
                      "<html><p align=\"justify\">Bráškové Pepa a Pavel si chtějí postavit houpačku z lehkého prkna dlouhého 4,2m a polena. Když si vše připraví, snaží se ji dát do rovnováhy. V jaké vzdálenosti od Pepy musí poleno umístit, když Pepa váží 24 kg, Pavel 18 kg, a oba sedí na koncích prkna?</p>",
                      "<html><p align=\"justify\">Pro pravoúhlý rovnoběžník s celočíselnými délkami stran platí, že číselná hodnota jeho obsahu je stejná jako hodnota jeho obvodu. Nalezněte rozměry tohoto čtyřúhelníku, který vlastnost výše splňuje a jeho obsah je nejmenší.</p>",
                      "<html><p align=\"justify\">Máme chuť na hrnek teplého mléka, a tak si jeden připravíme. Mléko o objemu V = 0,25 ℓ má teplotu t0 = 20 ◦C, a chceme si ho ohřát na teplotu t1 = 42 ◦C. Po ruce máme pouze rychlovarnou konvici. Připojíme ji k elektrickému zdroji, který do konvice přivádí proud I = 3,9A při napětí U = 220V. Jak dlouho musíme čekat, než se nám mléko ohřeje? Hustota mléka je stejná jako hustota vody, měrná tepelná kapacita je c = 3 900 J/(kg·◦C). Tepelné ztráty zanedbejte.</p>",
                      "<html><p align=\"justify\">Kuba jede na tábor a bere si svoji karimatku, která má tvar kvádru o rozměrech délky l = 180 cm, šířky d = 50 cm a výšky h = 0,7 cm. Kuba si karimatku natěsno sroluje podél delší strany l do tvaru válce. Jaký bude poloměr tohoto válce? Zanedbejte „zub“, který vznikne u konce karimatky. Za PI dosaďte 3,14.</p>",
                      "<html><p align=\"justify\">Ve vlakových kupé jsou dvě zrcadla umístěna proti sobě. To má za následek, že při pohledu do jednoho ze zrcadel mírně zboku vidíme spousty obrazů své tváře: první obraz v zrcadle, na který se díváme, druhý jako obraz obrazu obrazu, třetí jako obraz obrazu obrazu obrazu obrazu a tak dále. V jaké zdánlivé vzdálenosti vidíme druhý pozorovaný obraz, pokud je kupé široké 2m a my stojíme přesně v jeho středu?</p>",
                      "<html><p align=\"justify\">Ondra na noční obloze pozoruje dvojhvězdu, o které si myslí, že je od Země vzdálena 8,24 parseků (pc). Obě složky dvojhvězdy pozoruje pod úhlem 1′′. Jak daleko jsou od sebe vzdáleny složky dvojhvězdy ve skutečnosti? Vzdálenost vyjádřete v astronomických jednotkách (AU). Tip: jeden parsek je vzdálenost, ze které je jedna astronomická jednotka pozorovaná pod úhlem 1′′.</p>",
                      "<html><p align=\"justify\">Tři plechovky o poloměru podstavy r = 5 cm postavíme těsně k sobě tak, aby středy jejich podstav tvořily rovnostranný trojúhelník. Jak dlouhou potravinovou fólii budeme potřebovat, abychom plechovky obmotali jednou kolem dokola?</p>",
                      "<html><p align=\"justify\">Paťo má tři rezistory s odporem R = 1Ω. Paťo rezistory nějak zapojil (ale tak, aby všemi rezistory tekl proud), zapsal si hodnotu výsledného odporu a následně rezistory zapojil jiným způsobem (aby měly jiný odpor). Takto si zapsal hodnoty všech možných výsledných odporů zapojení a výsledky sečetl. Jakou hodnotu dostal?</p>",
                      "<html><p align=\"justify\">Kolik soli bychom museli nasypat do Baltského moře, aby mělo stejnou salinitu (slanost) jako Rudé moře? Uvažujte rozlohu Baltského moře 400 000km2 a jeho střední hloubku 50m. Průměrná salinita Baltského moře je 10h, Rudého moře 40h (salinita 1h představuje 1 g soli na 1 ℓ vody).</p>",
                      "<html><p align=\"justify\">Auto jede hodinu rychlostí v. Pak zrychlí a další půlhodinu jede rychlostí 2v. Po příjezdu do cíle cesty řidič zjistil, že jeho průměrná rychlost je o 20km/h vyšší, než rychlost v. Jaká byla jeho průměrná rychlost vp?</p>",
                      "<html><p align=\"justify\">Radčina nejlepší kamarádka má narozeniny, a tak se Radka rozhodla, že jí upeče dort. Na dort si připravila těsto, ze kterého vytvarovala kruh o poloměru r. Jenže pak si vzpomněla, že chtěla udělat čtvercový dort, a tak z již připraveného kruhu vykrojila největší možný čtverec, ze kterého pak dort upekla. Jak velká část z původního množství těsta Radce zbyla?</p>",
                      "<html><p align=\"justify\">Simča si nově pořídila hydraulické zařízení, které se skládá ze dvou propojených pístů s plochami 100 cm2 a 1 000 cm2. Na větší píst umístila měřicí přístroj o hmotnosti 250 kg a zjistila, že zvedat ho pomalu do výšky je brnkačka. Jakou práci Simča při zvedání vykonala, pokud přístroj zvedla do výšky 2m?</p>",
                      "<html><p align=\"justify\">Kolik nul na konci má ve svém dekadickém zápise číslo, které dostaneme vynásobením všech čísel od 1 do 100?</p>",
                      "<html><p align=\"justify\">Bronz je slitina cínu a mědi v hmotnostním poměru 1 : 3. Víme, že hustota cínu je přibližně ϱSn = 7 kg/dm3 a hustota mědi ϱCu = 9 kg/dm3. Jaký bude poměr objemů cínu a mědi VSn/VCu potřebných na výrobu m = 1 kg bronzu?</p>",
                      "<html><p align=\"justify\">Znavený turista se zastavil v hospodě u cesty a koupil si chlazený nápoj. Nápoj má teplotu t0 = 7 ◦C a objem V = 250mℓ, jeho energetická hodnota je \u03B5 = 1 000 kJ/kg. Svými vlastnostmi se nápoj blíží vodě, má tedy hustotu ϱ = 1 kg/ℓ a měrnou tepelnou kapacitu c = 4 kJ/(kg·◦C). Kolik energie turista z nápoje získá, jestliže ho po vypití ve svém těle zahřeje na t1 = 37 ◦C?</p>",
                      "<html><p align=\"justify\">Lenka si namalovala rovnoramenný trojúhelník ABC a s překvapením zjistila, že na jeho ramenech AB, resp. AC, lze najít body P, resp. Q, takové, že |BC| = |CP| = |PQ| = |QA|. Určete velikost úhlu ∡BAC.</p>",
                      "<html><p align=\"justify\">V obývacím pokoji máme skříň o hmotnosti m = 50 kg a šířce 1,2m a chceme přestěhovat její střed o 4m dále. Mezi původní a novou polohou skříně jsou dva metry koberce a poté dva metry lina. Třecí koeficient mezi skříní a kobercem je f1 = 1,0 a mezi skříní a linem f2 = 0,5. Vypočítejte jakou u toho vykonáme práci. Za gravitační zrychlení dosaďte 10 m/s^2.</p>",
                      "<html><p align=\"justify\">Najděte tři po sobě jdoucí přirozená čísla, která v součtu dávají pátou mocninu některého přirozeného čísla.</p>",
                      "<html><p align=\"justify\">V lunaparku mají novou horskou dráhu. Na začátku jízdy je vozíček na vrcholu stoupání ve výšce h = 20m, ze kterého následně sjede dolů. Dole je rovinka dlouhá s = 200m, na které vozíček brzdí s konstantním zpomalením. S jakým zpomalením (záporným zrychlením) vozíček brzdí, jestliže zastaví přesně na konci rovinky? Odporové síly zanedbejte.</p>",
                      "<html><p align=\"justify\">Kubo si chce doma zavěsit obraz. Má jeden hřebík a jeden provaz, který praskne, pokud je na něj působící síla větší než T = 100N. Jaký nejtěžší obdélníkový obraz si Kubo může pověsit na provaz a hřebík, když provaz a obraz svírají úhel \u000B = 30◦?</p>",
                      "<html><p align=\"justify\">Ve válcovém poháru naplněném vodou, který má plochu podstavy 200 cm2, se vznáší kostka ledu. Vznáší se, neboť je v ledu zamrznutý kamínek o hmotnosti 100 g a hustotě 5 000 kg/m3. Časem se led rozpustí a kamínek klesne na dno. Co se stane s hladinou vody? Klesne, stoupne, nebo se nezmění? Pokud se změní, tak o kolik?</p>",
    };
    private Object[][] dataRank;
    private Timer timer, penaltyTimer;
    private Font fontTitle, fontTotalPoints, fontTeam, fontTime, fontTaskPoints, fontFinish;
    private JLabel labelTaskTitle, labelTaskContent, labelTaskPoints, labelTotalPoints, labelTime, labelTeamName, labelResultFormat, labelThunder, labelCircuit, labelMountaineer, labelCheck, labelLock, labelEndOfCompetition, labelFinish, labelMatfyz, labelBrezinky;
    private JLabel[] labelVector;
    private NumberFormatter numberFormatter;
    private JTextField textUnits, textResult;
    private JFormattedTextField[] textTriangleMath;
    private JButton buttonTask, buttonRank, buttonRules, buttonSubmit, buttonHelp, buttonTaskBuy;
    private JScrollPane scrollPaneTasks, scrollPaneTableRank;
    private JList listTasks;
    private JTable tableRank;
    private paintComponent triangle, horizontalLineDown, horizontalLineUp, verticalLine;
    private DefaultTableCellRenderer centerRenderer;
    private ResultEvaluation resultEvaluation;
    private VectorPressedAdapter vectorPressedAdapter;
    private JPanel panelTask;

    private void initTaskPanel() {
        panelTask = new JPanel();
        getContentPane().add(panelTask);
        panelTask.setLayout(null);
        panelTask.setBackground(Color.decode(BACKGROUND_COLOR));

        endOfCompetition = false;
        totalPoints = 0;

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
                setTaskMode(listTasks.getSelectedIndex());
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

                getFileData();
                sortTableRank();

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

        fontTeam = new Font("Comic Sans", Font.PLAIN, 15);

        labelTeamName = new JLabel("Tým: " + teamName);
        labelTeamName.setBounds(390, 20, 200, 25);
        labelTeamName.setHorizontalAlignment(SwingConstants.CENTER);
        labelTeamName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelTeamName.setFont(fontTeam);
        panelTask.add(labelTeamName);

        resultEvaluation = new ResultEvaluation();

        listTasks = new JList(taskTitles);
        listTasks.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listTasks.setLayoutOrientation(JList.VERTICAL);
        listTasks.setVisibleRowCount(-1);
        listTasks.setSelectedIndex(0);
        listTasks.setCellRenderer(new SelectedListCellRenderer());
        listTasks.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(resultEvaluation.getTaskState(listTasks.getSelectedIndex()) == 2 || resultEvaluation.getTaskState(listTasks.getSelectedIndex()) == 1) {
                    labelTaskContent.setText(tasks[listTasks.getSelectedIndex()]);
                    textUnits.setText(resultEvaluation.getUnits(listTasks.getSelectedIndex()));
                    labelResultFormat.setText(resultEvaluation.getResultFormat(listTasks.getSelectedIndex()));
                    if(resultEvaluation.getHelpPoints(listTasks.getSelectedIndex()) != 0) {
                        buttonHelp.setEnabled(true);
                        if(!resultEvaluation.getHelpTextAvailable(listTasks.getSelectedIndex())) {
                            buttonHelp.setText("Nápověda (" + resultEvaluation.getHelpPoints(listTasks.getSelectedIndex()) + " " + resultEvaluation.getPointsTextFormat(Math.abs(resultEvaluation.getHelpPoints(listTasks.getSelectedIndex()))) + ")");
                            buttonHelp.setBackground(Color.LIGHT_GRAY);
                        }
                        else {
                            buttonHelp.setText("Zobrazit nápovědu");
                            buttonHelp.setBackground(Color.GREEN);
                        }
                    } else {
                        buttonHelp.setEnabled(false);
                        buttonHelp.setText("Bez nápovědy");
                        buttonHelp.setBackground(Color.LIGHT_GRAY);
                    }
                }
                labelTaskTitle.setText(taskTitles[listTasks.getSelectedIndex()]);
                labelTaskPoints.setText(String.valueOf("[" + resultEvaluation.getTaskPoints(listTasks.getSelectedIndex())) + " " + resultEvaluation.getPointsTextFormat(resultEvaluation.getTaskPoints(listTasks.getSelectedIndex())) + "]");
                setTaskVisibility(true);
                setTaskMode(listTasks.getSelectedIndex());
            }
        });

        scrollPaneTasks = new JScrollPane(listTasks);
        scrollPaneTasks.setBounds(20, 70, 230, 350);
        scrollPaneTasks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelTask.add(scrollPaneTasks);

        fontTitle = new Font("Serif", Font.BOLD, 20);

        labelTaskTitle = new JLabel(taskTitles[listTasks.getSelectedIndex()]);
        labelTaskTitle.setBounds(300, 70, 350, 30);
        labelTaskTitle.setFont(fontTitle);
        panelTask.add(labelTaskTitle);

        fontTaskPoints = new Font("Serif", Font.BOLD, 20);

        labelTaskPoints = new JLabel("[" + resultEvaluation.getTaskPoints(listTasks.getSelectedIndex()) + " " + resultEvaluation.getPointsTextFormat(resultEvaluation.getTaskPoints(listTasks.getSelectedIndex())) + "]");
        labelTaskPoints.setBounds(660, 70, 80, 30);
        labelTaskPoints.setFont(fontTaskPoints);
        panelTask.add(labelTaskPoints);

        labelTaskContent = new JLabel(tasks[listTasks.getSelectedIndex()]);
        labelTaskContent.setBounds(300, 120, 370, 220);
        labelTaskContent.setVerticalAlignment(JLabel.TOP);
        panelTask.add(labelTaskContent);

        textResult = new JTextField();
        textResult.setBounds(300, 400, 200, 20);
        textResult.setText("Zadejte výsledek");
        textResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textResult.setText("");
            }
        });
        panelTask.add(textResult);

        textUnits = new JTextField(resultEvaluation.getUnits(listTasks.getSelectedIndex()));
        textUnits.setBounds(500, 400, 50, 20);
        textUnits.setHorizontalAlignment(JTextField.CENTER);
        textUnits.setEditable(false);
        panelTask.add(textUnits);

        buttonSubmit = new JButton("Potvrdit");
        buttonSubmit.setBounds(570, 400, 100, 20);
        buttonSubmit.setBackground(Color.LIGHT_GRAY);
        buttonSubmit.setForeground(Color.BLACK);
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (resultEvaluation.getEvaluation(listTasks.getSelectedIndex(), textResult.getText())) {
                        totalPoints += resultEvaluation.getTaskPoints(listTasks.getSelectedIndex());
                        labelTotalPoints.setText(String.valueOf(totalPoints) + " " + resultEvaluation.getPointsTextFormat(totalPoints));
                        fileMessage = "(vyřešení příkladu " + listTasks.getSelectedIndex() + ")";
                        fileHandler.addRecords(teamName, totalPoints, fileMessage);
                        resultEvaluation.setTaskState(listTasks.getSelectedIndex(), 3);
                        resultEvaluation.setNextTaskUnlock(listTasks.getSelectedIndex());
                        setTaskMode(listTasks.getSelectedIndex());
                    } else {
                        if (resultEvaluation.getTaskPoints(listTasks.getSelectedIndex()) != 1) {
                            resultEvaluation.setTaskPoints(listTasks.getSelectedIndex());
                            labelTaskPoints.setText("[" + resultEvaluation.getTaskPoints(listTasks.getSelectedIndex()) + " " + resultEvaluation.getPointsTextFormat(resultEvaluation.getTaskPoints(listTasks.getSelectedIndex())) + "]");
                        }
                        penaltyTime = 60;
                        penaltyTimer = new Timer(1000, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    buttonSubmit.setEnabled(false);
                                    buttonSubmit.setBackground(Color.decode("#800000"));
                                    buttonSubmit.setForeground(Color.WHITE);
                                    penaltyTime--;
                                    buttonSubmit.setText("čekejte " + String.valueOf(penaltyTime) + "''");
                                    if (penaltyTime == 0) {
                                        penaltyTimer.stop();
                                        buttonSubmit.setEnabled(true);
                                        buttonSubmit.setBackground(Color.LIGHT_GRAY);
                                        buttonSubmit.setForeground(Color.BLACK);
                                        buttonSubmit.setText("Potvrdit");
                                    }
                                }
                        });
                        penaltyTimer.start();
                    }
                    textResult.setText("");
                    setTaskMode(listTasks.getSelectedIndex());
                } catch (Exception e1) {}
            }
        });
        panelTask.add(buttonSubmit);


        fontTotalPoints = new Font("Serif", Font.BOLD, 30);

        labelTotalPoints = new JLabel(totalPoints + " " + resultEvaluation.getPointsTextFormat(totalPoints));
        labelTotalPoints.setBounds(833, 20, 105, 35);
        labelTotalPoints.setHorizontalAlignment(SwingConstants.CENTER);
        labelTotalPoints.setFont(fontTotalPoints);
        panelTask.add(labelTotalPoints);

        fontTime = new Font("Serif", Font.BOLD, 23);

        labelTime = new JLabel("time error");
        labelTime.setBounds(840, 60, 90, 30);
        labelTime.setFont(fontTime);
        labelTime.setHorizontalAlignment(SwingConstants.CENTER);
        setCountdown();
        panelTask.add(labelTime);

        buttonHelp = new JButton("Bez nápovědy");
        buttonHelp.setEnabled(false);
        buttonHelp.setBounds(20, 470, 230, 60);
        buttonHelp.setBackground(Color.LIGHT_GRAY);
        buttonHelp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (resultEvaluation.getHelpPoints(listTasks.getSelectedIndex()) != 0) {
                    if (resultEvaluation.getHelpTextAvailable(listTasks.getSelectedIndex()))
                        JOptionPane.showMessageDialog(null, "<html><p align=\"justify\">" + resultEvaluation.getHelpText(listTasks.getSelectedIndex()) + "</p></html>", "Nápověda", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        totalPoints += resultEvaluation.getHelpPoints(listTasks.getSelectedIndex());
                        fileMessage = "(použití nápovědy " + listTasks.getSelectedIndex() + ")";
                        fileHandler.addRecords(teamName, totalPoints, fileMessage);
                        resultEvaluation.setHelpTextAvailable(listTasks.getSelectedIndex());
                        labelTotalPoints.setText(String.valueOf(totalPoints) + " " + resultEvaluation.getPointsTextFormat(totalPoints));
                        buttonHelp.setText("Zobrazit nápovědu");
                        buttonHelp.setBackground(Color.GREEN);

                    }
                }
            }
        });
        panelTask.add(buttonHelp);

        labelResultFormat = new JLabel(resultEvaluation.getResultFormat(listTasks.getSelectedIndex()));
        labelResultFormat.setBounds(300, 415, 370, 60);
        panelTask.add(labelResultFormat);

        labelMatfyz = new JLabel(new ImageIcon("mff.gif"));
        labelMatfyz.setBounds(810, 130, 150, 150);
        panelTask.add(labelMatfyz);
        labelBrezinky = new JLabel(new ImageIcon("brezinky.gif"));
        labelBrezinky.setBounds(810, 330, 150, 211);
        panelTask.add(labelBrezinky);

        horizontalLineDown = new paintComponent();
        horizontalLineDown.setBounds(770, 300, 230, 3);
        horizontalLineDown.setBackground(Color.LIGHT_GRAY);
        panelTask.add(horizontalLineDown);
        verticalLine = new paintComponent();
        verticalLine.setBounds(770, 0, 3, 600);
        verticalLine.setBackground(Color.LIGHT_GRAY);
        panelTask.add(verticalLine);
        horizontalLineUp = new paintComponent();
        horizontalLineUp.setBounds(770, 110, 230, 3);
        horizontalLineUp.setBackground(Color.LIGHT_GRAY);
        panelTask.add(horizontalLineUp);

        getFileData();

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
        for(i = 0; i < columnNames.length; i++) {
            tableRank.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        tableRank.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableRank.getColumnModel().getColumn(2).setPreferredWidth(20);

        sortTableRank();

        scrollPaneTableRank = new JScrollPane(tableRank);
        scrollPaneTableRank.setBounds(85, 70, 600, 460);
        scrollPaneTableRank.setVisible(false);
        panelTask.add(scrollPaneTableRank);

        labelRulesContent = new JLabel(rulesContent);

        scrollPaneRulesContent = new JScrollPane();
        scrollPaneRulesContent.setViewportView(labelRulesContent);
        scrollPaneRulesContent.setBounds(85, 70, 600, 460);
        scrollPaneRulesContent.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        scrollPaneRulesContent.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneRulesContent.getVerticalScrollBar().setUnitIncrement(16);
        scrollPaneRulesContent.setVisible(false);
        panelTask.add(scrollPaneRulesContent);

        labelVector = new JLabel[4];
        for(i = 0; i < labelVector.length; i++) {
            labelVector[i] = new JLabel();
        }
        labelVector[0].setIcon(new ImageIcon("vector_1.gif"));
        labelVector[0].setBounds(400, 175, 80, 80);
        labelVector[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textResult.setText("1");
                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setBorder(null);
                    if(i == 0)
                        labelVector[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                }
            }
        });
        labelVector[1].setIcon(new ImageIcon("vector_2.gif"));
        labelVector[1].setBounds(500, 175, 80, 80);
        labelVector[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textResult.setText("2");
                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setBorder(null);
                    if(i == 1)
                        labelVector[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                }
            }
        });
        labelVector[2].setIcon(new ImageIcon("vector_3.gif"));
        labelVector[2].setBounds(400, 270, 80, 80);
        labelVector[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textResult.setText("3");
                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setBorder(null);
                    if(i == 2)
                        labelVector[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                }
            }
        });
        labelVector[3].setText("<html><p align=\"center\">Žádná možnost nevyhovuje</p></html>");
        labelVector[3].setBounds(500, 270, 80, 80);
        labelVector[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                textResult.setText("4");
                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setBorder(null);
                    if(i == 3)
                        labelVector[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                }
            }
        });

        for(i = 0; i < labelVector.length; i++) {
            labelVector[i].setVisible(false);
            panelTask.add(labelVector[i]);
        }

        triangleMathTextSizeX = 30;
        triangleMathTextSizeY = 25;
        numberFormatter = new NumberFormatter();
        numberFormatter.setAllowsInvalid(false);
        textTriangleMath = new JFormattedTextField[6];
        for(i = 0; i < textTriangleMath.length; i++) {
            textTriangleMath[i] = new JFormattedTextField(numberFormatter);
            textTriangleMath[i].setHorizontalAlignment(JTextField.CENTER);
            textTriangleMath[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        triangleMathResult = "";
                        for (i = 0; i < textTriangleMath.length; i++) {
                            if (textTriangleMath[i].hasFocus()) {
                                textTriangleMath[i].setText(String.valueOf(e.getKeyChar()));
                                if (e.getKeyChar() == '0') {
                                    textTriangleMath[i].setText("1");
                                } else if (Integer.parseInt(String.valueOf(e.getKeyChar())) > 6) {
                                    textTriangleMath[i].setText("6");
                                }
                            }
                            triangleMathResult += textTriangleMath[i].getText() + "_";
                        }
                        textResult.setText(triangleMathResult);
                    } catch(Exception e1) {}
                }
            });
        }
        textTriangleMath[0].setBounds(365, 345, triangleMathTextSizeX, triangleMathTextSizeY);
        textTriangleMath[1].setBounds(470, 345, triangleMathTextSizeX, triangleMathTextSizeY);
        textTriangleMath[2].setBounds(575, 345, triangleMathTextSizeX, triangleMathTextSizeY);
        textTriangleMath[3].setBounds(535, 260, triangleMathTextSizeX, triangleMathTextSizeY);
        textTriangleMath[4].setBounds(470, 190, triangleMathTextSizeX, triangleMathTextSizeY);
        textTriangleMath[5].setBounds(405, 265, triangleMathTextSizeX, triangleMathTextSizeY);
        for(i = 0; i < textTriangleMath.length; i++) {
            textTriangleMath[i].setVisible(false);
            panelTask.add(textTriangleMath[i]);
        }

        triangle = new paintComponent();
        triangle.setBounds(300, 200, 370, 170);
        triangle.setBackground(Color.decode(BACKGROUND_COLOR));
        triangle.setVisible(true);
        panelTask.add(triangle);

        labelThunder = new JLabel(new ImageIcon("thunder.png"));
        labelThunder.setBounds(425, 180, 150, 150);
        labelThunder.setVisible(false);
        panelTask.add(labelThunder);

        labelCircuit = new JLabel(new ImageIcon("circuit.png"));
        labelCircuit.setBounds(425, 180, 150, 150);
        labelCircuit.setVisible(false);
        panelTask.add(labelCircuit);

        labelMountaineer = new JLabel(new ImageIcon("mountaineer.png"));
        labelMountaineer.setBounds(425, 210, 200, 160);
        labelMountaineer.setVisible(false);
        panelTask.add(labelMountaineer);

        labelCheck = new JLabel(new ImageIcon("check.png"));
        labelCheck.setBounds(400, 180, 200, 190);
        labelCheck.setVisible(false);
        panelTask.add(labelCheck);

        labelLock = new JLabel(new ImageIcon("lock.png"));
        labelLock.setBounds(400, 180, 200, 200);
        labelLock.setVisible(false);
        panelTask.add(labelLock);

        buttonTaskBuy = new JButton("Koupit úlohu (-2 body)");
        buttonTaskBuy.setBounds(400, 430, 200, 40);
        buttonTaskBuy.setBackground(Color.LIGHT_GRAY);
        buttonTaskBuy.setVisible(false);
        buttonTaskBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultEvaluation.setTaskState(listTasks.getSelectedIndex(), 2);
                setTaskVisibility(true);
                setTaskMode(listTasks.getSelectedIndex());
                totalPoints -= 2;
                labelTotalPoints.setText(String.valueOf(totalPoints) + " " + resultEvaluation.getPointsTextFormat(totalPoints));
                fileMessage = "(koupení příkladu " + listTasks.getSelectedIndex() + ")";
                fileHandler.addRecords(teamName, totalPoints, fileMessage);
            }
        });
        panelTask.add(buttonTaskBuy);

        labelEndOfCompetition = new JLabel(new ImageIcon("finish.png"));
        labelEndOfCompetition.setBounds(160, 137, 256, 256);
        labelEndOfCompetition.setVisible(false);
        panelTask.add(labelEndOfCompetition);

        fontFinish = new Font("Serif", Font.PLAIN, 40);

        labelFinish = new JLabel("Konec");
        labelFinish.setBounds(400, 350, 150, 40);
        labelFinish.setFont(fontFinish);
        labelFinish.setVisible(false);
        panelTask.add(labelFinish);

        add(panelTask);
    }

    private void getFileData() {
        fileOutputBase = fileHandler.readFile();

        fileOutput = fileOutputBase.split("_");

        for(i = 0; i < fileOutput.length; i++) {
            fileTeam = fileOutput[i].split(" ");
            teamNames[i] = fileTeam[0];
            points[i] = Integer.parseInt(fileTeam[1]);
        }
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
        if(!endOfCompetition) {
            scrollPaneTasks.setVisible(visibility);
            labelTaskTitle.setVisible(visibility);
            labelTaskPoints.setVisible(visibility);
            labelTaskContent.setVisible(visibility);
            textResult.setVisible(visibility);
            textUnits.setVisible(visibility);
            buttonSubmit.setVisible(visibility);
            buttonHelp.setVisible(visibility);
            labelResultFormat.setVisible(visibility);
            if (!visibility) {
                for (i = 0; i < labelVector.length; i++) {
                    labelVector[i].setVisible(false);
                }
                triangle.setVisible(false);
                for (i = 0; i < textTriangleMath.length; i++) {
                    textTriangleMath[i].setVisible(false);
                }
                labelThunder.setVisible(false);
                labelCircuit.setVisible(false);
                labelMountaineer.setVisible(false);
                labelCheck.setVisible(false);
                labelLock.setVisible(false);
                buttonTaskBuy.setVisible(false);
            }
        }
    }

    private void setCountdown() {
        seconds = 0;
        minutes = 0;
        hours = 3;
        timer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                seconds--;
                if(seconds == -1) {
                    minutes--;
                    if(minutes == -1) {
                        hours--;
                        if(hours == -1) {
                            timer.stop();
                            labelTime.setForeground(Color.decode("#800000"));
                            setTaskVisibility(false);
                            labelEndOfCompetition.setVisible(true);
                            labelFinish.setVisible(true);
                            setTaskMode(-1);
                            return;
                        }
                        minutes = 59;
                    }
                    seconds = 59;
                }
                labelTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }
        });
        /*timer.start();*/
    }

    private void setTaskMode(int index) {
        if(index == -1 || endOfCompetition) {
            endOfCompetition = true;
            return;
        }

        if(resultEvaluation.getTaskState(index) == 3) {
            setTaskVisibility(false);
            scrollPaneTasks.setVisible(true);
            labelTaskTitle.setVisible(true);
            labelCheck.setVisible(true);
            return;
        } else if(resultEvaluation.getTaskState(index) == 1) {
            setTaskVisibility(false);
            scrollPaneTasks.setVisible(true);
            labelTaskTitle.setVisible(true);
            labelLock.setVisible(true);
            buttonTaskBuy.setVisible(true);
            return;
        } else {
            labelCheck.setVisible(false);
            labelLock.setVisible(false);
            buttonTaskBuy.setVisible(false);
        }

        switch(index) {
            case 4:
                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setBorder(null);
                    labelVector[i].setVisible(true);
                }
                textResult.setEditable(false);
                textResult.setText("Není vybrána žádná možnost");
                vectorPressedAdapter = new VectorPressedAdapter();
                textResult.addMouseListener(vectorPressedAdapter);
                textUnits.setVisible(false);
                textResult.setBounds(300, 400, 250, 20);

                for(i = 0; i < textTriangleMath.length; i++) {
                    textTriangleMath[i].setVisible(false);
                }
                textResult.setVisible(true);
                buttonSubmit.setBounds(570, 400, 100, 20);
                triangle.setVisible(false);
                labelThunder.setVisible(false);
                labelCircuit.setVisible(false);
                labelMountaineer.setVisible(false);
                break;
            case 5:
                triangleMathResult = "";
                for(i = 0; i < textTriangleMath.length; i++) {
                    triangleMathResult += textTriangleMath[i].getText() + "_";
                    textTriangleMath[i].setVisible(true);
                }
                textResult.setText(triangleMathResult);
                textResult.setVisible(false);
                textUnits.setVisible(false);
                buttonSubmit.setBounds(300, 400, 370, 20);
                triangle.setVisible(true);

                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setVisible(false);
                }
                textResult.setBounds(300, 400, 200, 20);
                labelThunder.setVisible(false);
                labelCircuit.setVisible(false);
                labelMountaineer.setVisible(false);
                break;
            case 13:
                labelThunder.setVisible(true);

                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setVisible(false);
                }
                textResult.setBounds(300, 400, 200, 20);
                for(i = 0; i < textTriangleMath.length; i++) {
                    textTriangleMath[i].setVisible(false);
                }
                textResult.setVisible(true);
                buttonSubmit.setBounds(570, 400, 100, 20);
                triangle.setVisible(false);
                labelCircuit.setVisible(false);
                labelMountaineer.setVisible(false);
                break;
            case 16:
                labelCircuit.setVisible(true);

                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setVisible(false);
                }
                textResult.setBounds(300, 400, 200, 20);
                for(i = 0; i < textTriangleMath.length; i++) {
                    textTriangleMath[i].setVisible(false);
                }
                textResult.setVisible(true);
                buttonSubmit.setBounds(570, 400, 100, 20);
                triangle.setVisible(false);
                labelThunder.setVisible(false);
                labelMountaineer.setVisible(false);
                break;
            case 18:
                labelMountaineer.setVisible(true);

                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setVisible(false);
                }
                textResult.setBounds(300, 400, 200, 20);
                for(i = 0; i < textTriangleMath.length; i++) {
                    textTriangleMath[i].setVisible(false);
                }
                textResult.setVisible(true);
                buttonSubmit.setBounds(570, 400, 100, 20);
                triangle.setVisible(false);
                labelThunder.setVisible(false);
                labelCircuit.setVisible(false);
                break;
            default:
                /*case 4*/
                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setVisible(false);
                }

                /*case 5*/
                for(i = 0; i < textTriangleMath.length; i++) {
                    textTriangleMath[i].setVisible(false);
                }
                textResult.setVisible(true);
                textUnits.setVisible(true);
                buttonSubmit.setBounds(570, 400, 100, 20);
                triangle.setVisible(false);

                /*case 13*/
                labelThunder.setVisible(false);

                /*case 16*/
                labelCircuit.setVisible(false);

                /*case 18*/
                labelMountaineer.setVisible(false);

                textResult.setBounds(300, 400, 200, 20);
                textResult.setEditable(true);
                textResult.setText("Zadejte výsledek");
        }
    }

    public class SelectedListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            switch(resultEvaluation.getTaskState(index)) {
                case 1:
                    c.setEnabled(false);
                    break;
                case 3:
                    c.setForeground(Color.decode("#65a954"));
                    break;
                default:
            }
            return c;
        }
    }

    public class VectorPressedAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(listTasks.getSelectedIndex() == 4)
                textResult.setText("Klikněte na obrázek");
        }
    }

    public class paintComponent extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);

            /*devTool*/
            /*rename x & y*/
            if(listTasks.getSelectedIndex() == 5) {
                int[] x = {185, 100, 270}, y = {20, 140, 140};
                g.setColor(Color.DARK_GRAY);
                g.fillPolygon(x, y, 3);
            }
            /*end*/
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
                /*tady stejně bude něco jiného už*/
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

        labelTaskPoints = new JLabel("3 body");
        labelTaskPoints.setBounds(300, 500, 80, 20);
        panelTest.add(labelTaskPoints);

        labelTaskContent = new JLabel(tasks[listTasks.getSelectedIndex()]);
        labelTaskContent.setBounds(300, 120, 370, 220);
        labelTaskContent.setVerticalAlignment(JLabel.TOP);
        panelTest.add(labelTaskContent);

        textResult = new JFormattedTextField("Zadejte výsledek");
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

        panelTest.add(buttonSubmit);

        fontTotalPoints = new Font("Serif", Font.BOLD, 30);

        labelTotalPoints = new JLabel("0 bodů");
        labelTotalPoints.setBounds(850, 20, 200, 30);
        labelTotalPoints.setFont(fontTotalPoints);
        panelTest.add(labelTotalPoints);

        labelTime = new JLabel("2:40");
        labelTime.setBounds(850, 60, 200, 20);
        panelTest.add(labelTime);

        add(panelTest);
    }
    //endregion
}
