package com.ithrish.kafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer

@Configuration
@ConfigurationProperties(prefix = "kafka")
class KafkaConfig {

    var bootStrapServer: String = ""
    var groupId: String = ""
    var autoCommit: Boolean = false

    @Bean
    fun producerFactory(): ProducerFactory<String, String>{
        var configMap = HashMap<String, Any>()
        configMap[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootStrapServer
        configMap[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configMap[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java

        return DefaultKafkaProducerFactory<String, String>(configMap)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String,String>{
        return  KafkaTemplate<String, String>(producerFactory())

    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String>{
        var configMap = HashMap<String, Any>()
        configMap[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootStrapServer
        configMap[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configMap[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configMap[ConsumerConfig.GROUP_ID_CONFIG] = groupId

        return DefaultKafkaConsumerFactory<String, String>(configMap)
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>>
    {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}