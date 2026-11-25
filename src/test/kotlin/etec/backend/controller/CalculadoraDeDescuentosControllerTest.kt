
package etec.backend.controller

import com.ninjasquad.springmockk.MockkBean
import etec.backend.exception.MontoMenorACeroException
import etec.backend.service.CalculadoraDeDescuentosService
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(CalculadoraDeDescuentosController::class)
class CalculadoraDeDescuentosControllerTest(
    private val navegador: MockMvc,
    @MockkBean private val calculadora: CalculadoraDeDescuentosService
) : BehaviorSpec({

    Given("el endpoint /api/descuentos") {

        When("un usuario solicita un descuento con monto válido") {

            every {
                calculadora.aplicarDescuentosSegunUsuario("123", 100.0)
            } returns 75.0

            val respuesta = navegador.perform(
                get("/api/descuentos")
                    .param("userID", "123")
                    .param("monto", "100.0")
            )

            Then("debe responder HTTP 200") {
                respuesta.andExpect(status().isOk)
            }

            And("debe devolver el monto final de 75.0") {
                respuesta.andExpect(
                    jsonPath("$.montoFinal").value(75.0)
                )
            }
        }

        When("un usuario envía un monto negativo") {

            every {
                calculadora.aplicarDescuentosSegunUsuario("123", -10.0)
            } throws MontoMenorACeroException(
                "El monto debe ser mayor a cero"
            )

            val respuesta = navegador.perform(
                get("/api/descuentos")
                    .param("userID", "123")
                    .param("monto", "-10.0")
            )

            Then("debe responder HTTP 400") {
                respuesta.andExpect(status().isBadRequest)
            }

            And("debe devolver el mensaje de error") {
                respuesta.andExpect(
                    jsonPath("$.error")
                        .value("El monto debe ser mayor a cero")
                )
            }
        }
    }
})
