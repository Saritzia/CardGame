package com.saritzia.cardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*
import com.saritzia.cardgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startButtonTapped()
    }

    private fun startButtonTapped() {
        val userName = binding.userEditText.text
        binding.startButton.setOnClickListener {
            createSnackBar(userName)
        }
    }
    private fun createSnackBar(userName: Editable) {
        val snackbar: Snackbar
        if (userName.isNotEmpty()) {
            snackbar = make(binding.root, "@userName.toSring()@string/startMessage",
                LENGTH_INDEFINITE
            )
            snackbar.setAction("@string/okLiteral",View.OnClickListener {
                navigateToNextActivity(userName.toString())
            })
            snackbar.show()
        }else {
            snackbar = make(binding.root,"@string/emptyUserSnackBar", LENGTH_SHORT)
            snackbar.show()
        }
    }

    private fun navigateToNextActivity(userName: String) {
        val intent = Intent(applicationContext, GameActivity::class.java)
        intent.putExtra("userName", userName)
        startActivity(intent)
    }
}