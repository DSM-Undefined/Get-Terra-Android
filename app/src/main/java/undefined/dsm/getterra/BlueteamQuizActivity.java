package undefined.dsm.getterra;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

import undefined.dsm.getterra.ui.MainActivity;

public class BlueteamQuizActivity extends AppCompatActivity {

    ImageView blueteam_qrcode;
    IntentIntegrator integrator = new IntentIntegrator(this);

    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blueteam_qrcode);

        Intent intent_blue=new Intent(this.getIntent());

        blueteam_qrcode = (ImageView)findViewById(R.id.blueteam_qr);
        blueteam_qrcode.setOnClickListener(new View.OnClickListener() {
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


