package com.example.kreatorzamowieniakawy

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.properties.Delegates
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var nazwaKawy: String = ""
    private var cukier: String = "Nie"
    private var mleko: String = "Nie"
    private var ilosc = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val zdjecia = findViewById<ImageView>(R.id.images)
        val image = listOf(
            R.drawable.capuccino,
            R.drawable.espresso,
            R.drawable.latte
        )
        val kawy = findViewById<RadioGroup>(R.id.coffee_type)

        kawy.setOnCheckedChangeListener { _, checkedId ->
            val kawa = findViewById<RadioButton>(checkedId)
            if (kawa.text == "Cappuccino") {
                zdjecia.setImageResource(image[0])
                nazwaKawy = "Cappuccino"
            } else if (kawa.text == "Espresso") {
                nazwaKawy = "Espresso"
                zdjecia.setImageResource(image[1])
            } else if (kawa.text == "Latte") {
                nazwaKawy = "Latte"
                zdjecia.setImageResource(image[2])
            }
        }

        val cukierCheckBox = findViewById<CheckBox>(R.id.sugar)
        val mlekoCheckBox = findViewById<CheckBox>(R.id.milk)

        cukierCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                cukier = "Tak"
            } else {
                cukier = "Nie"
            }
        }
        mlekoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mleko = "Tak"
            } else {
                mleko = "Nie"
            }
        }

        val iloscKawNapis = findViewById<TextView>(R.id.iloscKaw)
        val iloscKaw = findViewById<SeekBar>(R.id.number)
        ilosc = 0
        iloscKaw.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                iloscKawNapis.text = "Ilość kaw: $progress"
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                ilosc = seekBar.progress
            }
        })

        val zamowB = findViewById<Button>(R.id.myButton)
        zamowB.setOnClickListener {
            zamow()
        }

    }

    private fun zamow() {
        val zamowienie = findViewById<TextView>(R.id.zamowienie)
        zamowienie.text =
            "Zamówiona kawa: $nazwaKawy,\nCukier: $cukier,\nMleko: $mleko,\nIlość: $ilosc"
        Toast.makeText(this, "Zamówienie zlożone", Toast.LENGTH_SHORT).show()
    }


}