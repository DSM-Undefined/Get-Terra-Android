package undefined.dsm.getterra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.zxing.integration.android.IntentIntegrator;
import com.justgo.Connecter.Connecter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import undefined.dsm.getterra.R;
import undefined.dsm.getterra.connecter.API;

public class QrActivity extends AppCompatActivity {
    TextView list_qr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        list_qr=findViewById(R.id.list_qr);
        Button btn=findViewById(R.id.btn_qr);
        get();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(QrActivity.this).initiateScan();
            }
        });
    }
    public void get(){
        API retrofit=Connecter.retrofit.create(API.class);
        retrofit.getMap("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1Mzk5NjU5NzIsIm5iZiI6MTUzOTk2NTk3MiwianRpIjoiNGY2YTAyNDUtMjRiZS00NDg1LWJhNmYtOTFhY2ZlMzZlMjQ2IiwiZXhwIjoxNTcxNTAxOTcyLCJpZGVudGl0eSI6Im5lcmQiLCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MifQ.ugcB9wpQJkAO3CwgKMX-vkU44OT97HAKEN8q_Po4MP8")
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                       list_qr.append(response.body().getAsString());
                        if(response.body().getAsBoolean()==true){
                            //  내 색으로 변환
                        }
                        else if(response.body().getAsBoolean()==false){
                            //다른색으로 변환
                        }
                        else{
                            //이것도 위에와 동일하게
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {

                    }
                });
    }
}
