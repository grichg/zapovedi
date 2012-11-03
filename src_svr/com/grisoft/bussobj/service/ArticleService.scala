package com.grisoft.bussobj.service
import com.grisoft.bussobj.model.Article
 

trait ArticleDB {
  // saves an entity, returns an ID
  def updateBlob(idlong: Long, blob: String)
  // more features..
}

trait ArticleService {
  this: ArticleDB with BusObjDB =>

  def createNew(artcl: Article): Long = {
    putNew(artcl)
  }

  def setBlob(idlong: Long, blob: String) = {
    updateBlob(idlong, blob)
  }

  //  def createNew(artcl: Article): Long = {
  //    putNew(artcl)
  //  }

}