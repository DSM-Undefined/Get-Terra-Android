package undefined.dsm.getterra.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_select_team.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import undefined.dsm.getterra.R

class SelectTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_team)
        val intent = Intent(this, TeamDetailActivity::class.java)
        selectTeam_blue_btn.onClick {
            intent.putExtra("team",0)
            startActivity(intent)
        }
        selectTeam_green_btn.onClick {
            intent.putExtra("team",1)
            startActivity(intent)
        }
        selectTeam_yellow_btn.onClick {
            intent.putExtra("team",2)
            startActivity(intent)
        }
        selectTeam_violet_btn.onClick {
            intent.putExtra("team",3)
            startActivity(intent)
        }
    }
}
