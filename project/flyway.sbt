resolvers ++= Seq(
  "Flyway".at("https://davidmweber.github.io/flyway-sbt.repo")
)
addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "6.4.0")
