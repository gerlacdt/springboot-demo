package com.example.demo.quote

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder


@Component
class QuoteGateway(val restTemplateBuilder: RestTemplateBuilder) {

    var restTemplate: RestTemplate = restTemplateBuilder.

    @Value("\${quote.baseurl}")
    lateinit var baseUrl: String

    fun getQuote(): Quote? {
        // https://gturnquist-quoters.cfapps.io/api/random
        println(baseUrl)
        val quote = restTemplate.getForObject("${baseUrl}/api/random",
                Quote::class.java)
        return quote;
    }
}