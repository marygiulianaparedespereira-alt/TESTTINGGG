
package etec.backend.service

import etec.backend.repository.CuponRepository
import etec.backend.exception.PrecondicionInvalidaException
import etec.backend.exception.CuponInvalidoException
import org.springframework.stereotype.Service

@Service
class ProcesamientoDeComprasService(
    private val repositorio: CuponRepository
) {

    fun procesarCompra(
        subtotal: Double,
        cupon: String
    ): Double {

        if (subtotal <= 0.0 || !subtotal.isFinite()) {
            throw PrecondicionInvalidaException(
                "El monto total de la compra debe ser mayor a cero"
            )
        }

        if (cupon.isBlank()) {
            return subtotal
        }

        val descuento = repositorio.obtenerDescuento(cupon)
            ?: throw CuponInvalidoException(
                "El cupón proporcionado no existe"
            )

        if (cupon == "BLACK_FRIDAY" && subtotal <= 500.0) {
            throw CuponInvalidoException(
                "BLACK_FRIDAY requiere un subtotal mayor a 500"
            )
        }

        return subtotal * (1 - descuento)
    }
}
