package domains.item

import base.Id

final case class Item(itemId: Id[Item], listPrice: BigDecimal)
