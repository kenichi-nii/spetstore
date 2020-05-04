package item

import base.{Id, MaybeId}
import domains.item.{Item, ItemRepository}

class ItemRepositoryJdbc extends ItemRepository {
  override def findBy(maybeId: MaybeId): Option[Item] = Some(Item(Id[Item](maybeId.value), listPrice = 10))
}
