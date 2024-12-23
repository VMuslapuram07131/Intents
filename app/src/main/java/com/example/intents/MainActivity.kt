package com.example.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->

            if(result.resultCode == RESULT_OK){
                val replydata = result.data?.getStringExtra("replykey")?: "No Reply Recieved"
                Snackbar.make(this, findViewById(R.id.main), replydata, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun giveSurprise(view: View) {
        val til = findViewById<TextInputLayout>(R.id.textInputLayout)
        val name = til.editText?.text.toString()

//        val bundle = Bundle()
//        bundle.putString("name", name)

        val i = Intent(this, Second_Activity::class.java)
        i.putExtra("name", name)
//        startActivity(i)
        resultLauncher.launch(i)
    }

}