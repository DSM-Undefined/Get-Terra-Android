package undefined.dsm.getterra.ui;

import android.app.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import undefined.dsm.getterra.R;
import undefined.dsm.getterra.connecter.API;
import undefined.dsm.getterra.connecter.GetSolve;

public class QuizMainActivity extends AppCompatActivity {

    String boothName = "undefined";
    String question = "문제입니당";
    String SclubName = "동아리이름입니당";
    int statusCode;

    //Fragment
    Fragment fr;

    //View
    TextView _problemType;
    TextView _question;
    TextView clubName;

    TextView nextActivity;

    EditText userInput;

    //retrofit
    API service;

    //test
    int _problemTypetest = 1;
    public static Activity _QuizMainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);
        //서버에서 데이터 불러와야함.
        _QuizMainActivity = QuizMainActivity.this;

        _problemType = findViewById(R.id.quiz_problemtype_tv);
        _question = findViewById(R.id.quiz_problem_tv);
        userInput = findViewById(R.id.quiz_userInput_et);
        clubName = findViewById(R.id.quiz_clubname_tv);

        nextActivity = findViewById(R.id.quiz_answer_tv);
        nextActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(getApplicationContext(), CorrectionMainActivity.class);
                startActivity(intent);
            }

            //retrofit

        });
        QuizSet();
        /*switch(statusCode)
        {
            case 204:Toast.makeText(getApplicationContext(), "해당 부스아이디에 해당하는 부스 없음.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 205:Toast.makeText(getApplicationContext(), "이미 팀에서 점령한 부스임.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 401:Toast.makeText(getApplicationContext(), "request header에 access token 없음.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 403:Toast.makeText(getApplicationContext(), "권한 없음.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 406:Toast.makeText(getApplicationContext(), "게임 시작 전.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 412:Toast.makeText(getApplicationContext(), "게임 종료.",Toast.LENGTH_SHORT).show(); finish(); break;
        }*/
        QuizActivitySet();
       FragmentSet();
    }
    public void QuizActivitySet(){
        switch (_problemTypetest)
        {
            case 0: {_problemType.setText("주관식");
                       fr = new SubjectFragment();
                       userInput.setVisibility(View.VISIBLE);

                       break;
                    }
            case 1: {_problemType.setText("객관식");
                        fr = new SelectFragment();
                        userInput.setVisibility(View.GONE);
                        break;
                    }
            case 2: {_problemType.setText("O/X");
                        fr = new OXFragment();
                        userInput.setVisibility(View.GONE);
                        break;
                    }
        }
        //_question.setText(/*question*/"문제@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
        _question.setText(question);
        clubName.setText(SclubName);
    }
    public void QuizSet()
    {
        service.getSolve(boothName).enqueue(new Callback<GetSolve>() {
            @Override
            public void onResponse(Call<GetSolve> call, Response<GetSolve> response) {
                if(response.isSuccessful()) {
                    GetSolve solve = response.body();
                    _problemTypetest =solve.getProblemType();
                    question = solve.getQuestion();
                    SclubName = solve.getBoothName();
                }else {
                    statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<GetSolve> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버에 접속할 수 없습니다.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    public void FragmentSet()
    {
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragment.beginTransaction();
        fragmentTransaction.replace(R.id.quiz_fragment_fg, fr);
        fragmentTransaction.commit();
    }
    public void backFromQuizMain(View v)
    {
        finish();
    }
}