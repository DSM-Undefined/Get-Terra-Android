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
import undefined.dsm.getterra.connecter.problemIdAnswer;

import static com.justgo.Util.PrefManagerKt.getToken;

public class QuizMainActivity extends AppCompatActivity implements OXFragment.SendDataOX, SelectFragment.SendDataSelect {
    String boothName = "시나브로";
    String question = "문제입니당";
    int problemId = 0;
    String answer = "답";
    String choices[] = new String[4];
    int statusCode;
    GetSolve solve;
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
        Intent intent = getIntent();
        boothName = intent.getStringExtra("boothName");
        _QuizMainActivity = QuizMainActivity.this;

        _problemType = findViewById(R.id.quiz_problemtype_tv);
        _question = findViewById(R.id.quiz_problem_tv);
        userInput = findViewById(R.id.quiz_userInput_et);
        clubName = findViewById(R.id.quiz_clubname_tv);

        nextActivity = findViewById(R.id.quiz_answer_tv);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (_problemTypetest) {
                    case 0:
                        answer = userInput.getText().toString();
                        if (answer.length() == 0) {
                            Toast.makeText(getApplicationContext(), "정답을 입력해주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    case 1:
                        for (int i = 0; i < 4; i++) {
                            if (BSelect[i] == true) {
                                answer = Integer.toString(i);
                                break;
                            }
                        }
                    case 2: {
                        if (OX[0] == true) {
                            answer = "O";
                        } else if (OX[1] == true) {
                            answer = "X";
                        }
                    }
                }
                Submission();
                Intent intent = new Intent(getApplicationContext(), CorrectionMainActivity.class);
                switch (result) {
                    case 201:
                        intent.putExtra("isAnswerTrue", true);
                        intent.putExtra("boothName", boothName);
                        startActivity(intent);
                        break;
                    case 204:
                        intent.putExtra("isAnswerTrue", false);
                        intent.putExtra("boothName", boothName);
                        startActivity(intent);
                        break;
                }
            }

            //retrofit

        });
        quizSet();
    }

    public void QuizActivitySet() {
        switch (_problemTypetest) {
            case 0: {
                _problemType.setText("주관식");
                fr = new SubjectFragment();
                userInput.setVisibility(View.VISIBLE);

                break;
            }
            case 1: {
                _problemType.setText("객관식");
                fr = new SelectFragment();
                Bundle bundle = new Bundle();
                bundle.putString("f_sel", choices[0]);
                bundle.putString("s_sel", choices[1]);
                bundle.putString("t_sel", choices[2]);
                bundle.putString("o_sel", choices[3]);
                fr.setArguments(bundle);
                userInput.setVisibility(View.GONE);
                break;
            }
            case 2: {
                _problemType.setText("O/X");
                fr = new OXFragment();
                userInput.setVisibility(View.GONE);
                break;
            }
        }
        _question.setText(question);
        clubName.setText(boothName);
        FragmentSet();
    }

    public void quizSet() {
        API service = Connecter.api;
        Call<GetSolve> call = service.getSolve(getToken(getBaseContext(), true), boothName);
        call.enqueue(new Callback<GetSolve>() {
            @Override
            public void onResponse(Call<GetSolve> call, Response<GetSolve> response) {
                if (response.code() == 200) {
                    solve = response.body();
                    _problemTypetest = solve.getProblemType();
                    question = solve.getContent();
                    boothName = solve.getBoothName();
                    problemId = solve.get_id();
                    choices = solve.getChoices();
                    QuizActivitySet();
                } else {
                    Toast.makeText(QuizMainActivity.this, "이미 점령한 구역입니다.", Toast.LENGTH_SHORT).show();
                    finish();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<GetSolve> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버에 접속할 수 없습니다.", Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
    }

    public void Submission() {
        API service = Connecter.api;
        problemIdAnswer PA;
        if (_problemTypetest == 1) {
            PA = new problemIdAnswer(problemId, choices[Integer.parseInt(answer)]);
        } else {
            PA = new problemIdAnswer(problemId, answer);
        }

        Call<Void> call = service.postSolve(getToken(getBaseContext(), true), boothName, PA);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    result = response.code();
                    finish();
                } else {
                    result = response.code();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버에 접속할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void FragmentSet() {
        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragment.beginTransaction();
        fragmentTransaction.replace(R.id.quiz_fragment_fg, fr);
        fragmentTransaction.commit();
    }

    public void backFromQuizMain(View v) {
        finish();
    }

    @Override
    public void sendDataOX(boolean mOX[]) {
        OX = mOX;
    }

    public void sendDataSelect(boolean mSelect[]) {
        BSelect = mSelect;
    }
}