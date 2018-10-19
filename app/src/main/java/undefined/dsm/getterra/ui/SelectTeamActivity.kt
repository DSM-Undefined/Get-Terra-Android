package undefined.dsm.getterra.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_select_team.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import undefined.dsm.getterra.R

class SelectTeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_team)

        selectTeam_blue_btn.onClick {

        }
    }
}
