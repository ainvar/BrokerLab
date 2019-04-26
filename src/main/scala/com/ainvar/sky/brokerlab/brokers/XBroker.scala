package com.ainvar.sky.brokerlab.brokers

import javax.jms.ConnectionFactory
import akka.Done
import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.broker.BrokerService

import scala.concurrent.{ExecutionContext, Future}

class XBroker {

  var brokerService: Option[BrokerService] = None

  def start(): BrokerService = {
    val broker = new BrokerService()
    broker.setBrokerName("xbroker")
    broker.setUseJmx(false)
    broker.addConnector("tcp://127.0.0.1:1883")
    broker.start()
    brokerService = Some(broker)

    broker
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
