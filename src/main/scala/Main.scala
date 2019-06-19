import com.github.glopartparser.persist.{ProductsRepository, WareCategories, WareCategoriesRepository}
import com.github.glopartparser.http.HttpClient
import monix.eval.Task
import monix.execution.Scheduler
import org.slf4j.LoggerFactory
import io.circe._, io.circe.parser._

import scala.util.{Failure, Success, Try}

object Main {

  def main(args: Array[String]): Unit = {

    val logger = LoggerFactory.getLogger("Main")

    val httpClient = new HttpClient("http://glopart.ru/api/")

    logger.debug(httpClient.get("v2/public-marketplace/leaders"))

    Task.fromTry(Try{ httpClient.get("warecategories") })
        .map(response => parse(response))
        .onErrorHandle(e => logger.error(e.getLocalizedMessage, e))
    // TODO: continue parsing WareCategories, map JSON into WareCategories models and persist them

    WareCategoriesRepository.createTable()
    ProductsRepository.createTable()

    Task.deferFuture(WareCategoriesRepository.insert(WareCategories("test", "test", 0, 0)))
      .runOnComplete{
        case Success(value) => logger.debug(value.toString)
        case Failure(exception) => logger.error(exception.getLocalizedMessage, exception)
      }(Scheduler.io())

    while(true) {}
  }

}
