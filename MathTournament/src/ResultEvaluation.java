
public class ResultEvaluation {

    private int i;
    private boolean[] helpTextAvailable = new boolean[42];
    private int taskResultInteger, i1, i2;
    private int[] taskPoints = {3, 3, 3, 3, 2, 3, 3, 3, 4, 6, 2, 4, 4, 4, 4, 5, 5, 6, 5, 5, 5, 5, 5, 6, 5, 6, 7, 5, 6, 6, 6, 6, 6, 5, 5, 7, 7, 6, 7, 7, 7, 10};
    private int[] helpPoints = {0, -1, 0, -1, 0, 0, -1, -1, -2, -2, 0, 0, -2, 0, -1, -2, -1, -2, -1, 0, -2, -2, -2, 0, -3, 0, -2, -1, -2, -1, -1, -2, -2, 0, -1, 0, 0, 0, 0, -2, 0, 0};
    private int[] taskState = new int[42];
    private double taskResultDouble;
    private String teamName;
    private String[] triangleMath = new String[6], sofaL = new String[2], units = {"s", "Kč", "", "km/h", "", "", "kg", "s", "min", "", "", "km", "", "m/s", "%", "g", "A", "oběhů", "m^2", "s", "", "m", "", "s", "cm", "m", "AU", "cm", "\u2126", "", "km/h", "%", "kJ", "", "", "kJ", "◦", "J", "", "m/s^2", "kg", "cm"};
    private String[] resultFormat = {"Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "",
                                     "Napsaná čísla můžete pouze přepsat.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "<html><p align=\"justify\">Poměr zapište ve tvaru 1:2. Nezapomeňte zapsat menší číslici jako první. V případě více řešení jednotlivé poměry oddělte čárkou bez mezery např.: 1:2,2:3</p></html>",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na jedno desetinné místo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na jedno desetinné místo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na jedno desetinné místo.",
                                     "Výsledné rozměry rovnoběžníku zapiště ve tvaru 1x1",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na jedno desetinné místo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na dvě desetinná místa.",
                                     "Výsledek zaokrouhlete na dvě desetinná místa.",
                                     "Výsledek zaokrouhlete na jedno desetinné místo.",
                                     "Zadejte počet cifer výsledného čísla",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "<html><p align=\"justify\">Poměr zapište ve tvaru 1:2. Nezapomeňte zapsat menší číslici jako první.</p></html>",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na dvě desetinná místa.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na jedno desetinné místo."};
    private String[] helpText = {"",
                                 "Označme si cenu míčku jako x korun. Platí, že pálka stojí (x + 1000) korun.<br>Set pálky a míčku za 1100 korun tedy lze zapsat rovnicí.",
                                 "",
                                 "Průměrná rychlost je podíl celkové dráhy a celkového času",
                                 "",
                                 "",
                                 "Určíme nejprve, o kolik měli zhubnout podle předsevzetí.<br>Vzhledem k tomu, že oba chtěli zhubnout o stejné procento, potřebujeme vypočítat, kolik je 10% z m<sub>Z</sub> + m<sub>J</sub> = 200 kg.<br>Potom spočítáme, kolik každý z manželů doopravdy zhubl. Paní Zita zhubla m<sub>Z</sub> · 15% a pan Jakub m<sub>J</sub> · 5%.<br>Nyní dáme dáme hodnoty dohromady a určíme rozdíl.",
                                 "Mezi devíti stromy je mezi stromy 8 stejných mezer.<br>Zjistíme, jaký čas trvá Lence přeběhnout jednu mezeru.",
                                 "Ze zadání vyplývá, že za minutu se napustí studenou vodou 1/4 vany (poněvadž za 4 minuty se napustí celá vana)<br>a horkou vodou 1/12 vany. Z toho dokážeme určit kolik se celkem napustí za minutu.",
                                 "Tři nejmenší prvočísla jsou 2, 3 a 5. Jsou nesoudělná, hledané číslo proto bude muset<br>být dělitelné jejich součinem – hledáme tedy násobek 30. To je to samé<br>jako bychom hledali násobek 3 a nakonec dopsali nulu. Kritériem pro dělitelnost trojkou<br>je podmínka, že ciferný součet čísla musí být dělitelný třemi.",
                                 "",
                                 "",
                                 "Mohli bychom počítat přímo neobarvené stěny, ale mnohem jednodušší bude<br>odečíst obarvené od celkového počtu. Podstava mrakodrapu není obarvená.",
                                 "",
                                 "Z jámy se na hromadu dostane dle zadání pouze 80% = 8/10 písku.<br>Z tohoto množství se do kolečka dostane zase jenom 8/10, ze zbytku do<br>kbelíku pouze 50% = 5/10 a do míchačky 40% = 4/10 zbylého množství.",
                                 "Jedná se o soustavu dvojzvratných pák, pro něž platí, že v rovnováze je<br>velikost momentů tíhových sil záváží stejná: m1*r1 = m2*r2.",
                                 "Pokud se na obvod podíváme pořádně, zjistíme,<br>že se jedná pouze o paralelní zapojení dvou rezistorů.",
                                 "Poměr poloměrů drah lodí je 3 : 4 : 5, což je i poměr jejich oběžných dob, neboť<br>všechny obíhají hvězdu stejnou rychlostí. Lodě a hvězda se setkají v jedné přímce po takovém počtu<br>oběhů, který je roven nejmenšímu společnému násobku čísel 3, 4 a 5.",
                                 "S pomocí Pythagorovy věty si vypočteme jeden z rozměrů horolezecké stěny c.",
                                 "",
                                 "Označme si x hodnotu druhého hodu.<br>Zbylé hody si zapíšeme v násobcích neznámé: první hod jedvakrát větší než<br>druhý, tedy 2x. Třetí hod je trojnásobek druhého, tedy 3x.<br>Počtvrté Pavel hodil 2x + x = 3x a v pátém hodu hodil 3x/2.<br>Dále si pomůžeme logickou úvahou, poněvadž víme, že na kostce mohou<br>být pouze celá čísla od 1 do 6.",
                                 "Poměr hmotností Pepy a Pavla je 4 : 3. Aby byli na houpačce v rovnováze, musí platit<br>tzv. momentová věta – momenty tíhových sil (součiny táhových sil,<br>tzn. hmotností a g a vzdáleností od osy otáčení) musí být z obou stran houpačky stejné.",
                                 "Pravá strana rovnice je vždy sudé číslo (neboť součet a + b násobíme dvěma).<br>Aby byla celá rovnice splněna, musí tedy i levá strana rovnice (ab) být sudá (dělitelná dvěma).<br>To nastane v případě, kdy alespoň jedno z čísel a nebo b je sudé.",
                                 "",
                                 "Při rolování karimatky vytvarujeme z boční strany s rozměry l × h podstavu válce s poloměrem r, který chceme spočítat.<br>Při našem zanedbání bude platit, že obě plochy jsou stejné, tudíž platí l*h = pi*r^2.",
                                 "",
                                 "Jelikož se Ondra dívá na dvojhvězdu pod stejným úhlem, pod kterým je definována<br>vzdálenost jednoho parseku, stačí z podobnosti trojúhelníků určit hledanou délku.",
                                 "Nakreslíme si plechovky z pohledu shora a doplníme kolmice ze středů jejich podstav na rovné úseky fólie.<br>Z obrázku je vidět, jaké útvary se pro výpočet použijí.",
                                 "Jsou čtyři možné kombinace zapojení.<br>Všechny rezistory můžeme zapojit sériově, všechny rezistory můžeme zapojit paralelně,<br>dva rezistory může zapojit sériově a jeden rezistor k nim paralelně, dva rezistory paralelně<br>a k nim jeden rezistor sériově.",
                                 "Nejdříve je třeba si vypočítat objem Baltského moře.<br>Víme, že 1 ℓ vody z Baltského moře obsahuje 10 g soli.<br>My ale chceme, aby 1 ℓ obsahoval 40 g soli.",
                                 "Průměrnou rychlost vypočítáme jako podíl celkové dráhy a času, za nějž ji auto ujelo.",
                                 "Kruh, který si Radka připravila, měl poloměr r. Víme, že z něj vykrojila největší možný čtverec, tedy že úhlopříčka čtverce měří 2r.<br>Stranu čtverce označme jako a. Pro trojúhelník, jehož přepona měří 2r, má odvěsny délky a, můžeme tedy napsat Pythagorovu větu.",
                                 "K řešení tohoto příkladu si stačí uvědomit, že práce, kterou Simča vykoná bude stejná,<br>jako práce, kterou vykoná zvedané těleso v tíhovém poli. Toto vyplývá z Pascalova zákona.",
                                 "",
                                 "Poměr hmotností 1:3 nám říká, že v 1 kg bronzu bude 1/4 kg cínu a 3/4 kg mědi.",
                                 "",
                                 "",
                                 "",
                                 "",
                                 "Na začátku má vozíček nulovou rychlost a je ve výšce h = 20m. Má tedy potenciální energii.<br>Při vjezdu na rovinku má rychlost v. Má tedy kinetickou energii. Při pohybu na rovince se jedná o<br>rovnoměrně zpomalený pohyb z rychlosti v na nulovou rychlost.",
                                 "",
                                 ""};
    private String[] pointsTextFormat = {"bod", "body", "bodů"};

    public ResultEvaluation() {
        for(i = 0; i < helpTextAvailable.length; i++) {
            if(i < 7) {
                taskState[i] = 2;
            } else {
                taskState[i] = 1;
            }
            helpTextAvailable[i] = false;
        }
    }

    public void setTaskState(int index, int state) {
        taskState[index] = state;
    }

    public void setNextTaskUnlock() {
        for(i = 6; i < taskState.length; i++) {
            if(taskState[i] == 1) {
                taskState[i] = 2;
                return;
            }
        }
    }

    public void setAdminTaskUnlock() {
        for(i = 0; i < taskState.length; i++) {
            helpTextAvailable[i] = true;
            if(taskState[i] == 1)
                taskState[i] = 2;
        }
    }

    public void setTaskPoints(int index) {
        taskPoints[index]--;
    }

    public void setHelpTextAvailable(int index) {
        helpTextAvailable[index] = true;
    }

    public void setTeamName(String team) {
        teamName = team;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTaskState(int index) {
        return taskState[index];
    }

    public boolean getHelpTextAvailable(int index) {
        return helpTextAvailable[index];
    }

    public String getPointsTextFormat(int points) {
        switch(points) {
            case 0: return pointsTextFormat[2];
            case 1: return pointsTextFormat[0];
            case 2:
            case 3:
            case 4: return pointsTextFormat[1];
            default: return pointsTextFormat[2];
        }
    }

    public int getHelpPoints(int index) {
        return helpPoints[index];
    }

    public String getHelpText(int index) {
        return helpText[index];
    }

    public String getResultFormat(int index) {
        return resultFormat[index];
    }

    public int getTaskPoints(int index) {
        return taskPoints[index];
    }

    public String getUnits(int index) {
        return units[index];
    }

    public boolean getEvaluation(int index, String taskResult) {

        try {
            if(index != 5 && index != 10 && index != 22 && index != 34) {
                taskResultInteger = Integer.parseInt(taskResult);
            }
        } catch(Exception e) {}
        try {
            if(index != 5 && index != 10 && index != 22 && index != 34) {
                taskResultDouble = Double.parseDouble(taskResult.replace(",", "."));
            }
        } catch(Exception e) {
            return false;
        }

        switch (index) {
            case 0: return taskResultInteger == 5184000;
            case 1: return taskResultInteger == 50;
            case 2: return taskResultInteger == 6;
            case 3: return taskResultInteger == 16;
            case 4: return taskResultInteger == 2;
            case 5:
                triangleMath = taskResult.split("_");
                for(i1 = 0; i1 < triangleMath.length; i1++) {
                    for(i2 = i1 + 1; i2 < triangleMath.length; i2++) {
                        if (triangleMath[i1].equals(triangleMath[i2])) {
                            return false;
                        }
                    }
                }
                if(Integer.parseInt(triangleMath[0]) + Integer.parseInt(triangleMath[1]) + Integer.parseInt(triangleMath[2]) != 9) {
                    return false;
                } else if(Integer.parseInt(triangleMath[2]) + Integer.parseInt(triangleMath[3]) + Integer.parseInt(triangleMath[4]) != 9) {
                    return false;
                } else if(Integer.parseInt(triangleMath[4]) + Integer.parseInt(triangleMath[5]) + Integer.parseInt(triangleMath[0]) != 9) {
                    return false;
                }
                return true;
            case 6: return taskResultInteger == 2;
            case 7: return taskResultInteger == 144;
            case 8: return taskResultInteger == 3;
            case 9: return taskResultInteger == 1110;
            case 10:
                sofaL = taskResult.split(",");
                if((sofaL[0].equals("3:4") && sofaL[1].equals("2:5")) || (sofaL[0].equals("2:5") && sofaL[1].equals("3:4"))) {
                    return true;
                }
                return false;
            case 11: return taskResultInteger == 120;
            case 12: return taskResultInteger == 968;
            case 13: return taskResultInteger == 333;
            case 14: return taskResultDouble == 12.8;
            case 15: return taskResultInteger == 175;
            case 16: return taskResultDouble == 1.3;
            case 17: return taskResultInteger == 12;
            case 18: return taskResultInteger == 15;
            case 19: return taskResultInteger == 25;
            case 20: return taskResultInteger == 21;
            case 21: return taskResultDouble == 1.8;
            case 22:return taskResult.equals("4x4");
            case 23: return taskResultInteger == 25;
            case 24: return taskResultDouble == 6.3;
            case 25: return taskResultInteger == 6;
            case 26: return taskResultDouble == 8.24;
            case 27: return taskResultDouble == 61.43;
            case 28: return taskResultDouble == 5.5;
            case 29: return taskResultInteger == 15;
            case 30: return taskResultInteger == 80;
            case 31: return taskResultInteger == 36;
            case 32: return taskResultInteger == 5;
            case 33: return taskResultInteger == 24;
            case 34: return taskResult.equals("3:7");
            case 35: return taskResultInteger == 220;
            case 36: return taskResultDouble == 25.71;
            case 37: return taskResultInteger == 3000;
            case 38: return Math.round(Math.pow(3*(taskResultDouble+1), 1.0/5.0)) % 3 == 0 ? true : false;
            case 39: return taskResultInteger == 1;
            case 40: return taskResultInteger == 10;
            case 41: return taskResultDouble == 0.4;
            default:
                return false;
        }
    }
}
