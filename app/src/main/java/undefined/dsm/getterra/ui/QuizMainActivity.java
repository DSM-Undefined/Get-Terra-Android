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


import com.justgo.Connecter.Connecter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import undefined.dsm.getterra.R;
import undefined.dsm.getterra.connecter.API;
import undefined.dsm.getterra.connecter.GetSolve;
import undefined.dsm.getterra.connecter.PostSolve;
import undefined.dsm.getterra.connecter.problemIdAnswer;

public class QuizMainActivity extends AppCompatActivity implements OXFragment.SendDataOX, SelectFragment.SendDataSelect{
    String boothName = "undefined";
    String question = "문제입니당";
    String problemId = "문제 아이디";
    String answer = "답";
    int statusCode;
    int result;
    boolean OX[] = new boolean[2];
    boolean BSelect[] = new boolean[4];

    //Fragment
    Fragment fr;

    //View
    TextView _problemType;
    TextView _question;
    TextView clubName;

    TextView nextActivity;

    EditText userInput;

    //retrofit

    //test
    int _problemTypetest = 2;
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
                switch(_problemTypetest)
                {
                    case 0: answer = userInput.getText().toString(); if(answer.length()==0){Toast.makeText(getApplicationContext(), "정답을 입력해주세요.",Toast.LENGTH_SHORT).show(); return;}
                    case 1:
                    for(int i=0; i<4; i++)
                    {
                        if(BSelect[i]==true)
                        {
                            answer = Integer.toString(i);
                            break;
                        }
                    }
                    case 2:
                    {
                        if(OX[0]==true)
                        {
                            answer = "O";
                        }
                        else if(OX[1]==true)
                        {
                            answer = "X";
                        }
                    }
                }
                Submission();
                Intent intent = new Intent(getApplicationContext(), CorrectionMainActivity.class);
                switch(result)
                {
                    case 401:Toast.makeText(getApplicationContext(), "request header에 access token 없음.",Toast.LENGTH_SHORT).show();  break;
                    case 403:Toast.makeText(getApplicationContext(), "권한 없음.",Toast.LENGTH_SHORT).show();  break;
                    case 406:Toast.makeText(getApplicationContext(), "게임 시작 전.",Toast.LENGTH_SHORT).show();  break;
                    case 412:Toast.makeText(getApplicationContext(), "게임 종료.",Toast.LENGTH_SHORT).show();  break;
                    case 201:intent.putExtra("isAnswerTrue",true); intent.putExtra("boothName",boothName); startActivity(intent); break;
                    case 204:intent.putExtra("isAnswerTrue",false); intent.putExtra("boothName",boothName); startActivity(intent); break;
                }
            }

            //retrofit

        });
        QuizSet();
        switch(statusCode)
        {
            case 204:Toast.makeText(getApplicationContext(), "해당 부스아이디에 해당하는 부스 없음.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 205:Toast.makeText(getApplicationContext(), "이미 팀에서 점령한 부스임.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 401:Toast.makeText(getApplicationContext(), "request header에 access token 없음.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 403:Toast.makeText(getApplicationContext(), "권한 없음.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 406:Toast.makeText(getApplicationContext(), "게임 시작 전.",Toast.LENGTH_SHORT).show(); finish(); break;
            case 412:Toast.makeText(getApplicationContext(), "게임 종료.",Toast.LENGTH_SHORT).show(); finish(); break;
        }
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
        _question.setText(question);
        clubName.setText(boothName);
    }
    public void QuizSet()
    {
        API service = Connecter.api;
        Call <GetSolve> call = service.getSolve("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1Mzk5NjU5NzIsIm5iZiI6MTUzOTk2NTk3MiwianRpIjoiNGY2YTAyNDUtMjRiZS00NDg1LWJhNmYtOTFhY2ZlMzZlMjQ2IiwiZXhwIjoxNTcxNTAxOTcyLCJpZGVudGl0eSI6Im5lcmQiLCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MifQ.ugcB9wpQJkAO3CwgKMX-vkU44OT97HAKEN8q_Po4MP8",boothName);
        call.enqueue(new Callback<GetSolve>() {
            @Override
            public void onResponse(Call<GetSolve> call, Response<GetSolve> response) {
                if(response.isSuccessful()) {
                    GetSolve solve = response.body();
                    _problemTypetest =solve.getProblemType();
                    question = solve.getQuestion();
                    boothName = solve.getBoothName();
                    problemId = solve.getProblemId();
                }else {
                    statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<GetSolve> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버에 접속할 수 없습니다.",Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
    }
    public void Submission()
    {
        API service = Connecter.api;
        problemIdAnswer PA = new problemIdAnswer(problemId, answer);
        Call <PostSolve> call = service.postSolve("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1Mzk5NjU5NzIsIm5iZiI6MTUzOTk2NTk3MiwianRpIjoiNGY2YTAyNDUtMjRiZS00NDg1LWJhNmYtOTFhY2ZlMzZlMjQ2IiwiZXhwIjoxNTcxNTAxOTcyLCJpZGVudGl0eSI6Im5lcmQiLCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MifQ.ugcB9wpQJkAO3CwgKMX-vkU44OT97HAKEN8q_Po4MP8",boothName, PA);
        call.enqueue(new Callback<PostSolve>() {
            @Override
            public void onResponse(Call<PostSolve> call, Response<PostSolve> response) {
                if(response.isSuccessful()) {
                    result  = response.code();
                }else {
                    result  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<PostSolve> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버에 접속할 수 없습니다.",Toast.LENGTH_SHORT).show();
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
    @Override
    public void sendDataOX(boolean mOX[])
    {
        OX = mOX;
    }
    public void sendDataSelect(boolean mSelect[])
    {
        BSelect = mSelect;
    }
}