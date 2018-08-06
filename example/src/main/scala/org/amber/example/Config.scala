package org.amber.example

import java.io.{File, FileInputStream}
import java.util.Properties

import org.apache.logging.log4j.scala.Logging


object Config extends Logging {
  private val properties: Properties = new Properties()
  private var file = new File("config.properties")
  if (!file.exists()) {
    val path = Thread.currentThread().getContextClassLoader
      .getResource("config.properties").getPath
    file = new File(path)
  }

  properties.load(new FileInputStream(file))

  import scala.collection.JavaConversions._

  properties.foreach(e => {
    logger.info(e.toString())
  })

  val age = properties.getProperty("age")
  val name = properties.getProperty("name")
}
