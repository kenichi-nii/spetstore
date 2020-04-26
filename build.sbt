import sbt._
import sbt.Keys._
import play.sbt.routes.RoutesKeys._

name := """spetstore"""
organization := "com.github.knii"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.2"
scalacOptions ++= Seq(
  "-feature",
  "-explaintypes",
  "-deprecation",
  "-unchecked",
  "-Xlint",
  "-Xfatal-warnings",
  "-Ywarn-dead-code",
  "-Ywarn-unused",
  "-Ywarn-numeric-widen",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions"
)

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.1.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.github.knii.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.github.knii.binders._"

wartremoverErrors in (Compile, compile) ++= Warts.unsafe
wartremoverExcluded ++= routes.in(Compile).value

import play.sbt.routes.RoutesKeys
RoutesKeys.routesImport := Seq.empty

val silencerVersion = "1.6.0"
libraryDependencies ++= Seq(
  compilerPlugin("com.github.ghik" % "silencer-plugin" % silencerVersion cross CrossVersion.full),
  "com.github.ghik" % "silencer-lib" % silencerVersion % Provided cross CrossVersion.full
)
scalacOptions ++= Seq(
  "-P:silencer:pathFilters=target/scala-2.13/routes/main/.*"
)
