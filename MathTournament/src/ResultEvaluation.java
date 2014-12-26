
public class ResultEvaluation {

    private int taskResultInteger;
    private int[] taskPoints = new int[42];
    private double taskResultDouble;

    public void setValues() {
        for (int i = 0; i < 42; i++) {
            taskPoints[i] = 3;
        }
    }

    public void setTaskPoints(int index) {
        taskPoints[index]--;
    }

    public int getTaskPoints(int index) {
        return taskPoints[index];
    }

    public boolean getEvaluation(int index, String taskResult) {

        try {
            taskResultDouble = Double.parseDouble(taskResult);
            taskResultInteger = Integer.parseInt(taskResult);
        } catch(Exception e) {
            return false;
        }

        switch (index) {
            case 0: return taskResultInteger == 5184000;
            case 1: return taskResultInteger == 50;
            case 2: return taskResultInteger == 6;
            case 3: return taskResultInteger == 16;
            case 4:
            case 5:
            case 6: return taskResultInteger == 2;
            case 7: return taskResultInteger == 144;
            case 8: return taskResultInteger == 3;
            case 9: return taskResultInteger == 1110;
            case 10:
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
            case 22:
            case 23: return taskResultInteger == 25;
            case 24: // dát na výběr ze čtyř možností
            case 25: return taskResultInteger == 6;
            case 26: return taskResultDouble == 8.24;
            case 27: return taskResultDouble == 61.43;
            case 28: return taskResultDouble == 5.5;
            case 29: // napsat v semilogaritmickém tvaru
            case 30: return taskResultInteger == 80;
            case 31: return taskResultInteger == 36;
            case 32: return taskResultInteger == 5;
            case 33: return taskResultInteger == 24;
            case 34:
            case 35: return taskResultInteger == 220;
            case 36: return taskResultDouble == 25.71;
            case 37:
            case 38: return Math.round(Math.pow(3*(taskResultDouble+1), 1.0/5.0)) % 3 == 0 ? true : false;
            case 39: return taskResultInteger == 1;
            case 40: return taskResultInteger == 10;
            case 41: return taskResultDouble == 0.4;
            default:
                return false;
        }
    }
}
