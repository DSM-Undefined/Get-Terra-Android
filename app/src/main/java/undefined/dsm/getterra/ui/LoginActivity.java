package undefined.dsm.getterra.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.justgo.Connecter.Connecter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import undefined.dsm.getterra.R;
import undefined.dsm.getterra.connecter.API;


public class LoginActivity extends Activity {
    String userid;
    String password;
    int code;

    public void post(String userid, String password){

        API api;
        api = Connecter.INSTANCE.createApi();
        api.postLogin(userid,password);
        Call<Void> call = api.postLogin("id","password");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    code=response.code();
                }
                //Toast.makeText(LoginActivity.this, "서버켜짐", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "서버꺼짐", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         final EditText idet = (EditText)findViewById(R.id.editText);
         final EditText passwordet = (EditText)findViewById(R.id.editText2);
        final TextView IdError = (TextView)findViewById(R.id.iderror);
        final TextView PasswordError = (TextView)findViewById(R.id.passworderror);
        IdError.setText("");
        PasswordError.setText("");

        Button signinB = (Button)findViewById(R.id.button2);
        signinB.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                        startActivity(intent);
            }
        });

        Button login = (Button)findViewById(R.id.buttonlog);
        login.setOnClickListener((new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                userid = idet.getText().toString();
                password = passwordet.getText().toString();
                userid = userid.trim();
                password = password.trim();
                IdError.setText("");
                PasswordError.setText("");
                if(userid.getBytes().length<=0|password.getBytes().length<=0){
                    IdError.setText("아이디 혹은 비밀번호를를 입력하시오");
                }else {
                    post(userid,password);
                    if(code == 201){
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    }else if(code == 401) {
                        IdError.setText("잘못된 ID입니다.");
                        PasswordError.setText("잘못된 PW입니다.");
                    }/*else{
                        codeerror = String.valueOf(code);
                        Toast.makeText(LoginActivity.this, codeerror, Toast.LENGTH_SHORT).show();
                    }
                    IdError.setText(userid);
                    PasswordError.setText(password);*/
                }
            }
        }));
    }
}