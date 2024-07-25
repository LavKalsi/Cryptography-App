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
import androidx.constraintlayout.widget.ConstraintSet
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class EncryptActivity : AppCompatActivity() {
    private lateinit var inputText: EditText
    private lateinit var encryptedText: TextView
    private lateinit var encryptionKey: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypt)

        inputText = findViewById(R.id.inputText)
        encryptedText = findViewById(R.id.encryptedText)
        encryptionKey = findViewById(R.id.encryptionKey)

        val encryptButton: LinearLayout = findViewById(R.id.encryptButton)
        val copyEncryptedButton: LinearLayout = findViewById(R.id.copyEncryptedButton)
        val copyKeyButton: LinearLayout = findViewById(R.id.copyKeyButton)
        val goToDecryptActivity: LinearLayout = findViewById(R.id.goToDecryptActivity)
        val saveToHistory: LinearLayout = findViewById(R.id.saveToHistory)

        encryptButton.setOnClickListener {
            try {
                val key = generateKey()
                val encrypted = encrypt(inputText.text.toString(), key)
                encryptedText.text = encrypted
                encryptionKey.text = Base64.encodeToString(key.encoded, Base64.DEFAULT)
            } catch (e: Exception) {
                encryptedText.text = "Encryption error"
                e.printStackTrace()
            }
        }

        copyEncryptedButton.setOnClickListener {
            copyToClipboard("Encrypted Text", encryptedText.text.toString())
        }

        copyKeyButton.setOnClickListener {
            copyToClipboard("Encryption Key", encryptionKey.text.toString())
        }

        goToDecryptActivity.setOnClickListener {
            val intent = Intent(this, DecryptActivity::class.java)
            startActivity(intent)
        }

        saveToHistory.setOnClickListener {
            saveToHistory("\nEncrypted Text: ${encryptedText.text} \nKey: ${encryptionKey.text}")
        }
    }

    private fun generateKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(256)
        return keyGen.generateKey()
    }

    @Throws(Exception::class)
    fun encrypt(data: String, key: SecretKey): String {
        val keySpec = SecretKeySpec(key.encoded, "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)
        val encryptedData = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(encryptedData, Base64.DEFAULT)
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
