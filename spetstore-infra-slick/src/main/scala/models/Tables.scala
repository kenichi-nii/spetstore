package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Items.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Items
   *  @param itemId Database column item_id SqlType(varchar), PrimaryKey, Length(10,true)
   *  @param listPrice Database column list_price SqlType(numeric), Default(None) */
  case class ItemsRow(itemId: String, listPrice: Option[scala.math.BigDecimal] = None)
  /** GetResult implicit for fetching ItemsRow objects using plain SQL queries */
  implicit def GetResultItemsRow(implicit e0: GR[String], e1: GR[Option[scala.math.BigDecimal]]): GR[ItemsRow] = GR{
    prs => import prs._
    ItemsRow.tupled((<<[String], <<?[scala.math.BigDecimal]))
  }
  /** Table description of table items. Objects of this class serve as prototypes for rows in queries. */
  class Items(_tableTag: Tag) extends profile.api.Table[ItemsRow](_tableTag, "items") {
    def * = (itemId, listPrice) <> (ItemsRow.tupled, ItemsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(itemId), listPrice)).shaped.<>({r=>import r._; _1.map(_=> ItemsRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column item_id SqlType(varchar), PrimaryKey, Length(10,true) */
    val itemId: Rep[String] = column[String]("item_id", O.PrimaryKey, O.Length(10,varying=true))
    /** Database column list_price SqlType(numeric), Default(None) */
    val listPrice: Rep[Option[scala.math.BigDecimal]] = column[Option[scala.math.BigDecimal]]("list_price", O.Default(None))
  }
  /** Collection-like TableQuery object for table Items */
  lazy val Items = new TableQuery(tag => new Items(tag))
}
