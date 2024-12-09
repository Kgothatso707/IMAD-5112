package my.firstproject.thebudgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import my.fristproject.thebudgetbuddy.R

class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        val tvDay = findViewById<TextView>(R.id.tvDay)
        val tvMorning = findViewById<TextView>(R.id.tvMorning)
        val tvAfternoon = findViewById<TextView>(R.id.tvAfternoon)
        val tvExpense = findViewById<TextView>(R.id.tvExpense)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        val btnDisplay = findViewById<Button>(R.id.btnDisplay)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val btnHighest = findViewById<Button>(R.id.btnHightest)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Retrieve data from Intent (String arrays sent via intent)
        val days = intent.getStringArrayExtra("Days")?.toList() ?: emptyList()
        val mornings = intent.getStringArrayExtra("Mornings")?.toList() ?: emptyList()
        val afternoons = intent.getStringArrayExtra("Afternoons")?.toList() ?: emptyList()
        val expenses = intent.getStringArrayExtra("Expensenotes")?.toList() ?: emptyList()

        // Back button listener
        btnBack.setOnClickListener {
            val backIntent = Intent(this, Mainscreen::class.java)
            startActivity(backIntent)
        }

        // Display highest spending day
        btnHighest.setOnClickListener {
            if (mornings.size == afternoons.size) {
                val expensesArray = Array(mornings.size) { i ->
                    mornings[i].toDouble() + afternoons[i].toDouble() // Combine mornings and afternoons into total expenses
                }
                val maxExpense = expensesArray.maxOrNull()
                val maxIndex = expensesArray.indexOf(maxExpense)

                if (maxExpense != null && maxIndex >= 0) {
                    val maxDay = days.getOrNull(maxIndex)
                    tvResult.text = "The highest spending day is $maxDay with a total of $maxExpense"
                }
            }
        }

        // Display average spending per day
        btnAverage.setOnClickListener {
            val expenseAverages = Array(mornings.size) { i ->
                (mornings[i].toDouble() + afternoons[i].toDouble()) / 2.0
            }
            val avgResult = StringBuilder()
            for (i in days.indices) {
                avgResult.append("${days[i]}: Average spending = ${expenseAverages[i]}\n")
            }
            tvResult.text = avgResult.toString()
        }

        // Display data for days, mornings, afternoons, and expenses
        btnDisplay.setOnClickListener {
            val dayOutput = days.joinToString("\n")
            tvDay.text = dayOutput

            val morningOutput = mornings.joinToString("\n")
            tvMorning.text = morningOutput

            val afternoonOutput = afternoons.joinToString("\n")
            tvAfternoon.text = afternoonOutput

            val expenseOutput = expenses.joinToString("\n")
            tvExpense.text = expenseOutput
        }
    }
}
