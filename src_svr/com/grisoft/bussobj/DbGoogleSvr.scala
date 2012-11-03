package com.grisoft.bussobj
import com.google.appengine.api.datastore.DatastoreServiceFactory
import com.google.appengine.api.datastore.Entity
import scala.collection.JavaConversions._
import scala.collection.breakOut
import scala.collection.immutable.Map
import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.FetchOptions
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Key
import com.google.appengine.api.datastore.Blob

object DbGoogleSvr {
  val sBlob: String = "blob"

  def putNewChild(busObj: BussObj, idLongParent: Long, objMetaParent: BusObjMetadata): Long = {
    val ds = DatastoreServiceFactory.getDatastoreService()
    val txn = ds.beginTransaction();
     val keyParent: Key = KeyFactory.createKey(objMetaParent.tblName, idLongParent)
    var objEntity = new Entity(busObj.tblName, keyParent);

    busObj.map foreach ((field) => (objEntity.setProperty(field._1, field._2)))
    //    objEntity.setUnindexedProperty()
    val key = ds.put(objEntity)
    txn.commit()
    addKeyId(ds, key, busObj)
    key.getId()
  }

  def putNew(busObj: BussObj): Long = {
    val ds = DatastoreServiceFactory.getDatastoreService()
    val txn = ds.beginTransaction();
    var objEntity = new Entity(busObj.tblName);

    busObj.map foreach ((field) => (objEntity.setProperty(field._1, field._2)))
    //    objEntity.setUnindexedProperty()
    val key = ds.put(objEntity)
    txn.commit()
    addKeyId(ds, key, busObj)
    key.getId()
  }

  def putBlob(idLong: Long, objMeta: BusObjMetadata, data: Blob) = {
    val ds = DatastoreServiceFactory.getDatastoreService()
    val ent = getEntityById(idLong, objMeta)
    ent.setUnindexedProperty(sBlob, data)
    ds.put(ent)
  }

  def getBlob(idLong: Long, objMeta: BusObjMetadata): Option[Blob] = {
    val ds = DatastoreServiceFactory.getDatastoreService()
    val ent = getEntityById(idLong, objMeta)
    ent.getProperty(sBlob) match {
      case Some(a: Blob) => Some(a)
      case _ => None
    }
  }

  def getObjById(idLong: Long, objMeta: BusObjMetadata): BussObj = {
    getFromEntity(getEntityById(idLong, objMeta), objMeta).get
  }

  private def getEntityById(idLong: Long, objMeta: BusObjMetadata): Entity = {
    val ds = DatastoreServiceFactory.getDatastoreService()
    val key: Key = KeyFactory.createKey(objMeta.tblName, idLong)
    ds.get(key)
  }

  def getAll(objMeta: BusObjMetadata, skip: Int, limit: Int): List[BussObj] = {
    val q = new Query(objMeta.tblName)
    getAllByQuery(objMeta, skip, limit, q)
  }

  def getAllByQuery(objMeta: BusObjMetadata, skip: Int, limit: Int, q: Query): List[BussObj] = {
    val ds = DatastoreServiceFactory.getDatastoreService()
    val pq = ds.prepare(q);
    val list = pq.asList(FetchOptions.Builder.withLimit(limit).offset(skip))
    list.map { e => getFromEntity(e, objMeta).get }(breakOut): List[BussObj]
  }

  def getAll(objMeta: BusObjMetadata): List[BussObj] = {
    val q = new Query(objMeta.tblName)
    getAllByQuery(objMeta, q)
  }

  def getAllByQuery(objMeta: BusObjMetadata, q: Query): List[BussObj] = {
    val ds = DatastoreServiceFactory.getDatastoreService()
    val pq = ds.prepare(q);
    val list = pq.asList(FetchOptions.Builder.withDefaults())
    list.map { e => DbGoogleSvr.getFromEntity(e, objMeta).get }(breakOut): List[BussObj]
  }

  def getFromEntity(ent: Entity, busObj: BusObjMetadata): Option[BussObj] = {
    val propMap: java.util.Map[String, Object] = ent.getProperties()
    val imm: Map[String, Any] = propMap.toMap
    val objRet = busObj.createFromMap(imm)
    objRet.get.idObj = ent.getKey().getId()
    objRet
  }

  //  def getObjList(len: Int, metaData: BusObjMetadata): List[BussObj] = {
  //    val ds = DatastoreServiceFactory.getDatastoreService()
  //    val q = new Query(metaData.tblName)
  //    val pq = ds.prepare(q)
  //    val it = pq.asIterator()
  //    var listRet: List[BussObj] = List.empty
  //    while (it.hasNext()) {
  //      val item = getFromEntity(it.next(), metaData)
  //      item match {
  //        case Some(x) => listRet = x :: listRet
  //        case None =>
  //      }
  //    }
  //    listRet
  //  }

  private def addKeyId(ds: com.google.appengine.api.datastore.DatastoreService,
    key: com.google.appengine.api.datastore.Key, busObj: BussObj): Unit = {
    val txn1 = ds.beginTransaction();
    val newEnt = ds.get(key)
    newEnt.setProperty(busObj.idName, key.getId())
    ds.put(newEnt)
    txn1.commit()
  }

}
