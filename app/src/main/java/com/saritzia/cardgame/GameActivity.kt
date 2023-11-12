package com.saritzia.cardgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.saritzia.cardgame.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity: AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private val cards = arrayOf(R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6,
        R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,R.drawable.c11,R.drawable.c12,R.drawable.c13)
    private var score: Int = 0
    private var previousPosition: Int = getNewPosition()
    private var currentPosition: Int = previousPosition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.userName.text = intent.extras?.getString("userName") ?: "User"
        binding.scoreText.text = score.toString()
        getNewCard(previousPosition)
        binding.upButton.setOnClickListener {
            buttonTapped()
        }
        binding.downButton.setOnClickListener {
            buttonTapped()
        }
    }

    private fun getNewPosition(): Int {
       return Random.nextInt(0,cards.size)
    }

    private fun getNewCard(position: Int) {
        binding.card.setImageResource(cards[position])
    }

    private fun checkCurrentCardIsGreater(): Boolean {
        return currentPosition > previousPosition
    }

    private fun buttonTapped() {
        previousPosition = currentPosition
        currentPosition = getNewPosition()
        if(currentPosition == previousPosition) {
            Snackbar.make(binding.root,getString(R.string.sameCardText),Snackbar.LENGTH_SHORT).show()
            return
        }
        when{
            binding.upButton.callOnClick() -> {
                if (checkCurrentCardIsGreater()) {
                    score ++
                }else{
                    createGameOverSnackBar()
                }
            }
            binding.downButton.callOnClick() ->
                if (!checkCurrentCardIsGreater()) {
                    score ++
                }else{
                    createGameOverSnackBar()
                }
            }
        }

    private fun createGameOverSnackBar() {
        val snackbar = Snackbar.make(binding.root,"Game over, puntuación: $score",Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(getString(R.string.backLiteral)){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        snackbar.show()
    }
}