import sbt.Keys._
import sbt._

object SpetStoreCore {
  lazy val project = Project(
    """spetstore-core""",
    file("spetstore-core")
  ).enablePlugins(Common)
    .settings(
      name := "spetstore-core"
    )
    .settings(
      libraryDependencies ++= Seq(
        "net.logstash.logback" % "logstash-logback-encoder" % "6.3"
      )
    )
}
