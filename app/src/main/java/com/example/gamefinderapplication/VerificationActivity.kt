package com.example.gamefinderapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class VerificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

            supportActionBar?.title = "Verification"

        val verifyCodeButton: Button = findViewById(R.id.verifyCodeButton)

        verifyCodeButton.setOnClickListener {
            // api call for verification code add later


            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}
