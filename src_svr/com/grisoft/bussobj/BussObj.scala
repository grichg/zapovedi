package com.grisoft.gaeds.bussobj


import scala.collection.immutable.Map

abstract class BussObj {
 val tblName: String
 val map: Map[String, Any]
 
def createFromMap(m: Map[String, Any]): Option[BussObj] 
}
