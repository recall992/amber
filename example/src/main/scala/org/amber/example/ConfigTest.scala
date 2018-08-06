package org.amber.example

import org.apache.logging.log4j.scala.Logging
import org.apache.spark.sql.SparkSession

object ConfigTest extends Logging {

  def main(args: Array[String]): Unit = {
    val sparkContext = SparkSession.builder().appName("test-config").master("local[3]").getOrCreate().sparkContext
    val rdd = sparkContext.makeRDD(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    rdd.foreach(v => {
      logger.info(v + "")
    })
  }
}
