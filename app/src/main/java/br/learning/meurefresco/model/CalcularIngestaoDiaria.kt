package br.learning.meurefresco.model

class CalcularIngestaoDiaria {

    private val ml_jovem = 40.0
    private val ml_adulto = 35.0
    private val ml_idoso = 30.0
    private val ml_anciao = 25.0

    private var resultadoMl = 0.0
    private var resultadoTotalMl = 0.0

    fun CalcularTotalMl(peso: Double, idade: Int){

        if (idade <= 17){
            resultadoMl = peso * ml_jovem
            resultadoTotalMl = resultadoMl
        }else if (idade <= 55){
            resultadoMl = peso * ml_adulto
            resultadoTotalMl = resultadoMl
        }else if (idade <= 65) {
            resultadoMl = peso * ml_idoso
            resultadoTotalMl = resultadoMl
        }else{
            resultadoMl = peso * ml_anciao
            resultadoTotalMl = resultadoMl
        }

    }

    fun ResultadoMl():Double{
        return resultadoMl
    }

}