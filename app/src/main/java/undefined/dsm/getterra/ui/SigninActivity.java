package undefined.dsm.getterra.ui;

import android.app.Activity;
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
        final TextView emailerror = (TextView)findViewById(R.id.emailerror);
        final EditText emailet =(EditText)findViewById(R.id.eamil);
        final EditText passwordet = (EditText)findViewById(R.id.password);
        final EditText idet = (EditText)findViewById(R.id.user);

        emailerror.setText("");
        Button signin = (Button)findViewById(R.id.sign_in);
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
                    post(email,password,userid);
                    if(code == 201){
                        Toast.makeText(SigninActivity.this, "가입완료", Toast.LENGTH_SHORT).show();
                    }else if(code == 205){
                        emailerror.setText("중복id혹은 email");
                    }else{
                        codeerror = String.valueOf(code);
                        Toast.makeText(SigninActivity.this, codeerror, Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    emailerror.setText("E-mail의 형식이 잘못되었습니다.");
                }

            }
        });
    }
    public void post(String email,String password ,String userid){
        final API api = Connecter.INSTANCE.createApi();
        api.postSignin(email,password,userid);
        Call<Void> call = api.postSignin("id","email","password");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    code=response.code();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SigninActivity.this, "서버꺼짐", Toast.LENGTH_SHORT).show();

            }
        });
    }
}