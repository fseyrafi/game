package com.example.myapplication

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.game.R
import com.example.game.databinding.ActivityMainBinding
import com.example.game.databinding.ActivityMainBinding.inflate
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var button2: Button
    lateinit var button: Button
    lateinit var button3: Button
    lateinit var button1: Button
    lateinit var button4: Button
    lateinit var textViewScore: TextView
    var random0100 = 0
    var random010 = 0
    var leftOver = 0
    var num1 = 0
    var num2 = 0
    var num3 = 0
    var score = 0
    var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        buttonsColor()
        binding.textViewScore.text = score.toString()


        binding.button.setOnClickListener {
            binding.button2.isEnabled = true
            binding.button3.isEnabled = true
            binding.button4.isEnabled = true
            binding.button1.isEnabled = true


            if (cont < 5) {
                genratRandomNumber()
                buttonsColor()
                cont++
            } else {
                showDialog()
            }
        }
        binding.button1.setOnClickListener {
            binding.button2.isEnabled = false
            binding.button3.isEnabled = false
            binding.button4.isEnabled = false

            if (checkAnswer(binding.button1.text.toString())) {
                binding.button1.setBackgroundColor(Color.GREEN)
                score += 5
                binding.textViewScore.text = score.toString()

            } else {
                binding.button1.setBackgroundColor(Color.RED)
                score -= 2
                binding.textViewScore.text = score.toString()
            }
        }
        binding.button2.setOnClickListener {
            binding.button1.isEnabled = false
            binding.button3.isEnabled = false
            binding.button4.isEnabled = false
            if (checkAnswer(binding.button2.text.toString())) {
                binding.button2.setBackgroundColor(Color.GREEN)
                score += 5
                binding.textViewScore.text = score.toString()
            } else {
                binding.button2.setBackgroundColor(Color.RED)
                score -= 2
                binding.textViewScore.text = score.toString()
            }
        }
        binding.button3.setOnClickListener {
            binding.button2.isEnabled = false
            binding.button1.isEnabled = false
            binding.button4.isEnabled = false
            if (checkAnswer(binding.button3.text.toString())) {
                binding.button3.setBackgroundColor(Color.GREEN)
                score += 5
                binding.textViewScore.text = score.toString()
            } else {
                binding.button3.setBackgroundColor(Color.RED)
                score -= 2
                binding.textViewScore.text = score.toString()
            }
        }
        binding.button4.setOnClickListener {
            binding.button2.isEnabled = false
            binding.button3.isEnabled = false
            binding.button1.isEnabled = false
            if (checkAnswer(binding.button4.text.toString())) {
                binding.button4.setBackgroundColor(Color.GREEN)
                score += 5
                binding.textViewScore.text = score.toString()
            } else {
                binding.button4.setBackgroundColor(Color.RED)
                score -= 2
                binding.textViewScore.text = score.toString()
            }
        }


    }

    fun checkOption() {
        while (true) {
            num1 = (1..9).random()
            if (num1 != leftOver) {
                break
            }
        }
        while (true) {
            num2 = (1..9).random()
            if (num2 != leftOver && num2 != num1) {
                break
            }
        }
        while (true) {
            num3 = (1..9).random()
            if (num3 != leftOver && num3 != num2 && num3 != num1) {
                break
            }
        }
    }

    fun genratRandomNumber() {
        random0100 = (0..100).random()
        binding.random0100.text = random0100.toString()
        random010 = (1..9).random()
        binding.random010.text = random010.toString()
        leftOver = random0100 % random010
        checkOption()

        val random1_4 = (1..4).random()

        when (random1_4) {
            1 -> {
                binding.button1.text = leftOver.toString()
                binding.button2.text = num1.toString()
                binding.button3.text = num2.toString()
                binding.button4.text = num3.toString()
            }
            2 -> {
                binding.button2.text = leftOver.toString()
                binding.button1.text = num1.toString()
                binding.button3.text = num2.toString()
                binding.button4.text = num3.toString()
            }
            3 -> {
                binding.button3.text = leftOver.toString()
                binding.button2.text = num1.toString()
                binding.button1.text = num2.toString()
                binding.button4.text = num3.toString()
            }
            4 -> {
                binding.button4.text = leftOver.toString()
                binding.button2.text = num1.toString()
                binding.button3.text = num2.toString()
                binding.button1.text = num3.toString()
            }
        }
    }

    fun checkAnswer(textButton: String): Boolean {
        return textButton == leftOver.toString()
    }

    fun buttonsColor() {
        binding.button1.setBackgroundColor(Color.GRAY)
        binding.button2.setBackgroundColor(Color.GRAY)
        binding.button3.setBackgroundColor(Color.GRAY)
        binding.button4.setBackgroundColor(Color.GRAY)
    }

    fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
// set message of alert dialog
        dialogBuilder.setMessage("امتیاز شما : $score")
// if the dialog is cancelable
            .setCancelable(true)
// positive button text and action
            .setPositiveButton("تایید", DialogInterface.OnClickListener { dialog, id ->
                random0100 = 0
                random010 = 0
                leftOver = 0
                num1 = 0
                num2 = 0
                num3 = 0
                score = 0
                cont = 0

            })
            .setNegativeButton("خروج ", DialogInterface.OnClickListener { dialog, id ->
                finish()
            })

// create dialog box
        val alert = dialogBuilder.create()
// set title for alert dialog box
        alert.setTitle("بازی تمام شد")
// show alert dialog
        alert.show()
    }


}