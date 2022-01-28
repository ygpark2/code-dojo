
name := "slice-code-scala"

version := "1.0"

scalaVersion := "2.13.8"

organization := "com.slice"


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.3" % "test",
  "org.scalactic" %% "scalactic" % "3.2.2"
)

Compile / scalaSource := baseDirectory.value / "src" / "main" / "scala"
Compile / resourceDirectory := baseDirectory.value / "resources"

// mainClass in (Compile, run) := Some("com.slice.MainApp")
// mainClass in (Compile, packageBin) := Some("com.slice.MainApp")
