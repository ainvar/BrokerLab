
name := "BrokerLab" 
version := "0.1.0" 
scalaVersion := "2.12.8" 
resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/" 
scalacOptions ++= Seq( 
	"-deprecation", // Emit warning and location for usages of deprecated APIs. 
	"-feature", // Emit warning and location for usages of features that should be imported explicitly. 
	"-unchecked", // Enable additional warnings where generated code depends on assumptions. 
//	"-Xfatal-warnings", // Fail the compilation if there are any warnings. 
	"-Xlint", // Enable recommended additional warnings. 
	"-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver. 
	"-Ywarn-dead-code", // Warn when dead code is identified. 
	"-Ywarn-inaccessible", // Warn about inaccessible types in method signatures. 
	"-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo. 
	"-Ywarn-numeric-widen", // Warn when numerics are widened. 
	"-language:higherKinds" // Enable the use of higher kinds by default 
)
libraryDependencies ++= Seq( // Testing 
	"org.scalatest" %% "scalatest" % "3.0.1" % "test" withSources() withJavadoc()
	,"org.scalacheck" %% "scalacheck" % "1.13.4" % "test" withSources() withJavadoc()
	,"org.mockito" % "mockito-all" % "1.10.19" withSources() // Logging 
	,"com.typesafe.scala-logging" %% "scala-logging" % "3.5.0" withSources()
	,"ch.qos.logback" % "logback-classic" % "1.1.7" withSources()
	, "org.apache.activemq" % "activemq-core" % "5.7.0" withSources()
	, "com.jsuereth" %% "scala-arm" % "2.0" withSources()
	, "com.typesafe.akka" %% "akka-actor" % "2.5.21" withSources()
	, "com.typesafe.akka" %% "akka-testkit" % "2.5.21" % Test withSources()
	, "com.typesafe.akka" %% "akka-stream" % "2.5.21" withSources()
	, "org.eclipse.paho" % "org.eclipse.paho.client.mqttv3" % "1.2.1" withSources())
// , "org.apache.activemq" % "activemq-broker" % "5.15.8" withSources()
// https://mvnrepository.com/artifact/org.apache.activemq/activemq-core
mainClass in assembly := Some("Main")
