import sbt._
import sbt.Keys._

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, Common)
  .settings(
    name := """spetstore""",
    libraryDependencies ++= Seq(
      guice,
      "net.logstash.logback" % "logstash-logback-encoder" % "6.3",
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
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
