package org.amber.example

import org.amber.common.LogSupport
import org.apache.logging.log4j.scala.Logging

object ConfigTest extends Logging {

  def main(args: Array[String]): Unit = {
    logger.info("1231313")
    val test = ConfigTest
    val claszz = test.getClass
    logger.error(ConfigTest.getClass.toString)
    /*    val sparkContext = SparkSession.builder().appName("test-config").master("local[3]").getOrCreate().sparkContext
        val rdd = sparkContext.makeRDD(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        rdd.foreach(v => {
          logger.info(v + "")
        })*/
  }
}
