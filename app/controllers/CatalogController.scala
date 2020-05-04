package controllers

import base.MaybeId
import controllers.writes.ItemWrites
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent}
import services.CatalogAppService

@Singleton
class CatalogController @Inject()(ucc: UserControllerComponents, val catalogAppService: CatalogAppService)
    extends UserBaseController(ucc)
    with ItemWrites {

  def getItem(itemId: MaybeId): Action[AnyContent] = UserAction { implicit request: UserRequest[_] =>
    (for {
      item <- catalogAppService
        .findItem(itemId)
        .left
        .map(error => NotFound(Json.obj("err" -> error.toString, "req" -> request.toString)))
    } yield {
      Ok(Json.toJson(item))
    }).fold(err => err, ret => ret)
  }
}
