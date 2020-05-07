import sbt.Keys._
import sbt._
import scalikejdbc.mapper.ScalikejdbcPlugin
import scoverage.ScoverageKeys._

object SpetStoreInfraScalikejdbc {
  import wartremover.WartRemover.autoImport._

  lazy val project = Project(
    """spetstore-infra-scalikejdbc""",
    file("spetstore-infra-scalikejdbc")
  ).enablePlugins(Common, ScalikejdbcPlugin)
    .settings(
      name := "spetstore-infra-scalikejdbc"
    )
    .settings(
      libraryDependencies ++= Seq(
        Dependencies.scalikejdbc.core,
        Dependencies.scalikejdbc.config
      )
    )
    .settings(
      libraryDependencies ++= Seq(
        Dependencies.silencer.plugin,
        Dependencies.silencer.lib
      ),
      scalacOptions ++= Seq("-P:silencer:pathFilters=src/main/scala/models/scalikejdbc/")
    )
    .settings(
      wartremoverExcluded ++= ((baseDirectory.value / "src" / "main" / "scala" / "models" / "scalikejdbc") ** "*.scala").get
    )
    .settings(
      coverageExcludedPackages := "<empty>;models\\.scalikejdbc\\..*"
    )
}
