package models.scalikejdbc

import scalikejdbc._

case class Items(
  itemId: String,
  listPrice: Option[BigDecimal] = None) {

  def save()(implicit session: DBSession = Items.autoSession): Items = Items.save(this)(session)

  def destroy()(implicit session: DBSession = Items.autoSession): Int = Items.destroy(this)(session)

}


object Items extends SQLSyntaxSupport[Items] {

  override val schemaName = Some("public")

  override val tableName = "items"

  override val columns = Seq("item_id", "list_price")

  def apply(i: SyntaxProvider[Items])(rs: WrappedResultSet): Items = apply(i.resultName)(rs)
  def apply(i: ResultName[Items])(rs: WrappedResultSet): Items = new Items(
    itemId = rs.get(i.itemId),
    listPrice = rs.get(i.listPrice)
  )

  val i = Items.syntax("i")

  override val autoSession = AutoSession

  def find(itemId: String)(implicit session: DBSession = autoSession): Option[Items] = {
    withSQL {
      select.from(Items as i).where.eq(i.itemId, itemId)
    }.map(Items(i.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[Items] = {
    withSQL(select.from(Items as i)).map(Items(i.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(Items as i)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[Items] = {
    withSQL {
      select.from(Items as i).where.append(where)
    }.map(Items(i.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[Items] = {
    withSQL {
      select.from(Items as i).where.append(where)
    }.map(Items(i.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(Items as i).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    itemId: String,
    listPrice: Option[BigDecimal] = None)(implicit session: DBSession = autoSession): Items = {
    withSQL {
      insert.into(Items).namedValues(
        column.itemId -> itemId,
        column.listPrice -> listPrice
      )
    }.update.apply()

    Items(
      itemId = itemId,
      listPrice = listPrice)
  }

  def batchInsert(entities: Seq[Items])(implicit session: DBSession = autoSession): List[Int] = {
    val params: Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        'itemId -> entity.itemId,
        'listPrice -> entity.listPrice))
    SQL("""insert into items(
      item_id,
      list_price
    ) values (
      {itemId},
      {listPrice}
    )""").batchByName(params: _*).apply[List]()
  }

  def save(entity: Items)(implicit session: DBSession = autoSession): Items = {
    withSQL {
      update(Items).set(
        column.itemId -> entity.itemId,
        column.listPrice -> entity.listPrice
      ).where.eq(column.itemId, entity.itemId)
    }.update.apply()
    entity
  }

  def destroy(entity: Items)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(Items).where.eq(column.itemId, entity.itemId) }.update.apply()
  }

}
