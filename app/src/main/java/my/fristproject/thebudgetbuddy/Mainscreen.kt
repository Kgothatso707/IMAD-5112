package my.firstproject.thebudgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import my.firstproject.thebudgetbuddy.DetailedViewScreen
import my.fristproject.thebudgetbuddy.MainActivity

class Mainscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainscreen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Data arrays representing days of the week, morning expenses, afternoon expenses, and expense notes.
        val Days = arrayOf<String>("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val Mornings = arrayOf<Int>(50, 2000, 41, 500, 84, 100, 15)  // Morning expenses (in currency)
        val Afternoons = arrayOf<Int>(6000, 435, 500, 799, 423, 1324, 60) // Afternoon expenses (in currency)
        val Expensenotes = arrayOf<String>(
            "Bought cupcakes and gave my wife weekly allowance",
            "Paid for petrol and dinner",
            "Bought breakfast and paid gym membership",
            "Paid for medication and wifi bill",
            "Bought Redbull and phone cover",
            "Gave my son money for washing the car and went out partying",
            "Donated money and bought wine"
        )

        // Initialize the button that will trigger the transition to the DetailedViewScreen.
        val btnViewDetails = findViewById<Button>(R.id.btnDetails)

        // Initialize the back button to navigate to MainActivity.
        val Back = findViewById<Button>(R.id.btnBack1)

        // Set the back button onClickListener to go back to MainActivity.
        Back.setOnClickListener {
            // Create an Intent to start MainActivity and navigate back.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Set the onClickListener for the "View Details" button to open DetailedViewScreen.
        btnViewDetails.setOnClickListener {
            // Create an Intent to transition to the DetailedViewScreen.
            val intent = Intent(this, DetailedViewScreen::class.java)

            // Pass the data (arrays) as extras to the new activity.
            intent.putExtra("Days", Days)
            intent.putExtra("Mornings", Mornings)
            intent.putExtra("Afternoons", Afternoons)
            intent.putExtra("Expensenotes", Expensenotes)

            // Start the DetailedViewScreen activity.
            startActivity(intent)
        }
    }
}
