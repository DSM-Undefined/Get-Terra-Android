package undefined.dsm.getterra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GreenteamActivity  extends AppCompatActivity{

    Button green_select;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_greenteam);

            green_select = (Button)findViewById(R.id.teamgreen_btn);
            green_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(getBaseContext(), GreenteamQuizActivity.class);  //다음 실행될 액티비티 이름 적기!
                    startActivity(intent1);
                }
            });

        }
}
