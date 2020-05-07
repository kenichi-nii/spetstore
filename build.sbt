import sbt.Keys._
import sbt._

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val core = SpetStoreCore.project
lazy val infra = SpetStoreInfra.project
lazy val infraSlick = SpetStoreInfraSlick.project
lazy val infraScalikejdbc = SpetStoreInfraScalikejdbc.project
lazy val flyway = SpetStoreFlyway.project
lazy val api = SpetStoreApi.project
lazy val test = SpetStoreTest.project

lazy val root = (project in file("."))
  .enablePlugins(Common)
  .settings(
    name := """spetstore"""
  )
  .aggregate(core, infra, infraSlick, infraScalikejdbc, api, test, flyway)
