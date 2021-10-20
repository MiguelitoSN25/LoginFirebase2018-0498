package com.example.loginfirebase2018_0498

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        txtEmail = findViewById(R.id.txtcorreo)
    }

fun send(view: View){
    val email=txtEmail.text.toString()

    if (!TextUtils.isEmpty(email)){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this){
                task ->

                if (task.isSuccessful)
                {
                    startActivity(Intent(this,LoginActivity::class.java))
                }else
                {
                    Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
}