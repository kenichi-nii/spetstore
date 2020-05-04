import sbt.Keys._
import sbt._

lazy val core = SpetStoreCore.project
lazy val infra = SpetStoreInfra.project
lazy val api = SpetStoreApi.project
lazy val test = SpetStoreTest.project

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, Common)
  .settings(
    name := """spetstore"""
  )
  .aggregate(core, infra, api, test)
