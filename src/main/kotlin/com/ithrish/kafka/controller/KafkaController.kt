package com.ithrish.kafka.controller

import com.ithrish.kafka.service.KafkaProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KafkaController(val kafkaProducer: KafkaProducer) {

    @RequestMapping("/send/{message}")
    fun sendMessage(@PathVariable message: String): String{
        kafkaProducer.send(message)
        return "Success"

    }


}