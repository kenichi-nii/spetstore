flywayDriver := "org.postgresql.Driver"
flywayUrl := "jdbc:postgresql://localhost:5432/spetstore"
flywayUser := "postgres"
flywayPassword := "postgres"
flywaySchemas := Seq("public")
flywayLocations := Seq("filesystem:./spetstore-flyway/src/main/resources/db/migration")
