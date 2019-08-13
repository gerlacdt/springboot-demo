package com.example.demo.github

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubUser(var name: String, var blog: String)

@Component
class GithubGateway(val restTemplateBuilder: RestTemplateBuilder) {

    var restTemplate: RestTemplate = restTemplateBuilder.build()

    @Value("\${github.baseurl}")
    lateinit var baseUrl: String

    @Async("threadPoolTaskExecutor")
    fun getUserInfo(name: String): CompletableFuture<GithubUser?> {
//        val githubUser = restTemplate.getForObject("${baseUrl}/${name}",
//                GithubUser::class.java)
        Thread.sleep(300)
        val githubUser = GithubUser("danger", "danger.blog.io")
        return CompletableFuture.completedFuture(githubUser)
    }

}