package services

import base.MaybeId
import domains.item.{Item, ItemError, ItemRepository}
import javax.inject.Inject

class CatalogAppService @Inject() (val itemRepository: ItemRepository) {
  def findItem(itemId: MaybeId): Either[ItemError, Item] =
    itemRepository.findBy(itemId).toRight(ItemError.notFound(itemId))
}
