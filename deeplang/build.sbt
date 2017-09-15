// Copyright (c) 2015, CodiLime Inc.
//
// Owner: Jacek Laskowski

organization := "io.deepsense"
name         := "deepsense-deeplang"
version      := "0.1.0"
scalaVersion := "2.11.6"

scalacOptions := Seq(
  "-unchecked", "-deprecation", "-encoding", "utf8", "-feature", "-language:existentials",
  "-language:implicitConversions"
)

libraryDependencies ++= Seq(
  "org.scalatest"          %% "scalatest"     % "2.2.4" % "test",
  "com.github.nscala-time" %% "nscala-time"   % "1.8.0",
  "org.scala-lang"         %  "scala-reflect" % scalaVersion.value
)

// Fork to run all test and run tasks in JVM separated from sbt JVM
fork := true