package domains.item

import base.MaybeId

trait ItemRepository {
  def findBy(id: MaybeId): Option[Item]
}
