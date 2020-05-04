package controllers

import javax.inject.{Inject, Provider, Singleton}
import play.api.http.DefaultHttpErrorHandler
import play.api.mvc.Results._
import play.api.mvc.{RequestHeader, Result}
import play.api.routing.Router
import play.api.{Configuration, Environment, OptionalSourceMapper, UsefulException}

import scala.concurrent.Future

@Singleton
class ErrorHandler @Inject() (
  env: Environment,
  config: Configuration,
  sourceMapper: OptionalSourceMapper,
  router: Provider[Router]
) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) {
  override def onProdServerError(request: RequestHeader, exception: UsefulException): Future[Result] =
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )

  override def onForbidden(request: RequestHeader, message: String): Future[Result] =
    Future.successful(
      Forbidden("You're not allowed to access this resource.")
    )
}
