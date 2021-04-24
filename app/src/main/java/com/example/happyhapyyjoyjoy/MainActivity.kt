package com.example.happyhapyyjoyjoy

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numSides = 6
        val dicesymbols: String = "⚀⚁⚂⚃⚄⚅"
        val rollButton: Button = findViewById(R.id.buttonRoll)
        val numDiceBox: TextView = findViewById(R.id.editTextNumber)
        val dicefaceTextView: TextView = findViewById(R.id.textViewDice)

        suspend fun rollthedice(){
            dicefaceTextView.textSize = (30.0).toFloat()
            dicefaceTextView.text = "Rolling"
            rollButton.isEnabled = false
            delay(600)
            val numDice = numDiceBox.text.toString().toInt()
            val randomvalues = (1..numDice).map { (0 until numSides).random() }

            var textsize = 80.0 * (8.0 / numDice.toFloat())
            if (textsize > 100.0)
                textsize = 100.0
            if (textsize < 18.0)
                textsize = 18.0
            dicefaceTextView.textSize= textsize.toFloat()

            dicefaceTextView.text = randomvalues.map {dicesymbols.get(it) } .joinToString("")
            delay(400)
            rollButton.isEnabled = true
        }


        rollButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main){
                rollthedice()
            }
        }
//

    }

}