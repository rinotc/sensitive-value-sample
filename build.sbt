ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.7"

resolvers += "Atlassian's Maven Public Repository" at "https://packages.atlassian.com/maven-public/"

lazy val root = (project in file("."))
  .settings(
    name := "sensitive-value-sample",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.10" % Test,
      "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test
    )
  )
