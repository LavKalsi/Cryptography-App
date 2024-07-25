package com.example.cryptographyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goToEncryptActivity: LinearLayout = findViewById(R.id.goToEncryptActivity)
        val goToDecryptActivity: LinearLayout = findViewById(R.id.goToDecryptActivity)
        val viewHistory: LinearLayout = findViewById(R.id.viewHistory)

        goToEncryptActivity.setOnClickListener {
            val intent = Intent(this, EncryptActivity::class.java)
            startActivity(intent)
        }

        goToDecryptActivity.setOnClickListener {
            val intent = Intent(this, DecryptActivity::class.java)
            startActivity(intent)
        }

        viewHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}
