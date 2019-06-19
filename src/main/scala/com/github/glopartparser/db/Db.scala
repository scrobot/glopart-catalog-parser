package com.github.glopartparser.db

import slick.jdbc.{JdbcBackend, JdbcProfile}

object Db {
  val db = JdbcBackend.backend.Database.forConfig("mydb")

  def close() = db.close()
}
