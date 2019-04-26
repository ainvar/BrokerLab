package com.ainvar.sky.brokerlab

import java.util.Properties

import com.ainvar.sky.brokerlab.brokers.MqttBroker
import org.eclipse.paho.client.mqttv3.{MqttClient, MqttConnectOptions}

import scala.concurrent.{ExecutionContext, Future}

object Launcher  {
  def main(args: Array[String]): Unit = {
    implicit val ec = ExecutionContext.global
    println("Started!!")
    val mqttBroker = new MqttBroker
    mqttBroker.brokerService.get.waitUntilStarted()
    Thread.sleep(4000)
    // ############ test with paho #############
    val client: MqttClient = new MqttClient("tcp://127.0.0.1:1883", "tcp-1", null)

    val connOpts = new MqttConnectOptions
    val props = new Properties
    connOpts.setSSLProperties(props)
    connOpts.setCleanSession(false)
    connOpts.setKeepAliveInterval(1500000)
    connOpts.setConnectionTimeout(1500000)
    client.connect(connOpts)
    mqttBroker checkConnection(client, connOpts)

    Thread.sleep(20000)
    // ############ test con paho fine #############
    mqttBroker stop()

  }
}
