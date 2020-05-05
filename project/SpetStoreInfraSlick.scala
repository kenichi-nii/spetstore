import com.github.tototoshi.sbt.slick.CodegenPlugin
import sbt.Keys._
import sbt._
import scoverage.ScoverageKeys._

object SpetStoreInfraSlick {
  import com.github.tototoshi.sbt.slick.CodegenPlugin.autoImport._
  import wartremover.WartRemover.autoImport._

  lazy val project = Project(
    """spetstore-infra-slick""",
    file("spetstore-infra-slick")
  ).enablePlugins(Common, CodegenPlugin)
    .settings(
      name := "spetstore-infra-slick"
    )
    .settings(
      libraryDependencies ++= Seq(
        Dependencies.slick.core,
        Dependencies.slick.codegen
      )
    )
    .settings(
      slickCodegenDatabaseUrl := "jdbc:postgresql://localhost:5432/spetstore",
      slickCodegenDriver := slick.jdbc.PostgresProfile,
      slickCodegenJdbcDriver := "org.postgresql.Driver",
      slickCodegenDatabaseUser := "postgres",
      slickCodegenDatabasePassword := "postgres",
      slickCodegenOutputPackage := "models",
      slickCodegenOutputDir := (scalaSource in Compile).value,
      slickCodegenExcludedTables := Seq("flyway_schema_history")
    )
    .settings(
      libraryDependencies ++= Seq(
        Dependencies.silencer.plugin,
        Dependencies.silencer.lib
      ),
      scalacOptions ++= Seq("-P:silencer:pathFilters=src/main/scala/models/Tables.scala")
    )
    .settings(
      wartremoverExcluded += baseDirectory.value / "src" / "main" / "scala" / "models" / "Tables.scala"
    )
    .settings(
      coverageExcludedPackages := "<empty>;.Tables.scala"
    )
}
