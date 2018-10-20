package undefined.dsm.getterra.ui

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.google.gson.JsonArray
import com.justgo.Connecter.Connecter
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import undefined.dsm.getterra.R

class TeamDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        val intent = getIntent()
        val type = intent.getIntExtra("team", 0)
        val api = Connecter.createApi()
        api.getTeam("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1Mzk5NjU5NzIsIm5iZiI6MTUzOTk2NTk3MiwianRpIjoiNGY2YTAyNDUtMjRiZS00NDg1LWJhNmYtOTFhY2ZlMzZlMjQ2IiwiZXhwIjoxNTcxNTAxOTcyLCJpZGVudGl0eSI6Im5lcmQiLCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MifQ.ugcB9wpQJkAO3CwgKMX-vkU44OT97HAKEN8q_Po4MP8")
                .enqueue(object : Callback<JsonArray> {
                    override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                        val body = response.body()
                        body?.forEach {
                            teamDetail_list_tv.append(it.asString)
                        }
                    }

                    override fun onFailure(call: Call<JsonArray>, t: Throwable) {

                    }

                })
        when (type) {
            0 -> {
                teamDetail_card.setCardBackgroundColor(Color.parseColor("#a4d2e6"))
                teamDetail_ellipse_view.background = ContextCompat.getDrawable(baseContext, R.drawable.back_ellipse_blue)
                teamDetail_team_tv.text = "Team_Blue"
            }
            1 -> {
                teamDetail_card.setCardBackgroundColor(Color.parseColor("#a4e6ab"))
                teamDetail_ellipse_view.background = ContextCompat.getDrawable(baseContext, R.drawable.back_ellipse_green)
                teamDetail_team_tv.text = "Team_Green"
            }
            2 -> {
                teamDetail_card.setCardBackgroundColor(Color.parseColor("#ffec8f"))
                teamDetail_ellipse_view.background = ContextCompat.getDrawable(baseContext, R.drawable.back_ellipse_yellow)
                teamDetail_team_tv.text = "Team_Yellow"
            }
            3 -> {
                teamDetail_card.setCardBackgroundColor(Color.parseColor("#d398ec"))
                teamDetail_ellipse_view.background = ContextCompat.getDrawable(baseContext, R.drawable.back_ellipse_violet)
                teamDetail_team_tv.text = "Team_Violet"
            }
        }

        teamDetail_select_btn.onClick {
            api.postTeam("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1Mzk5NjU5NzIsIm5iZiI6MTUzOTk2NTk3MiwianRpIjoiNGY2YTAyNDUtMjRiZS00NDg1LWJhNmYtOTFhY2ZlMzZlMjQ2IiwiZXhwIjoxNTcxNTAxOTcyLCJpZGVudGl0eSI6Im5lcmQiLCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MifQ.ugcB9wpQJkAO3CwgKMX-vkU44OT97HAKEN8q_Po4MP8", type.toString())
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {

                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                        }

                    })

        }
    }
}
