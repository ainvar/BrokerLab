package com.ainvar.sky.brokerlab.context

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

trait BrokerExecutionContext {
  implicit val ec: ExecutionContextExecutor = ExecutionContext.global
}
