package com.example.loginfirebase2018_0498

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var txtcorreo : EditText
    private lateinit var txtcontrasena : EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtcorreo = findViewById(R.id.txtcorreo)
        txtcontrasena = findViewById(R.id.txtcontraseña)
        auth = FirebaseAuth.getInstance()

    }
    fun login(view:View) {
        loginUser()
    }

    fun forgotPassword(view: View){
        startActivity(Intent(this,ForgotPasswordActivity::class.java))
    }

    fun registrarse(view:View){
        startActivity(Intent(this,RegisterActivity::class.java))
    }
   private fun loginUser(){
      val user:String = txtcorreo.text.toString()
       val contrasena:String = txtcontrasena.text.toString()

       if (!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(contrasena) ) {

           auth.signInWithEmailAndPassword(user,contrasena)
               .addOnCompleteListener(this){
                       task ->
                   if(task.isSuccessful)
                   { action()
                   }
                   else{
                       Toast.makeText(this, "Error en la autentificacion", Toast.LENGTH_SHORT).show()
                   }
               }
       }

   }
    private fun action(){
        startActivity(Intent(this,MainActivity::class.java))
    }
}