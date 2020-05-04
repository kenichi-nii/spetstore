import sbt._
import sbt.Keys._

object Common extends AutoPlugin {
  override def trigger = allRequirements
  override def requires: Plugins = plugins.JvmPlugin

  import wartremover.WartRemover.autoImport._
  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    organization := "com.github.knii",
    javacOptions in Compile ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8"),
    scalaVersion := "2.13.2",
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
    ),
    scalacOptions in Test ++= Seq("-Yrangepos"),
    autoAPIMappings := true,
    wartremoverErrors in (Compile, compile) ++= Warts.unsafe
  )
}
