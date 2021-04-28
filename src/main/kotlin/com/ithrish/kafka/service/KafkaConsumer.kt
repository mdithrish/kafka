package com.ithrish.kafka.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaConsumer {

    private val logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = ["\${kafka.topic}"], containerFactory = "kafkaListenerContainerFactory")
    fun processMessage(@Payload message: String,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) partition: String,
                       @Header(KafkaHeaders.OFFSET) offset: String){
        logger.info("Recieved message : {} in partition {} and Offset {}", message, partition , offset)
    }
}