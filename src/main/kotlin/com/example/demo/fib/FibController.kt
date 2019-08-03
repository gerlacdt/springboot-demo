package com.example.demo.fib

import com.example.demo.user.ErrorResponse
import io.swagger.annotations.*
import net.logstash.logback.marker.Markers.append
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fib")
@Api(value = "fibonacci calculator", description = "calculates the nth fibonacci number")
class FibController(val fibService: FibService) {

    val logger: Logger = LoggerFactory.getLogger(FibController::class.java)

    @ApiOperation(value = "Calculates the nth fibonacci number", response = FibResult::class)
    @ApiResponses(
        ApiResponse(code = 200, message = "Successful operation", response = FibResult::class),
        ApiResponse(code = 500, message = "Unknown server error", response = ErrorResponse::class)
    )
    @GetMapping
    fun fib(@ApiParam(value = "nth fibonacci", defaultValue = "10")
            @RequestParam(value="n", defaultValue = "10") n: Int): FibResult {
        val result = FibResult(n = n, result = fibService.fib(n))
        logger.info(append("result", result), "http request: fib($n) ")
        return result
    }
}


// swagger urls
// http://localhost:8080/v2/api-docs
// http://localhost:8080/swagger-ui.html