
public class ResultEvaluation {
    public boolean getEvaluation(int index, String taskResult) {
        switch (index) {
            case 0:
                return taskResult.equals("5184000") ? true : false;
        }
        return false;
    }
}
