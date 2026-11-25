
package etec.backend.service

import etec.backend.exception.MontoMenorACeroException
import org.springframework.stereotype.Service

@Service
class CalculadoraDeDescuentosService {

    fun aplicarDescuentosSegunUsuario(
        userID: String,
        monto: Double
    ): Double {

        if (monto <= 0) {
            throw MontoMenorACeroException(
                "El monto debe ser mayor a cero"
            )
        }

        if (userID == "123") {
            return monto * 0.75
        }

        return monto
    }
}
