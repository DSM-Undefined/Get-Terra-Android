package undefined.dsm.getterra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.integration.android.IntentIntegrator;

public class GreenteamQuizActivity extends AppCompatActivity {

    ImageView greenteam_qrcode;
    IntentIntegrator integrator = new IntentIntegrator(this);

    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greenteam_qrcode);

        Intent intent_green=new Intent(this.getIntent());

        greenteam_qrcode = (ImageView)findViewById(R.id.greenteam_qr);
        greenteam_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                integrator.setCaptureActivity(CaptureActivityAnyOrientation.class);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
                Log.d(this.getClass().getName(),"qr코드 실행");

            }
        });
    }
}
