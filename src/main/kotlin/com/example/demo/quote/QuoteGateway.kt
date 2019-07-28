package com.example.demo.quote

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
class QuoteGateway {
    var restTemplate: RestTemplate = RestTemplate()

    fun getQuote(): Quote? {
        val quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random",
                Quote::class.java)
        return quote;
    }
}