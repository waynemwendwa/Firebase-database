package com.wayne.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName : EditText = findViewById(R.id.inputName)
        val inputEmail : EditText = findViewById(R.id.inputEmail)
        val inputAge : EditText = findViewById(R.id.inputAge)
        val buttonSave : Button = findViewById(R.id.buttonSave)

        val database = Firebase.database

        val refUsers = database.getReference("users")

        //refUsers.setValue("Faith")
        buttonSave.setOnClickListener {
            val name = inputName.text.toString().trim()
            val age = inputAge.text.toString().trim().toIntOrNull()
            val email = inputEmail.text.toString().trim()

            if (name.isNotEmpty() && age != null && email.isNotEmpty()){
                //save
                val user = User(name, age, email)
                refUsers.push().setValue(user)

                //clear inputs
                inputName.text.clear()
                inputAge.text.clear()
                inputEmail.text.clear()

                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()

            }
        }
    }

    data class User(val name:String = "", val age :Int = 0,val email:String = "")
}
