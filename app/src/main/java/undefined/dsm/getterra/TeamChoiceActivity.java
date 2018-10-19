package undefined.dsm.getterra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import undefined.dsm.getterra.ui.MainActivity;

public class TeamChoiceActivity extends AppCompatActivity{

    Button team1, team2, team3, team4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamchoice);
        Log.d(this.getClass().getName(),"시작");

        team1 = (Button) findViewById(R.id.teamChoice_teamBlue_btn);
        team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(), BlueteamActivity.class);  //다음 실행될 액티비티 이름 적기!
                startActivity(intent1);
                Log.d(this.getClass().getName(),"1번");
            }
        });
        team2 = (Button)findViewById(R.id.teamChoice_teamGreen_btn);
        team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getBaseContext(), GreenteamActivity.class);  //다음 실행될 액티비티 이름 적기!
                startActivity(intent2);
                Log.d(this.getClass().getName(),"2번");
            }
        });
        team3 = (Button)findViewById(R.id.teamChoice_teamYellow_btn);
        team3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getBaseContext(), YellowteamActivity.class);  //다음 실행될 액티비티 이름 적기!
                startActivity(intent3);
                Log.d(this.getClass().getName(),"3번");
            }
        });
        team4 = (Button)findViewById(R.id.teamChoice_teamViolet_btn);
        team4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getBaseContext(), VioletteamActivity.class);  //다음 실행될 액티비티 이름 적기!
                startActivity(intent4);
                Log.d(this.getClass().getName(),"4번");
            }
        });
    }


}
