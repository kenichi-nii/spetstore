package controllers.writes

import base.Id
import play.api.libs.json.{JsString, Writes}

trait BaseWrites {
  final implicit def idWrites[A]: Writes[Id[A]] = Writes { id =>
    JsString(id.value.toString)
  }
}
