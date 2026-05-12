package com.android.finalproject.screens.sub.history

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.app.WildQuestApp
import com.android.finalproject.data.database.WildQuestDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val app = application as WildQuestApp
        val userId = app.getUser().username.toIntOrNull() ?: 0

        val db = WildQuestDatabase.getDatabase(this)

        val historyListView = findViewById<ListView>(R.id.historyListView)
        val tvEmpty         = findViewById<TextView>(R.id.tvHistoryEmpty)

        val historyList = mutableListOf<String>()

        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,  // built-in single-text row
            historyList
        )

        historyListView.adapter = arrayAdapter

        // ── Load quiz attempts from Room database ────────────────
        // This is a suspend function so it must run inside a coroutine
        lifecycleScope.launch {
            val attempts = db.quizAttemptDao().getAttemptsByUser(userId)

            if (attempts.isEmpty()) {
                tvEmpty.visibility         = TextView.VISIBLE
                historyListView.visibility = ListView.GONE
            } else {
                tvEmpty.visibility         = TextView.GONE
                historyListView.visibility = ListView.VISIBLE

                // Format each attempt as a readable string for the plain ListView
                // Plain ListView can only show one string per row — so we combine
                // all the info into one formatted string
                val dateFormatter = SimpleDateFormat("MMM dd, yyyy  hh:mm a", Locale.getDefault())

                historyList.clear()
                attempts.forEach { attempt ->
                    val date     = dateFormatter.format(Date(attempt.dateTaken))
                    val mode     = attempt.mode.replace("_", " ").replaceFirstChar { it.uppercase() }
                    val category = attempt.category ?: "Official"
                    val score    = "${attempt.score} / ${attempt.totalScore}"
                    historyList.add("$mode  •  $score  •  $category\n$date")
                }

                // Tell the ListView to redraw with the new data
                arrayAdapter.notifyDataSetChanged()
            }
        }
    }
}