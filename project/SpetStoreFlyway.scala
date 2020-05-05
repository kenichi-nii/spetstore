import sbt.Keys._
import sbt._
import _root_.io.github.davidmweber.FlywayPlugin

object SpetStoreFlyway {
  import _root_.io.github.davidmweber.FlywayPlugin.autoImport._

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
    .settings(
      flywayDriver := "org.postgresql.Driver",
      flywayUrl := "jdbc:postgresql://localhost:5432/spetstore",
      flywayUser := "postgres",
      flywayPassword := "postgres",
      flywaySchemas := Seq("public"),
      flywayLocations := Seq("filesystem:./spetstore-flyway/src/main/resources/db/migration")
    )
}
