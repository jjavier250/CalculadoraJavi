package activitis

import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.calculadorajavi.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToLong


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
            //llama a la ventana sin parametros


            intent.putExtra("Altura",txtaltura.text.toString())
            intent.putExtra("Peso",txtpeso.text.toString())
            intent.putExtra("imc",texttotal.text.toString())
            intent.putExtra("desc",descripcion.text.toString())

            startActivity(intent)

        }

    }



    fun funcioncalcularIMC(){
        var vtotal:Float
        var valtura:Float

        valtura=altura.toFloat()/100
        valtura=valtura.toFloat().pow(2)
        vtotal=peso/valtura.toFloat()

        val numeroFormateado = DecimalFormat("#.##").format(vtotal)

      //  texttotal.text=vtotal.toString()
        texttotal.text=numeroFormateado + " IMC"

        descripcion.setTextColor(getColor(R.color.white))

        when(vtotal){

                in 0.0 ..18.5->{
                    descripcion.text="Resultado: Bajo peso"
                    descripcion.setTextColor(getColor(R.color.gris))
                    sonido()
                }

                     in 18.5 ..24.9->{
                         descripcion.text="Resultado: peso Saludable"
                         descripcion.setTextColor(getColor(R.color.verde))
                     }
                     in 24.9..29.9->{
                         descripcion.text="Resultado:Sobrepeso"
                         descripcion.setTextColor(getColor(R.color.amarillo))
                         sonido()
                     }

                else->{
                    descripcion.text="Resultado: Obesidad"
                    descripcion.setTextColor(getColor(R.color.rojo))
                    sonido()

                }



        }



    }

    fun sonido(){
        var toneGenerator =
            ToneGenerator(AudioManager.STREAM_ALARM, 100) // El segundo parámetro es el volumen

        // Lanzar un pitido
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 800)
    }


}
