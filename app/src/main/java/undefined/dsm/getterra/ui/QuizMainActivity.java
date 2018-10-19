package undefined.dsm.getterra.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.annotations.NonNull;
import undefined.dsm.getterra.R;
import retrofit2.Retrofit;
import undefined.dsm.getterra.connecter.QuizRetrofit;

public class QuizMainActivity extends AppCompatActivity {

    // Retrofit
    /*Retrofit.Builder retrofit;
    QuizRetrofit quizRetrofit;
    int problemType, problemid;
    String question;
    String[] select;*/

    //Fragment
    Fragment fr;

    //View
    TextView _problemType;
    TextView _question;
    TextView _answer;
    TextView nextActivity;
    EditText userInput;

    //test
    int _problemTypetest = 0;

    public static Activity _QuizMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);
        //서버에서 데이터 불러와야함.
        _QuizMainActivity = QuizMainActivity.this;

        /*problemType = quizRetrofit.getProblemType();
        problemid = quizRetrofit.getProblemId();
        question = quizRetrofit.getQuestion();
        select = quizRetrofit.getChoices();
        */
        _problemType = findViewById(R.id.quiz_problemtype_tv);
        _question = findViewById(R.id.quiz_problem_tv);
        _answer = findViewById(R.id.quiz_answer_tv);
        userInput = findViewById(R.id.quiz_userInput_et);

        nextActivity = findViewById(R.id.quiz_answer_tv);
        nextActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(getApplicationContext(), CorrectionMainActivity.class);
                startActivity(intent);
            }
        });

        QuizActivitySet();
       FragmentSet();
    }
    public void QuizActivitySet(){
        switch (_problemTypetest)
        {
            case 0: {_problemType.setText("주관식");
                       fr = new SubjectFragment();
                       break;
                    }
            case 1: {_problemType.setText("객관식");
                        fr = new SelectFragment();
                        break;
                    }
            case 2: {_problemType.setText("O/X");
                        fr = new OXFragment();
                        break;
                    }
        }
        _question.setText(/*question*/"문제@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
    }
    public void FragmentSet()
    {
        FragmentManager fragment = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragment.beginTransaction();
        fragmentTransaction.replace(R.id.quiz_fragment_fg, fr);
        fragmentTransaction.commit();
    }
    public void backFromQuizMain(View v)
    {
        finish();
    }
}