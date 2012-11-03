package com.grisoft.bussobj.model
import com.grisoft.bussobj.BussObj
import com.grisoft.bussobj.BusObjMetadata
 

class Article(val id: Long, val name: String) extends BussObj {
  val map = Map("id" -> id, "name" -> name)
  var idObj = id
  val tblName = Article.tblName
  val idName = Article.idName

  def createFromMap(m: Map[String, Any]): Option[BussObj] = Article.createFromMap(m)
}

object Article extends BusObjMetadata {
  val tblName = "article"
  val idName = "id"
  def createFromMap(m: Map[String, Any]): Option[BussObj] =
    for {
      id <- m.get("id").collect { case l: Long => l }
      name <- m.get("name").collect { case s: String => s }
    } yield new Article(id, name)

}