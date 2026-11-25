
package etec.backend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController



 @RestController
 class StatusController{
     @GetMapping("/status")
     fun status(): Map<String, String > {
         return mapOf("message" to "ok")
     }
 }