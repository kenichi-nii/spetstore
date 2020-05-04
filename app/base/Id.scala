package base

final case class Id[A](value: Long) extends AnyVal with Identifier
