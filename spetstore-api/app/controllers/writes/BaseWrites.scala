package controllers.writes

import base.Id
import play.api.libs.json.{JsString, Writes}

trait BaseWrites {
  implicit final def idWrites[A]: Writes[Id[A]] = Writes(id => JsString(id.value.toString))
}
