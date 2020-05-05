import sbt._

object Dependencies {
  private object Version {
    val slick = "3.3.2"
    val scalikejdbc = "3.4.1"
    val silencer = "1.6.0"
    val postgresql = "42.2.12"
  }

  val postgresql = "org.postgresql" % "postgresql" % Version.postgresql
  val h2 = "com.h2database" % "h2" % "1.4.200"

  object slick {
    val core = "com.typesafe.slick" %% "slick" % Version.slick
    val hikaricp = "com.typesafe.slick" %% "slick-hikaricp" % Version.slick
    val codegen = "com.typesafe.slick" %% "slick-codegen" % Version.slick
  }
  object scalikejdbc {
    val core = "org.scalikejdbc" %% "scalikejdbc" % Version.scalikejdbc
    val config = "org.scalikejdbc" %% "scalikejdbc-config" % Version.scalikejdbc
    val test = "org.scalikejdbc" %% "scalikejdbc-test" % Version.scalikejdbc
  }

  object silencer {
    val plugin = compilerPlugin(("com.github.ghik" % "silencer-plugin" % Version.silencer).cross(CrossVersion.full))
    val lib = ("com.github.ghik" % "silencer-lib" % Version.silencer % Provided).cross(CrossVersion.full)
  }
}
