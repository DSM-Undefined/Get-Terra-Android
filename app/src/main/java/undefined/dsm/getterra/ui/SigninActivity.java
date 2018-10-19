package undefined.dsm.getterra.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.justgo.Connecter.Connecter;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import undefined.dsm.getterra.R;
import undefined.dsm.getterra.connecter.API;

public class SigninActivity extends Activity {
    String password;
    String userid;
    String email;
    String codeerror;
    int code;
    static final String URL = "http://ec2.istruly.sexy:1234/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        final TextView emailerror = (TextView)findViewById(R.id.signin_emaile_tv);
        final EditText emailet =(EditText)findViewById(R.id.signin_email_btn);
        final EditText passwordet = (EditText)findViewById(R.id.sginin_pw_et);
        final EditText idet = (EditText)findViewById(R.id.signin_id_et);

        emailerror.setText("");
        Button signin = (Button)findViewById(R.id.sginin_sign_in_bt);
        signin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailet.getText().toString();
                userid = idet.getText().toString();
                password = passwordet.getText().toString();
                emailerror.setText("");
                if(email.getBytes().length<=0){
                    emailerror.setText("email을 입력해주세요");
                }
                if(email.matches("^[A-z|0-9]([A-z|0-9]*)(@)([A-z]*)(\\.)([A-z]*)$")){
                    //아이디비번설정 해야함
                        //서버랑연동
                    post(email,password,userid);/*
                    if(code == 201){
                        Toast.makeText(SigninActivity.this, "가입완료", Toast.LENGTH_SHORT).show();
                    }else if(code == 205){
                        emailerror.setText("중복id혹은 email");
                    }else{
                        codeerror = String.valueOf(code);
                        Toast.makeText(SigninActivity.this, codeerror, Toast.LENGTH_SHORT).show();
                    }*/
                }else
                {
                    emailerror.setText("E-mail의 형식이 잘못되었습니다.");
                }

            }
        });
    }
    public void post(String email,String password ,String userid){
        final TextView emailerror = (TextView)findViewById(R.id.signin_emaile_tv);
        final API api = Connecter.INSTANCE.createApi();
        JsonObject req = new JsonObject();
        req.addProperty("id",userid);
        req.addProperty("password",password);
        req.addProperty("email",email);
        api.postSignin(req);
        Call<Void> call = api.postSignin(req);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    code=response.code();
                    if(response.code()==201){
                        Toast.makeText(SigninActivity.this, "가입완료", Toast.LENGTH_SHORT).show();
                    }else if(response.code() == 205){
                        emailerror.setText("중복id혹은 email");
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SigninActivity.this, "서버꺼짐", Toast.LENGTH_SHORT).show();

            }
        });
    }
}