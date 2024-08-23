package com.canerbulut.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    // RecyclerView için kullanılacak liste ve MainActivity2 ye aktarım yapıldı.
    private val Liste = mutableListOf<String>()
    private lateinit var aktarma: MainActivity2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // RecyclerView'u tanımlayıp aktarım
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        aktarma = MainActivity2(Liste)
        recyclerView.adapter = aktarma

        // Butonu aktif eddim
        val button = findViewById<FloatingActionButton>(R.id.button)
        button.setOnClickListener {
            showBottomSheetDialog()
        }

    }

    private fun showBottomSheetDialog() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.ekle, null)
        dialog.setContentView(view)

        val kaydetButton = view.findViewById<Button>(R.id.KaydetButton)
        val yeniText = view.findViewById<EditText>(R.id.YeniText)

        kaydetButton?.setOnClickListener {
            val text = yeniText?.text.toString()
            if (text.isNotEmpty()) {
                Liste.add(text)
                aktarma.notifyItemInserted(Liste.size - 1)
                dialog.dismiss()
            }
        }

        dialog.show()

    }
}