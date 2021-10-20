package com.example.loginfirebase2018_0498

    import android.app.DatePickerDialog
    import android.content.Intent
    import android.content.res.Configuration
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.text.TextUtils
    import android.view.View
    import android.widget.DatePicker
    import android.widget.EditText
    import android.widget.Toast
    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.auth.FirebaseUser
    import com.google.firebase.database.DatabaseReference
    import com.google.firebase.database.FirebaseDatabase
    import java.time.Month
    import java.util.*


class RegisterActivity : AppCompatActivity() {
        private lateinit var txtnombre:EditText
        private lateinit var txtApellido:EditText
        private lateinit var txtTelefono:EditText
        private lateinit var txtcorreo:EditText
        private lateinit var txtcontrasena:EditText
        private lateinit var txtdireccion:EditText
        private lateinit var txtfecha:EditText
        private lateinit var dbreference:DatabaseReference
         private lateinit var auth: FirebaseAuth
        private lateinit var database:FirebaseDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtnombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtcorreo = findViewById(R.id.txtcorreo)
        txtcontrasena = findViewById(R.id.txtcontraseña)
        txtdireccion = findViewById(R.id.txtdireccion)
        txtfecha = findViewById(R.id.txtfecha)


        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        dbreference = database.reference.child("User")

    }





fun registrarse(view:View){
    createNewAccount()


}
private fun createNewAccount(){
    val name:String=txtnombre.text.toString()
    val lastname:String=txtApellido.text.toString()
    val telefono:String=txtTelefono.text.toString()
    val correo:String=txtcorreo.text.toString()
    val contrasena:String=txtcontrasena.text.toString()
    val direccion:String=txtdireccion.text.toString()
    val fecha:String=txtfecha.text.toString()


    if (!TextUtils.isEmpty(name) &&!TextUtils.isEmpty(lastname)&&
        !TextUtils.isEmpty(telefono)&& !TextUtils.isEmpty(correo)&&
        !TextUtils.isEmpty(contrasena)&& !TextUtils.isEmpty(direccion)
        && !TextUtils.isEmpty(fecha))

    auth.createUserWithEmailAndPassword(correo,contrasena)
        .addOnCompleteListener(this) {
            task ->
            if (task.isComplete){
                val user: FirebaseUser?=auth.currentUser
                verifyEmail(user)

                val userBD=dbreference.child(user?.uid.toString())

                userBD.child("Nombre").setValue(name)
                userBD.child("Apellido").setValue(lastname)
                userBD.child("Telefono").setValue(telefono)
                userBD.child("Correo").setValue(correo)
                userBD.child("Fecha").setValue(fecha)
                userBD.child("Direccion").setValue(direccion)
              action()
            }
        }

    }

    private fun action(){
startActivity(Intent(this,LoginActivity::class.java))
    }
private fun verifyEmail(user: FirebaseUser?){
    user?.sendEmailVerification()
        ?.addOnCompleteListener(this){
            task ->
            if (task.isComplete){
                Toast.makeText(this,"Email Enviado",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Error al enviar",Toast.LENGTH_LONG).show()
            }
        }
}
}


