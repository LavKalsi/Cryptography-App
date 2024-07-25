package com.example.cryptographyapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class DecryptActivity : AppCompatActivity() {
    private lateinit var inputKey: EditText
    private lateinit var inputEncryptedText: EditText
    private lateinit var decryptedText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decrypt)

        inputKey = findViewById(R.id.inputKey)
        inputEncryptedText = findViewById(R.id.inputEncryptedText)
        decryptedText = findViewById(R.id.decryptedText)

        val decryptButton: LinearLayout = findViewById(R.id.decryptButton)
        val copyDecryptedButton: LinearLayout = findViewById(R.id.copyDecryptedButton)
        val goToEncryptActivity: LinearLayout = findViewById(R.id.goToEncryptActivity)
        val saveToHistory: LinearLayout = findViewById(R.id.saveToHistory)

        decryptButton.setOnClickListener {
            try {
                val key = Base64.decode(inputKey.text.toString(), Base64.DEFAULT)
                val decrypted = decrypt(inputEncryptedText.text.toString(), key)
                decryptedText.text = decrypted
            } catch (e: Exception) {
                decryptedText.text = "Decryption error"
                e.printStackTrace()
            }
        }

        copyDecryptedButton.setOnClickListener {
            copyToClipboard("Decrypted Text", decryptedText.text.toString())
        }

        goToEncryptActivity.setOnClickListener {
            val intent = Intent(this, EncryptActivity::class.java)
            startActivity(intent)
        }

        saveToHistory.setOnClickListener {
            saveToHistory("\nDecrypted: ${decryptedText.text}")
        }
    }

    @Throws(Exception::class)
    fun decrypt(encryptedData: String, key: ByteArray): String {
        val keySpec = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, keySpec)
        val decodedData = Base64.decode(encryptedData, Base64.DEFAULT)
        val decryptedData = cipher.doFinal(decodedData)
        return String(decryptedData)
    }

    private fun copyToClipboard(label: String, text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "$label copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    private fun saveToHistory(entry: String) {
        val sharedPreferences = getSharedPreferences("HistoryPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val historySet = sharedPreferences.getStringSet("history", mutableSetOf()) ?: mutableSetOf()
        historySet.add(entry)
        editor.putStringSet("history", historySet)
        editor.apply()
        Toast.makeText(this, "Saved to history", Toast.LENGTH_SHORT).show()
    }
}
