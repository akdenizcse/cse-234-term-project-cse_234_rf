package com.example.gamefinderapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val newPasswordEditText: EditText = findViewById(R.id.editNewPassword)
        val confirmPasswordEditText: EditText = findViewById(R.id.editConfirmPassword)
        val resetPasswordButton: Button = findViewById(R.id.resetPasswordButton)

        resetPasswordButton.setOnClickListener {
            Log.d("ResetPasswordActivity", "Reset password button clicked")
            val newPassword = newPasswordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            Log.d("ResetPasswordActivity", "New Password: '$newPassword', Confirm Password: '$confirmPassword'")

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Log.d("ResetPasswordActivity", "One or both password fields are empty")
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            } else if (validatePasswords(newPassword, confirmPassword)) {
                Log.d("ResetPasswordActivity", "Passwords validated")

                // Call API to reset the password (logic to be added later)

                // Navigate to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Log.d("ResetPasswordActivity", "Navigating to LoginActivity")
                finish() // Optional: finish this activity to prevent going back to it
            } else {
                Log.d("ResetPasswordActivity", "Password validation failed")
                Toast.makeText(this, "Passwords do not match or are too short", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validatePasswords(newPassword: String, confirmPassword: String): Boolean {
        return newPassword == confirmPassword && newPassword.length >= 6
    }
}
