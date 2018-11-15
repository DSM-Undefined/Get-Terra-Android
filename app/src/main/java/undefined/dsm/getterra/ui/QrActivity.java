package undefined.dsm.getterra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.justgo.Connecter.Connecter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import undefined.dsm.getterra.R;
import undefined.dsm.getterra.connecter.API;
import undefined.dsm.getterra.model.ListModle;
import undefined.dsm.getterra.util.QrAdapter;

import static com.justgo.Util.PrefManagerKt.getToken;

public class QrActivity extends AppCompatActivity {
    RecyclerView listQr;
    QrAdapter qrAdapter;
    ArrayList<ListModle> listModles = new ArrayList<>();
    IntentIntegrator qrscan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        listQr = findViewById(R.id.list_qr);
        Button btn = findViewById(R.id.btn_qr);
        qrAdapter = new QrAdapter(listModles);
        listQr.setHasFixedSize(true);
        listQr.setLayoutManager(new LinearLayoutManager(this));
        listQr.setAdapter(qrAdapter);
        qrscan = new IntentIntegrator(this);
        get();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrscan.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(QrActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                String boothname = result.getContents();
                Toast.makeText(QrActivity.this, boothname, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QrActivity.this, QuizMainActivity.class);
                intent.putExtra("boothName", boothname);
                startActivity(intent);
            }
        }
    }

    public void get() {
        API retrofit = Connecter.retrofit.create(API.class);
        retrofit.getMap(getToken(getBaseContext(), true))
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        int type = response.body().get("myTeam").getAsInt();
                        JsonArray map = response.body().get("map").getAsJsonArray();
                        for (JsonElement element : map) {
                            listModles.add(new ListModle(element.getAsJsonArray().get(0).getAsString(), element.getAsJsonArray().get(1).getAsBoolean(), type));
                        }
                        listQr.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
    }
}

