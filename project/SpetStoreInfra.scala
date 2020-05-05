import sbt.Keys._
import sbt._

object SpetStoreInfra {
  lazy val project = Project(
    """spetstore-infra""",
    file("spetstore-infra")
  ).enablePlugins(Common)
    .settings(
      name := "spetstore-infra"
    )
    .settings(
      libraryDependencies ++= Seq(
        play.sbt.PlayImport.guice
      )
    )
    .dependsOn(SpetStoreCore.project, SpetStoreInfraSlick.project)
}
