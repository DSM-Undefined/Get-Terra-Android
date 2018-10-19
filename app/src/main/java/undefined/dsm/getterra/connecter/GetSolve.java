package undefined.dsm.getterra.connecter;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSolve {
    @SerializedName("boothName")
    @Expose
    private String boothName;
    @SerializedName("problemId")
    @Expose
    private String problemId;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("problemType")
    @Expose
    private int problemType;
    @SerializedName("choices")
    @Expose
    private String[] choices = new String[4];

    public String getBoothName() {
        return boothName;
    }

    public String getQuestion() {
        return question;
    }

    public int getProblemType() {
        return problemType;
    }

    public String[] getChoices() {
        return choices;
    }

    public String getProblemId()
    {
        return problemId;
    }
}
