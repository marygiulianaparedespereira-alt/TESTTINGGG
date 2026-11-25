
package etec.backend.controller

import etec.backend.service.CalculadoraDeDescuentosService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/descuentos")
class CalculadoraDeDescuentosController(
    private val calculadora: CalculadoraDeDescuentosService
) {

    @GetMapping
    fun calcularDescuento(
        @RequestParam userID: String,
        @RequestParam monto: Double
    ): Map<String, Double> {

        val resultado = calculadora.aplicarDescuentosSegunUsuario(
            userID,
            monto
        )

        return mapOf("montoFinal" to resultado)
    }
}
