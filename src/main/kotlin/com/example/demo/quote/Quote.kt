package com.example.demo.quote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quote(var type: String, var value: Value)


@JsonIgnoreProperties(ignoreUnknown = true)
data class Value(var id: Int, var quote: String)