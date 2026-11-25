
package etec.backend.repository

import org.springframework.stereotype.Repository

interface CuponRepository {
    fun obtenerDescuento(codigo: String): Double?
}

@Repository
class CuponRepositoryMemoria : CuponRepository {

    override fun obtenerDescuento(codigo: String): Double? {
        return when (codigo) {
            "PRIMERA_COMPRA" -> 0.15
            "BLACK_FRIDAY" -> 0.30
            else -> null
        }
    }
}
