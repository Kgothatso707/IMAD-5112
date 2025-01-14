package my.fristproject.thebudgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import my.firstproject.thebudgetbuddy.Mainscreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Get started button which leades the to the Maincsreen of the app
        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)
        val btnStop = findViewById<Button>(R.id.btnStop)
        btnGetStarted.setOnClickListener{
            intent= Intent(this, Mainscreen::class.java)
            startActivity(intent)
        }
        //Exit App
        btnStop.setOnClickListener {
            finish()
            System.out.close()
        }


    }
}