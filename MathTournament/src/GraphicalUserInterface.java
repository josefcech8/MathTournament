import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class GraphicalUserInterface extends JFrame {

    /*devTools*/
    private JLabel labelMatfyz, labelBrezinky;
    /*end*/


    private int screenSizeX, screenSizeY, FRAME_SIZE_X, FRAME_SIZE_Y;
    String BACKGROUND_COLOR = "#00A08A";
    private Dimension screenSize;

    public GraphicalUserInterface() {
        super("Matematický turnaj");

        FRAME_SIZE_X = 1000;
        FRAME_SIZE_Y = 600;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new CardLayout(0, 0));

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSizeX = screenSize.width;
        screenSizeY = screenSize.height;
        setBounds(screenSizeX / 2 - FRAME_SIZE_X / 2, screenSizeY / 2 - FRAME_SIZE_Y / 2, FRAME_SIZE_X, FRAME_SIZE_Y);


        /*initLoginPanel();
        initRulesPanel();
        /*initTestPanel();
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
        panelLogin.setBackground(Color.decode(BACKGROUND_COLOR));

        labelTitle = new JLabel("Matematický turnaj", SwingConstants.CENTER);
        labelTitle.setBounds(0, 20, FRAME_SIZE_X, 20);
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
    boolean[] taskIndexes = {true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    int totalPoints, i, pointsHolder = 0, seconds, minutes, hours;
    int[] points = {13, 5, 30}, pointsClone;
    String teamNamesHolder;
    String[] columnNames = {"POŘADÍ", "TEAM", "BODY"};
    String[] teamNames = {"Cmyk", "Havíři", "Tutor"};

    String[] taskTitles = {"1. úloha: Meziprostorová",
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
                      "<html><p align=\"justify\">Kuba jede na tábor a bere si svoji karimatku, která má tvar kvádru o rozměrech délky l, šířky d a výšky h. Kuba si karimatku natěsno sroluje podél delší strany l do tvaru válce. Jaký bude poloměr tohoto válce? Zanedbejte „zub“, který vznikne u konce karimatky.</p>",
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
                      "<html><p align=\"justify\">Znavený turista se zastavil v hospodě u cesty a koupil si chlazený nápoj. Nápoj má teplotu t0 = 7 ◦C a objem V = 250mℓ, jeho energetická hodnota je \" = 1 000 kJ/kg. Svými vlastnostmi se nápoj blíží vodě, má tedy hustotu ϱ = 1 kg/ℓ a měrnou tepelnou kapacitu c = 4 kJ/(kg·◦C). Kolik energie turista z nápoje získá, jestliže ho po vypití ve svém těle zahřeje na t1 = 37 ◦C?</p>",
                      "<html><p align=\"justify\">Lenka si namalovala rovnoramenný trojúhelník ABC a s překvapením zjistila, že na jeho ramenech AB, resp. AC, lze najít body P, resp. Q, takové, že |BC| = |CP| = |PQ| = |QA|. Určete velikost úhlu ∡BAC.</p>",
                      "<html><p align=\"justify\">V obývacím pokoji máme skříň o hmotnosti m = 50 kg a šířce 1,2m a chceme přestěhovat její střed o 4m dále. Mezi původní a novou polohou skříně jsou dva metry koberce a poté dva metry lina. Třecí koeficient mezi skříní a kobercem je f1 = 1,0 a mezi skříní a linem f2 = 0,5. Nakreslete graf, kde na vodorovné ose bude poloha středu skříně a na svislé ose síla, kterou musíme skříň tlačit. Osy správně popište a vyznačte na nich důležité body!</p>",
                      "<html><p align=\"justify\">Najděte tři po sobě jdoucí přirozená čísla, která v součtu dávají pátou mocninu některého přirozeného čísla.</p>",
                      "<html><p align=\"justify\">V lunaparku mají novou horskou dráhu. Na začátku jízdy je vozíček na vrcholu stoupání ve výšce h = 20m, ze kterého následně sjede dolů. Dole je rovinka dlouhá s = 200m, na které vozíček brzdí s konstantním zpomalením. S jakým zpomalením (záporným zrychlením) vozíček brzdí, jestliže zastaví přesně na konci rovinky? Odporové síly zanedbejte.</p>",
                      "<html><p align=\"justify\">Kubo si chce doma zavěsit obraz. Má jeden hřebík a jeden provaz, který praskne, pokud je na něj působící síla větší než T = 100N. Jaký nejtěžší obdélníkový obraz si Kubo může pověsit na provaz a hřebík, když provaz a obraz svírají úhel \u000B = 30◦?</p>",
                      "<html><p align=\"justify\">Ve válcovém poháru naplněném vodou, který má plochu podstavy 200 cm2, se vznáší kostka ledu. Vznáší se, neboť je v ledu zamrznutý kamínek o hmotnosti 100 g a hustotě 5 000 kg/m3. Časem se led rozpustí a kamínek klesne na dno. Co se stane s hladinou vody? Klesne, stoupne, nebo se nezmění? Pokud se změní, tak o kolik?</p>",
    };
    Object[][] dataRank;
    Timer timer;
    Font fontTitle, fontTotalPoints, fontTeam, fontTime, fontTaskPoints;
    JLabel labelTaskTitle, labelTaskContent, labelTaskPoints, labelTotalPoints, labelTime, labelTeamName;
    JLabel[] labelVector;
    JTextField textResult, textUnits;
    JButton buttonTask, buttonRank, buttonRules, buttonSubmit;
    JScrollPane scrollPaneTasks, scrollPaneTableRank;
    JList listTasks;
    JTable tableRank;
    DefaultTableCellRenderer centerRenderer;
    ResultEvaluation resultEvaluation;
    VectorPressedAdapter vectorPressedAdapter;
    JPanel panelTask;

    private void initTaskPanel() {
        panelTask = new JPanel();
        getContentPane().add(panelTask);
        panelTask.setLayout(null);
        panelTask.setBackground(Color.decode(BACKGROUND_COLOR));

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

        fontTeam = new Font("Comic Sans", Font.PLAIN, 15);

        labelTeamName = new JLabel("Tým: CMYK");
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
                if(taskIndexes[listTasks.getSelectedIndex()]) {
                    labelTaskContent.setText(tasks[listTasks.getSelectedIndex()]);
                } else {
                    /*labelTaskContent.setText("zámek");*/
                    labelTaskContent.setText(tasks[listTasks.getSelectedIndex()]);
                } /* else => v případě vyřešené úlohy fajfka */
                labelTaskTitle.setText(taskTitles[listTasks.getSelectedIndex()]);
                labelTaskPoints.setText(String.valueOf("[" + resultEvaluation.getTaskPoints(listTasks.getSelectedIndex())) + " bodů]");

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

        labelTaskPoints = new JLabel("[3 bodů]");
        labelTaskPoints.setBounds(660, 70, 80, 30);
        labelTaskPoints.setFont(fontTaskPoints);
        panelTask.add(labelTaskPoints);

        labelTaskContent = new JLabel(tasks[listTasks.getSelectedIndex()]);
        labelTaskContent.setBounds(300, 120, 370, 220);
        labelTaskContent.setVerticalAlignment(JLabel.TOP);
        panelTask.add(labelTaskContent);

        textResult = new JTextField("Zadejte výsledek");
        textResult.setBounds(300, 400, 200, 20);
        /* Záleží na každém úkolu
        textResult.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Vzorové zadání odpovědi");
                textResult.setText("");
            }
        });*/
        panelTask.add(textResult);

        textUnits = new JTextField("km");
        textUnits.setBounds(500, 400, 50, 20);
        textUnits.setEditable(false);
        panelTask.add(textUnits);

        /*devTool*/
        resultEvaluation.setValues();
        /*end*/

        buttonSubmit = new JButton("Potvrdit");
        buttonSubmit.setBounds(570, 400, 100, 20);
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(resultEvaluation.getEvaluation(listTasks.getSelectedIndex(), textResult.getText())) {
                    totalPoints += resultEvaluation.getTaskPoints(listTasks.getSelectedIndex());
                    labelTotalPoints.setText(String.valueOf(totalPoints) + " bodů");
                } else {
                    if(resultEvaluation.getTaskPoints(listTasks.getSelectedIndex()) != 1) {
                        resultEvaluation.setTaskPoints(listTasks.getSelectedIndex());
                        labelTaskPoints.setText("[" + resultEvaluation.getTaskPoints(listTasks.getSelectedIndex()) + " bodů]");
                    }
                }
                textResult.setText("");
            }
        });
        panelTask.add(buttonSubmit);

        fontTotalPoints = new Font("Serif", Font.BOLD, 30);

        labelTotalPoints = new JLabel("0 bodů");
        labelTotalPoints.setBounds(850, 20, 200, 30);
        labelTotalPoints.setFont(fontTotalPoints);
        panelTask.add(labelTotalPoints);

        fontTime = new Font("Serif", Font.BOLD, 23);

        labelTime = new JLabel("time error");
        labelTime.setBounds(850, 60, 200, 30);
        labelTime.setFont(fontTime);
        setCountdown();
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
        scrollPaneRulesContent.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
        scrollPaneRulesContent.setVisible(false);
        panelTask.add(scrollPaneRulesContent);

        labelVector = new JLabel[4];
        for(i = 0; i < labelVector.length; i++) {
            labelVector[i] = new JLabel();
        }
        labelVector[0].setIcon(new ImageIcon("vector_1.gif"));
        labelVector[0].setBounds(300, 175, 80, 80);
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
        labelVector[1].setBounds(400, 175, 80, 80);
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
        labelVector[2].setBounds(300, 270, 80, 80);
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
        labelVector[3].setBounds(400, 270, 80, 80);
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

        /*devTool*/
        /*cleancode*/
        /*rename class*/
        /*center pictures || layout*/
        paintComponent paint100 = new paintComponent();
        paint100.setBounds(300, 200, 370, 170);
        paint100.setBackground(Color.decode(BACKGROUND_COLOR));
        panelTask.add(paint100);
        /*end*/

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
        labelTaskPoints.setVisible(visibility);
        labelTaskContent.setVisible(visibility);
        textResult.setVisible(visibility);
        textUnits.setVisible(visibility);
        buttonSubmit.setVisible(visibility);
    }

    private void setCountdown() {
        seconds = 30;
        minutes = 5;
        hours = 3;
        timer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                seconds--;
                if(seconds == -1) {
                    minutes--;
                    if(minutes == -1) {
                        hours--;
                        if(hours == -1) {
                            // konec
                        }
                        minutes = 59;
                    }
                    seconds = 59;
                }
                labelTime.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }
        });
        timer.start();
    }

    private void setTaskMode(int index) {
        switch(index) {
            case 4:
                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setVisible(true);
                }
                textResult.setEditable(false);
                textResult.setText("není vybrána žádná možnost");
                vectorPressedAdapter = new VectorPressedAdapter();
                textResult.addMouseListener(vectorPressedAdapter);
                break;
            default:
                for(i = 0; i < labelVector.length; i++) {
                    labelVector[i].setVisible(false);
                    labelVector[i].setBorder(null);
                }

                textResult.setEditable(true);
                textResult.setText("Zadejte výsledek");
        }
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

    public class VectorPressedAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if(listTasks.getSelectedIndex() == 4)
                textResult.setText("klikni na obrázek");
        }
    }

    public class paintComponent extends JPanel {
        public void paint(Graphics g) {
            super.paint(g);

            if(listTasks.getSelectedIndex() == 5) {
                int[] x = {185, 100, 270}, y = {20, 140, 140};
                g.setColor(Color.DARK_GRAY);
                g.fillPolygon(x, y, 3);
            }
        }
    }

    public class paintComponent2 extends JPanel {
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
