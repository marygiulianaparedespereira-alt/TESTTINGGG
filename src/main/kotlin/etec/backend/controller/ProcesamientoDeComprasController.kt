
package etec.backend.controller

import etec.backend.service.ProcesamientoDeComprasService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/compras")
class ProcesamientoDeComprasController(
    private val servicio: ProcesamientoDeComprasService
) {

    @GetMapping("/calcular-descuento")
    fun procesar(
        @RequestParam subtotal: Double,
        @RequestParam cupon: String
    ): Map<String, Double> {

        val montoFinal = servicio.procesarCompra(
            subtotal,
            cupon
        )

        return mapOf("montoFinal" to montoFinal)
    }
}
