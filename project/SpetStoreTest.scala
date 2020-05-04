import sbt.Keys._
import sbt._

object SpetStoreTest {
  lazy val project = Project(
    """spetstore-test""",
    file("spetstore-test")
  ).enablePlugins(Common)
    .settings(
      name := "spetstore-test"
    )
    .settings(
      libraryDependencies ++= Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0"
      )
    )
    .dependsOn(SpetStoreCore.project)
}
