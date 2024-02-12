package activitis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.calculadorajavi.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    var peso:Int=60
    var altura:Int=60

    lateinit var txtpeso: TextView
    lateinit var txtaltura:TextView
    lateinit var buttonmenos:FloatingActionButton
    lateinit var buttonmas:FloatingActionButton
    lateinit var descripcion:TextView
    lateinit var texttotal:TextView
    lateinit var calcularIMC: AppCompatButton
    lateinit var seekBar: SeekBar
    lateinit var detalle:AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val userEdittext:EditText=findViewById(R.id.edittext1)


        initView()

    }
    fun initView(){



         txtpeso = findViewById(R.id.txtpeso)
         txtaltura=findViewById(R.id.txtaltura)
         buttonmenos=findViewById(R.id.buttonmenos)
         buttonmas=findViewById(R.id.buttonmas)
         descripcion=findViewById(R.id.descripcion)
         texttotal=findViewById(R.id.texttotal)
         calcularIMC=findViewById(R.id.calcularIMC)
         seekBar=findViewById(R.id.seekBar)
         detalle=findViewById(R.id.detalle)




        buttonmenos.setOnClickListener {
            peso--
            txtpeso.text = "Peso: " + peso.toString() + " Kg"
        }

        buttonmas.setOnClickListener {
            peso++
            txtpeso.text ="Peso: " + peso.toString() + " Kg"
        }

        txtpeso.text = "Peso: " + peso.toString() + " Kg"

       // txtpeso.text = getString(R.string.Boton_calcular_IMC)--> para recuperar del ficghero string .xml

        texttotal.text="0.0"

        calcularIMC.setOnClickListener {
            funcioncalcularIMC()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Actualizar el TextView con el valor seleccionado
                txtaltura.text = "Altura: $progress  cm"
                altura=progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Método requerido, pero no se utiliza en este ejemplo
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Método requerido, pero no se utiliza en este ejemplo
            }
        })

        detalle.setOnClickListener(){
            val intent = Intent(this, MainActivityDescripcion::class.java)
            startActivity(intent)



        }

    }



    fun funcioncalcularIMC(){
        var vtotal:Float
        var valtura:Float

        valtura=altura.toFloat()/100
        valtura=valtura.toFloat().pow(2)
        vtotal=peso/valtura.toFloat()


        texttotal.text=vtotal.toString()


        when(vtotal){

                in 0.0 ..18.5->{
                    descripcion.text="Resultado: Bajo peso"
                    descripcion.setTextColor(getColor(R.color.black))

                }

                     in 18.5 ..24.9->{
                         descripcion.text="Resultado: peso saludable"
                     }
                     in 24.9..29.9->{
                         descripcion.text="Resultado:sobrepeso"
                     }

                else->{
                    descripcion.text="Resultado: obesidad"
                }
        }



    }

}
