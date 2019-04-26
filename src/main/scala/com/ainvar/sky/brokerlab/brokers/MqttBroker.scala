package com.ainvar.sky.brokerlab.brokers

import java.net.URI
import java.util.Properties

import akka.Done
import org.apache.activemq.broker.{BrokerService, TransportConnector}
import org.eclipse.paho.client.mqttv3.{MqttClient, MqttConnectOptions, MqttException}

import scala.concurrent.{ExecutionContext, Future}

//TODO: in draft
class MqttBroker {

  var brokerService: Option[BrokerService] = startBroker()

  def startBroker() = {
    val broker = new BrokerService

//    val kahaDB = new KahaDBStore
//    kahaDB.setDirectory(FileSystems.getDefault.getPath("./target4", "activemq", "data").toFile)
//    kahaDB.setJournalMaxFileLength(10240 * 100)
//
//    kahaDB.setIndexWriteBatchSize(100) // small batch -> more frequent small writes
//    kahaDB.setEnableIndexWriteAsync(true) // index write in separate thread
//
//    broker.setPersistenceAdapter(kahaDB)

//    broker.addConnector("tcp://127.0.0.1:1883")


    broker.setPersistent(false)
    broker.setUseJmx(false)

    broker.addConnector(getEmbeddedMqttBroker)

    broker.start()
    Some(broker)
  }

  def getEmbeddedMqttBroker = {
    val connector = new TransportConnector()
    connector.setUri(new URI("mqtt://127.0.0.1:1883"))
    connector.setName("mqtt")
    connector
  }

  def checkConnection(client: MqttClient, connOpts: MqttConnectOptions): Unit = {
    var counter = 0
    while ( {
      !client.isConnected
    }) {
      if (counter > 0) System.out.println("try no: " + counter)
      try
        client.connect(connOpts)
      catch {
        case e: MqttException =>
          println(client.getClientId + " MQTTException : " + e.getMessage + " - " + "retry no: " + {
            counter += 1; counter
          })
      }
    }
    if (counter > 0) println(client.getClientId + " isConnected")
  }

  def stop()(implicit ec: ExecutionContext): Future[Done] = {
    brokerService.fold(Future.successful(Done)){ broker =>
      Future {
        broker.stop()
        scala.concurrent.blocking {
          broker.waitUntilStopped()
        }
        Done
      }
    }
  }
}
