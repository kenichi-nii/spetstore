package controllers

import javax.inject.Inject
import play.api.mvc.AbstractController

class UserBaseController @Inject()(ucc: UserControllerComponents)
    extends AbstractController(ucc)
    with RequestMarkerContext {
  def UserAction: UserActionBuilder = ucc.userActionBuilder
}
