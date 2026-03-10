package com.example.project5_cs388_christopherlarena

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project6_cs388_christopherlarena.R

class MainActivity : AppCompatActivity() {

    private val waterViewModel: WaterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        // Handle window insets for edge-to-edge
        val mainView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize RecyclerView and Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = WaterAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up input fields and button
        val editTextAmount = findViewById<EditText>(R.id.editTextAmount)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val amountText = editTextAmount.text.toString()
            if (amountText.isNotEmpty()) {
                val amount = amountText.toInt()
                val entry = WaterEntry(amount = amount)
                waterViewModel.insert(entry)
                editTextAmount.text.clear()
            }
        }

        // Observe the water entries from the ViewModel
        waterViewModel.allEntries.observe(this) { entries ->
            entries?.let { adapter.submitList(it) }
        }
    }
}
