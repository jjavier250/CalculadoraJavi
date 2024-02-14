package activitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.calculadorajavi.R


class MainActivityDescripcion : AppCompatActivity() {

    lateinit var txtalturap2:TextView
    lateinit var txtpeso2:TextView
    lateinit var txtIMC:TextView
    lateinit var txtdescripciones:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_descripcion)

        actividad()

    }

    fun actividad(){



        txtalturap2=findViewById(R.id.txtalturap2)
        txtpeso2=findViewById(R.id.txtpeso2)
        txtIMC=findViewById(R.id.txtIMC)
        txtdescripciones=findViewById(R.id.txtdescripciones)


        val parametroAltura:String=intent.getStringExtra("Altura").toString()

        txtalturap2.text=parametroAltura


        val parametropeso:String=intent.getStringExtra("Peso").toString()
        txtpeso2.text=parametropeso

        val pamaetroimc:String=intent.getStringExtra("imc").toString()
        txtIMC.text= "Indice de masa corporal: " + pamaetroimc

        val parametrodesc:String=intent.getStringExtra("desc").toString()
        txtdescripciones.text=parametrodesc




    }
}