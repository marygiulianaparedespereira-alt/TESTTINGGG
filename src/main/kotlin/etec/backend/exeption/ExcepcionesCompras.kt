
package etec.backend.exception

class PrecondicionInvalidaException(
    mensaje: String
) : RuntimeException(mensaje)

class CuponInvalidoException(
    mensaje: String
) : RuntimeException(mensaje)
