package undefined.dsm.getterra.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import undefined.dsm.getterra.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent : Intent = Intent(this,QuizMainActivity::class.java)
        startActivity(intent)
    }
}