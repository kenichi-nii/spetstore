package domains.item

import base.Identifier

sealed abstract class ItemError
object ItemError {
  final case class NotFound(id: Identifier) extends ItemError

  def notFound(id: Identifier): ItemError = NotFound(id)
}
