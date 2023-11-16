package net.d3sarrollo.cuadratica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val value: TextView = findViewById(R.id.texto1)

        val cadenaFuncion = "g(x) = 2x²+2x+3"
        val x = 2.2

        value.setText(calcularEcuacionCuadratica(cadenaFuncion, x))

    }

    fun calcularEcuacionCuadratica(cadenaFuncion: String, x: Double): String {
        val ecuacion = cadenaFuncion.substringAfter("=").trim() // Obtener la parte de la ecuación después del "="
        val terminos = ecuacion.split(Regex("(?=[+-])"))
        val coeficientes = mutableListOf<Int>()
        val exponentes = mutableListOf<Int>()

        // Obtener los coeficientes y exponentes de los términos
        for (termino in terminos) {
            val limpio = termino.trim()
            if (limpio.isNotEmpty()) {
                val coeficienteString = limpio.substringBefore("x").trim()
                val coeficienteStringSinEspacios = coeficienteString.replace(" ", "")
                val coeficiente = if (coeficienteStringSinEspacios == "-") -1 else if (coeficienteStringSinEspacios == "") 1 else coeficienteStringSinEspacios.toInt()
                coeficientes.add(coeficiente)
                val potenciaString = limpio.substringAfter(coeficiente.toString()).trim()
                if (potenciaString == "x²"){
                    exponentes.add(2)
                } else if (potenciaString == "x"){
                    exponentes.add(1)
                } else {
                    exponentes.add(0)
                }
            }
        }

    // Calcular el resultado de la ecuación
        var resultado = ""
        for (numero in -10..10){
            var r = 0.0
            for (i in coeficientes.indices) {
                val coeficiente = coeficientes[i]
                val exponente = exponentes[i]
                val resultado1: Double = (coeficiente * Math.pow(numero.toDouble(), exponente.toDouble()))
                r += resultado1
            }
            resultado += numero.toString() + "  **  " + r.toString() + "\n"
        }

        return resultado

    }
}
