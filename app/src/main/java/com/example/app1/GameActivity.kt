package com.example.app1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.fragments.FragmentGame
import com.example.app1.fragments.FragmentGameOver

class GameActivity : AppCompatActivity(){

    var score = 0
    var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar?.hide()

        val currentPlayer = intent.getSerializableExtra("currentPlayer") as Player

        startGame()

        val backButton = findViewById<Button>(R.id.buttonBack)
        val restartButton = findViewById<Button>(R.id.buttonRestart)
        val currentPlayerTextView = findViewById<TextView>(R.id.textViewCurrentPlayer)

        backButton.setOnClickListener { finish() }
        restartButton.setOnClickListener { startGame() }
        currentPlayerTextView.text = currentPlayer.name
    }

    fun gameOver(){
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.container, FragmentGameOver(), "fragment")
        transaction.commit()
    }

    fun startGame(){
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag("fragment")

        if(fragment != null){
            transaction.replace(R.id.container, FragmentGame(), "fragment")
        }else{
            transaction.add(R.id.container, FragmentGame(), "fragment")
        }

        transaction.commit()
    }
}