package com.github.glopartparser.persist

import com.github.glopartparser.db.Db
import slick.driver.PostgresDriver.api._

case class Product(id: Int,
                   price: Double,
                   currency: String,
                   ownerName: String,
                   ownerGravatar: String,
                   wareUrl: String,
                   image: String,
                   title: String,
                   createdAt: String,
                   apiId: Int)

final case class ProductsTable(tag: Tag) extends Table[Product](tag, Some("public"), "products") {

  def id() = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def price() = column[Double]("price")

  def currency() = column[String]("currency")

  def ownerName() = column[String]("ownerName")

  def ownerGravatar() = column[String]("ownerGravatar")

  def wareUrl() = column[String]("wareUrl")

  def image() = column[String]("image")

  def title() = column[String]("title")

  def createdAt() = column[String]("createdAt")

  def apiId() = column[Int]("apiId")

  def *() = (id, price, currency, ownerName, ownerGravatar, wareUrl, image, title, createdAt, apiId) <>
    (applyProducts.tupled, unapplyProducts)

  def applyProducts: (Int, Double, String, String, String, String, String, String, String, Int) ⇒ Product = {
    case (id, price, currency, ownerName, ownerGravatar, wareUrl, image, title, createdAt, apiId) ⇒
      Product(
        id, price, currency, ownerName, ownerGravatar, wareUrl, image, title, createdAt, apiId
      )
  }

  def unapplyProducts: Product ⇒ Option[(Int, Double, String, String, String, String, String, String, String, Int)] = { p ⇒
    Product.unapply(p).map { case (id, price, currency, ownerName, ownerGravatar, wareUrl, image, title, createdAt, apiId) ⇒
      (id, price, currency, ownerName, ownerGravatar, wareUrl, image, title, createdAt, apiId) }
  }
}

object ProductsRepository {

    private val products = TableQuery[ProductsTable]

    private val db = Db.db

    def createTable() = db.run(products.schema.create)

    def insert(product: Product) = db.run(products.insertOrUpdate(product))

}
