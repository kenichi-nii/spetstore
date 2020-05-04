import sbt.Keys._
import sbt._

object SpetStoreApi {
  import wartremover.WartRemover.autoImport._

  lazy val project = Project(
    """spetstore-api""",
    file("spetstore-api")
  ).enablePlugins(play.sbt.PlayScala, Common)
    .settings(
      name := "spetstore-api"
    )
    .settings(
      libraryDependencies ++= Seq(
        play.sbt.PlayImport.guice
      )
    )
    .settings(
      play.sbt.routes.RoutesKeys.routesImport := Seq("base.MaybeId")
    )
    .settings(
      wartremoverExcluded ++= play.sbt.routes.RoutesKeys.routes.in(Compile).value
    )
    .settings(
      libraryDependencies ++= Seq(
        compilerPlugin(("com.github.ghik" % "silencer-plugin" % "1.6.0").cross(CrossVersion.full)),
        ("com.github.ghik" % "silencer-lib" % "1.6.0" % Provided).cross(CrossVersion.full)
      ),
      scalacOptions ++= Seq("-P:silencer:pathFilters=target/.*")
    )
    .dependsOn(
      SpetStoreCore.project,
      SpetStoreInfra.project,
      SpetStoreTest.project % Test
    )
}
