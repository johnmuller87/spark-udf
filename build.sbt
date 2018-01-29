name := """spark-udf"""
version := "0.2.0"
organization := "com.ing.wbaa"

scalaVersion := "2.11.11"
scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation")


val root = project.in(file("."))
  .settings(Seq(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % Test,
      "org.iban4j" % "iban4j" % "3.2.1",
      "org.apache.spark" % "spark-sql_2.11" % "2.1.1" % Provided
    ))
  )

// Without this repo, you might get a failure trying to resolve transitive
// dependency org.pentaho:pentaho-aggdesigner-algorithm:5.1.5-jhyde
resolvers += "conjars" at "http://conjars.org/repo"
