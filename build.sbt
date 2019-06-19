name := "glopart-catalog-parser"

version := "0.1"

scalaVersion := "2.12.8"

val monix = "2.3.3"
val cats = "1.6.0"
val circe = "0.10.0"
val scalatest = "3.0.8"
val slick = "3.2.3"
val slf4j = "1.7.26"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.4",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.slf4j" % "slf4j-api" % slf4j,
  "io.monix" %% "monix" % monix,
  "org.typelevel" %% "cats-core" % cats,
  "io.circe" %% "circe-core" % circe,
  "io.circe" %% "circe-generic" % circe,
  "io.circe" %% "circe-parser" % circe,
  "org.scalatest" %% "scalatest" % scalatest % "test"
)

// adding slick

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % slick,
  "com.typesafe.slick" %% "slick-hikaricp" % slick,
  "org.postgresql" % "postgresql" % "42.2.5"
)