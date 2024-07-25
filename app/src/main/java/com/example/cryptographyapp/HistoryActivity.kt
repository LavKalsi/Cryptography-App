package com.example.cryptographyapp

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var historyAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listView = findViewById(R.id.historyListView)
        val history = getHistory()

        historyAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, history)
        listView.adapter = historyAdapter
    }

    private fun getHistory(): MutableList<String> {
        val sharedPreferences = getSharedPreferences("HistoryPrefs", Context.MODE_PRIVATE)
        val historySet = sharedPreferences.getStringSet("history", setOf()) ?: setOf()
        return historySet.toMutableList()
    }
}
