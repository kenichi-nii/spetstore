import sbt.Keys._
import sbt._
import _root_.io.github.davidmweber.FlywayPlugin

object SpetStoreFlyway {
  lazy val project = Project(
    """spetstore-flyway""",
    file("spetstore-flyway")
  ).enablePlugins(Common, FlywayPlugin)
    .settings(
      name := "spetstore-flyway"
    )
    .settings(
      libraryDependencies ++= Seq(
        Dependencies.postgresql,
        Dependencies.h2
      )
    )
}
