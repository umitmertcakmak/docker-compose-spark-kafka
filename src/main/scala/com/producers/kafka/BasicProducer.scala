package com.producers.kafka

/**
  * Created by umitcakmak on 30/05/17.
  */

import java.util.Properties

import org.apache.kafka.clients.producer._

object BasicProducer {
  def main(args: Array[String]): Unit = {

    if (args.length < 2) {
      System.err.println(s"""
                            |Usage: DirectKafkaWordCount <brokers> <topics>
                            |  <brokers> is a list of one or more Kafka brokers
                            |  <topics> is a list of one or more kafka topics to consume from
                            |
        """.stripMargin)
      System.exit(1)
    }

    val Array(brokers, topics) = args

    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)

    System.out.println("Starting here...")

    val  props = new Properties()
    props.put("bootstrap.servers", brokers)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val TOPIC="word-count"

    for(i<- 1 to 50){
      val record = new ProducerRecord(TOPIC, "key", s"hello count me $i")
      producer.send(record)
      Thread.sleep(10000)
    }

    val record = new ProducerRecord(TOPIC, "key", "the end "+new java.util.Date)
    producer.send(record)

    producer.close()

  }

}
