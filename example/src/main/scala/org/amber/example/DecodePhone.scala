package org.amber.example

import cn.hutool.core.codec.Base64
import org.apache.spark.{SparkConf, SparkContext}

object DecodePhone {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("set").setMaster("local[" + args(0) + "]")
    conf.set("spark.executor.memory", "2g")
    conf.set("spark.driver.memory", "6g")
    val sparkContext = new SparkContext(conf)
    val rdd = sparkContext.textFile(args(1))
    rdd.map(v => {
      val strings = v.split(",")
      var result = ""
      try {
        result = ShellUtils.decodePhone(strings(0)) + "," + strings
      } catch {
        case _: Exception => result = "," + strings
      }

    }).saveAsTextFile(args(2))
  }
}
