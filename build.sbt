name := "big_data_pipelines"

scalaVersion := "2.11.7"

val sparkVersion = "2.1.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  ("org.apache.spark" %% "spark-streaming-kafka" % sparkVersion) exclude ("org.spark-project.spark", "unused"),
  "org.apache.kafka" % "kafka-clients" % "0.10.0.1"
)

assemblyJarName in assembly := name.value + "allinone.jar"
