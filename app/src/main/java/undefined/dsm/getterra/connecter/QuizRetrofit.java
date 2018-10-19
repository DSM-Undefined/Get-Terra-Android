package undefined.dsm.getterra.connecter;

public class QuizRetrofit {
    String question;
    int problemType; // 0: 주관식 1: 4지선다 2: ox
    String []choices = new String[4];
    int problemId;

    public String getQuestion() {
        return question;
    }

    public int getProblemType() {
        return problemType;
    }

    public String[] getChoices() {
        return choices;
    }

    public int getProblemId() {
        return problemId;
    }
}
