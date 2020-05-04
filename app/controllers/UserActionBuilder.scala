package controllers

import javax.inject.Inject
import play.api.http.HttpVerbs
import play.api.i18n.MessagesApi
import play.api.mvc._
import play.api.{Logger, MarkerContext}

import scala.concurrent.{ExecutionContext, Future}

trait UserRequestHeader extends MessagesRequestHeader with PreferredMessagesProvider
class UserRequest[A](request: Request[A], val messagesApi: MessagesApi)
    extends WrappedRequest(request)
    with UserRequestHeader

class UserActionBuilder @Inject()(messagesApi: MessagesApi, playBodyParsers: PlayBodyParsers)(
  implicit val executionContext: ExecutionContext
) extends ActionBuilder[UserRequest, AnyContent]
    with RequestMarkerContext
    with HttpVerbs {

  override val parser: BodyParser[AnyContent] = playBodyParsers.anyContent

  private val logger = Logger(getClass)

  override def invokeBlock[A](request: Request[A], block: UserRequest[A] => Future[Result]): Future[Result] = {
    // Convert to marker context and use request in block
    implicit val markerContext: MarkerContext = requestHeaderToMarkerContext(request)
    logger.trace(s"invokeBlock: ")
    block(new UserRequest(request, messagesApi))
  }
}
