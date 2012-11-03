package com.grisoft.bussobj.service
import com.grisoft.bussobj.BussObj
import com.grisoft.bussobj.DbGoogleSvr
import com.google.appengine.api.datastore.DatastoreServiceFactory
import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Key
import com.grisoft.bussobj.BusObjMetadata
import scala.collection.JavaConversions._
import com.google.appengine.api.datastore.Query
import com.google.appengine.api.datastore.FetchOptions
import scala.collection.breakOut

trait ArticleDBAeg extends ArticleDB {

   def updateBlob(idlong: Long, blob: String)={}
   
}