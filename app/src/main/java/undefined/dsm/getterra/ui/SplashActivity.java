package undefined.dsm.getterra.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import undefined.dsm.getterra.R;

import static com.justgo.Util.PrefManagerKt.getTeamStatus;
import static com.justgo.Util.PrefManagerKt.getToken;

public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
        Log.d("Sibal",getToken(getBaseContext(), true));
        if (!getToken(getBaseContext(), true).equals("Bearer ") && getTeamStatus(getBaseContext()).equals("ok")) {
            Intent mainIntent = new Intent(SplashActivity.this, QrActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        } else if (!getToken(getBaseContext(), true).equals("Bearer ") && getTeamStatus(getBaseContext()).equals("")) {
            Intent mainIntent = new Intent(SplashActivity.this, SelectTeamActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        } else if (getToken(getBaseContext(), true).equals("Bearer ") && getTeamStatus(getBaseContext()).equals("")) {
            Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        } else {
            Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        }

//            }
//        }, SPLASH_DISPLAY_LENGTH);
        /*super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        finish();*/

    }
}
