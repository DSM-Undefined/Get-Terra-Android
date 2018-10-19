package undefined.dsm.getterra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class VioletteamActivity extends AppCompatActivity {

    Button violet_select;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violetteam);

        Intent intent_violet = new Intent(this.getIntent());

        violet_select = (Button) findViewById(R.id.teamviolet_btn);
        violet_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getBaseContext(), VioletteamQuizActivity.class);  //다음 실행될 액티비티 이름 적기!
                startActivity(intent1);
            }
        });

    }
}
