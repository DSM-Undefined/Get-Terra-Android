package undefined.dsm.getterra.ui;

import android.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import undefined.dsm.getterra.R;


public class CorrectionMainActivity extends AppCompatActivity {
    Activity quizActivity = QuizMainActivity._QuizMainActivity;
    View correctionBackground;
    TextView goToBack;
    TextView isAnswerTrue;
    TextView clubName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction_main);
        correctionBackground = findViewById(R.id.correction_background);
        goToBack = (TextView)findViewById(R.id.correction_gotoback_tv);
        isAnswerTrue = (TextView)findViewById(R.id.correction_isanswertrue_tv);
        clubName = (TextView)findViewById(R.id.correction_clubname_tv);
        setBackGroundColor();
    }
    public void goToMain(View v)
    {
      quizActivity.finish();
      finish();
    }
    public void backToResolve(View v)
    {
        finish();
    }
    public void setBackGroundColor()
    {
        clubName.setText("동아리의 이름입니당");//동아리이름. 레트로핏 서버연동 필요

        if(true){ // 정답이 틀렸다면.
            correctionBackground.setBackgroundColor(0xffd4e5ff);
            isAnswerTrue.setText("오답");
            goToBack.setTextColor(0xff84b6ff);
        }
        else{
            correctionBackground.setBackgroundColor(0xfffff5f5);
            isAnswerTrue.setText("정답!");
            goToBack.setTextColor(0xffffb587);

            // 점령시켰다는 메세지 서버에 보내기.
        }
    }
}