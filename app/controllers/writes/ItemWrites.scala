package controllers.writes

import domains.item.Item
import play.api.libs.json.{Json, Writes}

private[controllers] trait ItemWrites extends BaseWrites {

  implicit val itemWrites: Writes[Item] = Json.writes[Item]

}
