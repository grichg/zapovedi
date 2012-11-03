package com.grisoft.bussobj.service
import com.grisoft.bussobj.BussObj
import com.grisoft.bussobj.BusObjMetadata
import com.google.appengine.api.datastore.Query

trait BusObjDB {
 
  def putNew(busObj: BussObj): Long
  def putNewChild(busObj: BussObj, idLongParent: Long, objMetaParent: BusObjMetadata): Long
  def getObjById(idLong: Long, objMeta: BusObjMetadata): BussObj
  def getAll(objMeta: BusObjMetadata, skip: Int, limit: Int): List[BussObj]
  def getAllByQuery(objMeta: BusObjMetadata, skip: Int, limit: Int, q: Query): List[BussObj]

  // more features..
}