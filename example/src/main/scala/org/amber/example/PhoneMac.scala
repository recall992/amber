package org.amber.example


import cn.hutool.core.util.CharsetUtil
import cn.hutool.crypto.{Mode, Padding, SecureUtil}
import cn.hutool.crypto.symmetric.AES
import org.apache.commons.codec.binary.Base64


object PhoneMac {

  def main(args: Array[String]): Unit = {
    val key = "09415D320F19E067725F3A199E2956DE".toLowerCase()
    val value = "0000CeGtVraR5Xj1rPoGtw=="
    val aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes("ASCII"))
    val bytes = new Base64().decode("0000CeGtVraR5Xj1rPoGtw==")
    // val str = aes.decryptStrFromBase64(value)
    // println(str)
    println(AesUtils.decrypt(value, key))
    println(AesUtils.decryptJava(value, key))
  }

}

/**
  * *
  * 15228378132
  * 00001/cHhXOwwl2F1oQF+w==,8C-BE-BE-B0-13-70,863077029122839,2016-07-25 16:22:44
  * 13935362778
  * 0000CeGtVraR5Xj1rPoGtw==,F4-8B-32-8D-9B-7E,867822029363248,2016-07-09 11:55:36
  * 13509630571
  * 0002BcuRIdlgEF8EQ6NsEA==,38-A4-ED-18-65-22,,2016-11-06 18:50:32
  * 13265171090
  * 0003LTQS9JgTJ5hzPEbZ+w==,74-23-44-99-03-1F,,2016-11-01 09:21:19
  * 18629501182
  * 0004omZ+hWMAYOjcOwTuEQ==,74-23-44-E9-EB-3D,,2016-12-11 17:16:53
  * 18612504987
  * 0005CIReRhTpb/1Mg6Y7yQ==,B0-E2-35-C1-1F-DB,,2016-08-06 07:15:16
  * 13161815155
  * 0005zqbKWdg1LtJ+S4kJdQ==,10-2A-B3-7A-4E-F8,99000709050626,2016-10-11 18:06:22
  * 15032361289
  * 00066ExmZDwBHl9hZ0sxmw==,A0-86-C6-94-69-BF,,2016-08-06 18:37:59
  * 15135878130
  * 0006+AvwofECKMDpp4lvfg==,74-23-44-D8-57-E6,,2016-10-01 18:43:40
  * 18601381560
  * 0008+tAJciCqY3XPIe3rmQ==,B0-E2-35-7D-1A-CB,861735038624762,2016-11-26 11:53:17
  *
  */