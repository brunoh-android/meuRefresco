package br.learning.meurefresco

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.learning.meurefresco.databinding.ActivityMainBinding
import br.learning.meurefresco.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /*private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var bt_calcular: Button
    private lateinit var txt_resultado_ml: TextView
    private lateinit var ic_redefinir_dados: ImageView
    private lateinit var bt_lembrete: Button
    private lateinit var bt_alarme: Button
    private lateinit var txt_hora: TextView
    private lateinit var txt_minutos: TextView*/

    private lateinit var calcularIngestaoDiaria: CalcularIngestaoDiaria
    private var resultadoMl = 0.0

    lateinit var timePickerDialog: TimePickerDialog
    lateinit var calendar: Calendar
    var horaAtual = 0
    var minutosAtual = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar!!.hide()
        //IniciarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()


        binding.btCalcular.setOnClickListener {

            if (binding.editPeso.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_peso, Toast.LENGTH_LONG).show()

            } else if (binding.editIdade.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_idade, Toast.LENGTH_LONG).show()
            } else {

                /*val peso = edit_peso.text.toString().toDouble()
                val idade = edit_idade.text.toString().toInt()*/

                val peso = binding.editPeso.text.toString().toDouble()
                val idade = binding.editIdade.text.toString().toInt()

                calcularIngestaoDiaria.CalcularTotalMl(peso, idade)
                resultadoMl = calcularIngestaoDiaria.ResultadoMl()
                val formatar = NumberFormat.getNumberInstance(Locale("pt", "br"))
                formatar.isGroupingUsed = false
                binding.txtResultado.text = formatar.format(resultadoMl) + " " + "ml"
            }
        }

        binding.icRedefinir.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.text_dialog)
                .setMessage(R.string.text_dialog)
                .setPositiveButton("OK", { dialogInterface, i ->
                    binding.editPeso.setText("")
                    binding.editIdade.setText("")
                    binding.txtResultado.text = ""
                })
            alertDialog.setNegativeButton("Cancelar", { dialogInterface, i -> })
            val dialog = alertDialog.create()
            dialog.show()
        }

        binding.btDefinirLembrete.setOnClickListener {
            calendar = Calendar.getInstance()
            horaAtual = calendar.get(Calendar.HOUR_OF_DAY)
            minutosAtual = calendar.get(Calendar.MINUTE)
            timePickerDialog =
                TimePickerDialog(this, { timePicker: TimePicker, hourOfDay: Int, minutes: Int ->
                    binding.txtHora.text = String.format("%02d", hourOfDay)
                    binding.txtMinutos.text = String.format("%02d", minutes)
                }, horaAtual, minutosAtual, true)
            timePickerDialog.show()
        }

        binding.btDefinirAlarme.setOnClickListener {

            val txt_hora = binding.txtHora
            val txt_minutos = binding.txtMinutos

            if (!txt_hora.toString().isEmpty() && !txt_minutos.toString().isEmpty()) {
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, txt_hora.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES, txt_minutos.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.alarme_menssagem))
                startActivity(intent)

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)

                }
            }
        }
    }

    /*private fun IniciarComponentes() {
        edit_peso = findViewById(R.id.edit_peso)
        edit_idade = findViewById(R.id.edit_idade)
        bt_calcular = findViewById(R.id.bt_calcular)
        txt_resultado_ml = findViewById(R.id.txt_resultado)
        ic_redefinir_dados = findViewById(R.id.ic_redefinir)
        bt_lembrete = findViewById(R.id.bt_definir_lembrete)
        bt_alarme = findViewById(R.id.bt_definir_alarme)
        txt_hora = findViewById(R.id.txt_hora)
        txt_minutos = findViewById(R.id.txt_minutos)


    }*/

}