package com.grisoft.zapovedi.bussobj

import com.grisoft.gaeds.bussobj.BussObj

class User(val id: Long, name: String) extends BussObj {
  val map = Map("id" -> id, "name" -> name)
  val tblName = "user"

  def createFromMap(m: Map[String, Any]): Option[BussObj] = {
    val ret: Option[BussObj] = None
    try {
      val idval: Long = map.get("id") match {
        case Some(a: Long) => a
        case _ => throw new ClassCastException
      }
      val nameval: String = map.get("name") match {
        case Some(a: String) => a
        case _ => throw new ClassCastException
      }
      return Some(new User(idval, nameval))
    } catch {
      case e: ClassCastException =>
    }
    ret
  }
}