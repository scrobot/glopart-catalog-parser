package com.github.glopartparser.persist

import com.github.glopartparser.db.Db
import slick.lifted.TableQuery
import slick.driver.PostgresDriver.api._

case class WareCategories(slug: String, title: String, orderNum: Int, apiId: Int)

final case class WareCategoriesTable(tag: Tag) extends Table[WareCategories](tag, Some("public"), "ware_categories") {
  def slug = column[String]("slug", O.PrimaryKey)

  def title = column[String]("title")

  def orderNum = column[Int]("order_num")

  def apiId = column[Int]("api_id")

  def * = (slug, title, orderNum, apiId) <> (WareCategories.tupled, WareCategories.unapply)

  def applyCategories: (String, String, Int, Int) ⇒ WareCategories = {
    case (slug, title, orderNum, apiId) ⇒
      WareCategories(
        slug, title, orderNum, apiId
      )
  }

  def unapplyCategories: WareCategories ⇒ Option[(String, String, Int, Int)] = { wc ⇒
    WareCategories.unapply(wc).map { case (slug, title, orderNum, apiId) ⇒ (slug, title, orderNum, apiId) }
  }
}

object WareCategoriesRepository {

  private val categories = TableQuery[WareCategoriesTable]

  private val db = Db.db

  def createTable() = db.run(categories.schema.create)

  def insert(category: WareCategories) = db.run(categories.insertOrUpdate(category))
}
