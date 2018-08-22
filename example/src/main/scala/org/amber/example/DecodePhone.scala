package org.amber.example

import cn.hutool.core.codec.Base64
import org.apache.spark.{SparkConf, SparkContext}

object DecodePhone {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("set").setMaster("local[68]")
    val sparkContext = new SparkContext(conf)
    val rdd = sparkContext.textFile("/home/sundays/result.csv")
    rdd.map(v => {
      val strings = v.split(",")
      ShellUtils.decodePhone(strings(0)) + "," + strings
    }).saveAsTextFile("/tmp/result-phone")
  }
}
