package com.grisoft.bussobj

import scala.collection.immutable.Map

abstract class BussObj {
 val tblName: String
 val idName: String
 var idObj:Long
 val map: Map[String, Any]
 
def createFromMap(m: Map[String, Any]): Option[BussObj] 
}

