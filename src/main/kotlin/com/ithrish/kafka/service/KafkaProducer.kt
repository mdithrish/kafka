package com.ithrish.kafka.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(private val kafkaTemplate: KafkaTemplate<String,String>) {

    @Value("\${kafka.topic}")
    lateinit var topic: String

    fun send(message: String){
        kafkaTemplate.send(topic, message)
    }

}