
public class ResultEvaluation {

    private int taskResultInteger, i1, i2;
    private int[] taskPoints = new int[42];
    private double taskResultDouble;
    private String[] triangleMath = new String[6], sofaL = new String[2], units = {"s", "Kč", "\\", "km/h", "\\", "\\", "kg", "s", "min", "\\", "\\", "km", "\\", "m/s", "%", "g", "A", "oběhů", "m^2", "s", "\\", "m", "\\", "s", "cm", "m", "AU", "cm", "\u2126", "\\", "km/h", "%", "kJ", "\\", "\\", "kJ", "◦", "J", "\\", "m/s^2", "kg", "cm"};
    private String[] resultFormat = {"Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "",
                                     "",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Poměr zapište ve tvaru 1:2. Nezapomeňte zapsat menší číslici jako první. V případě více řešení jednotlivé poměry oddělte čárkou bez mezery např.: 1:2,2:3",
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
                                     "Poměr zapište ve tvaru 1:2. Nezapomeňte zapsat menší číslici jako první.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na dvě desetinná místa.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na celé číslo.",
                                     "Výsledek zaokrouhlete na jedno desetinné."};

    public void setValues() {
        for (int i = 0; i < 42; i++) {
            taskPoints[i] = 3;
        }
        System.out.println(resultFormat.length);
    }

    public void setTaskPoints(int index) {
        taskPoints[index]--;
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
                taskResultDouble = Double.parseDouble(taskResult);
                taskResultInteger = Integer.parseInt(taskResult);
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
