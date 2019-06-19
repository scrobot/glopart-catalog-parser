package com.github.glopartparser.http

import java.io.IOException
import java.net.{HttpURLConnection, URL}

import scala.io.Source

class HttpClient(val baseUrl: String) {

  @throws(classOf[IOException])
  @throws(classOf[java.net.SocketTimeoutException])
  def get(endpoint: String,
          connectTimeout: Int = 5000,
          readTimeout: Int = 5000,
          requestMethod: String = "GET") = {
    val connection = new URL(baseUrl + endpoint).openConnection.asInstanceOf[HttpURLConnection]
    connection.setConnectTimeout(connectTimeout)
    connection.setReadTimeout(readTimeout)
    connection.setRequestMethod(requestMethod)
    val inputStream = connection.getInputStream
    val content = Source.fromInputStream(inputStream).mkString
    if (inputStream != null) inputStream.close
    content
  }

}
