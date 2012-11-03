package com.grisoft.bussobj
import com.google.appengine.api.datastore.Entity

trait BusObjMetadata {
  def tblName: String
  def idName: String
  def createFromMap(m: Map[String, Any]): Option[BussObj]
}