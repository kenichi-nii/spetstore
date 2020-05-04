package domains.category

import base.Id

final case class Category(id: Id[Category], name: String, description: String)
