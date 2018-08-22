package org.amber.example

import java.io.{BufferedReader, InputStreamReader}
import java.util

import cn.hutool.crypto.{Mode, Padding}
import cn.hutool.crypto.symmetric.AES

import scala.collection.JavaConverters._

object ShellUtils {

  def execCmd(cmd: String): Unit = {
    val process = Runtime.getRuntime.exec(cmd)
    val i = process.waitFor()

    val stream = if (i == 0) {
      process.getInputStream
    } else {
      process.getErrorStream
    }
    val reader = new BufferedReader(new InputStreamReader(stream))
    val result = for (v <- reader.readLine()) yield v
    print(result)
  }

  def execCmd(cmds: Array[String]): Unit = {
    val process = Runtime.getRuntime.exec(cmds)
    val i = process.waitFor()

    val stream = if (i == 0) {
      process.getInputStream
    } else {
      process.getErrorStream
    }
    val reader = new BufferedReader(new InputStreamReader(stream))
    val result = for (v <- reader.readLine()) yield v
    print(result)
  }

  def decodePhone(code: String): String = {
    val array = Array("sh", "-c", s"/bin/echo $code|/usr/bin/openssl enc -d -aes-128-ecb -a -K 09415d320f19e067725f3a199e2956de")
    val list = util.Arrays.asList[String](array: _*)
    val processBuilder = new ProcessBuilder(list)
    val process = processBuilder.start()
    val i = process.waitFor()

    val stream = if (i == 0) {
      process.getInputStream
    } else {
      process.getErrorStream
    }
    val reader = new BufferedReader(new InputStreamReader(stream))
    val result = for (v <- reader.readLine()) yield v
    result
  }

  def main(args: Array[String]): Unit = {
    val key = "09415d320f19e067725f3a199e2956de"
    val value = "00001/cHhXOwwl2F1oQF+w=="
    val cmd = s"sh -c '/bin/echo 00001/cHhXOwwl2F1oQF+w==|/usr/bin/openssl enc -d -aes-128-ecb -a -K 09415d320f19e067725f3a199e2956de'"
    val array = Array("sh", "-c", "/bin/echo 00001/cHhXOwwl2F1oQF+w==|/usr/bin/openssl enc -d -aes-128-ecb -a -K 09415d320f19e067725f3a199e2956de")
    val list = util.Arrays.asList[String](array: _*)
    val processBuilder = new ProcessBuilder(list)
    val process = processBuilder.start()
    val i = process.waitFor()

    val stream = if (i == 0) {
      process.getInputStream
    } else {
      process.getErrorStream
    }
    val reader = new BufferedReader(new InputStreamReader(stream))
    val result = for (v <- reader.readLine()) yield v
    println(result)
    println(decodePhone(value))

    val aes = new AES(Mode.ECB,Padding.NoPadding,key.getBytes("utf-8"))
    println(aes.decryptStrFromBase64(value))
  }

}
