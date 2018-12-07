
// cross(CrossVersion.full) required as scala-kernel-api is published for each
// scala version for now.
// Provided means that this dependency will be available to us at compile time,
// yet will not be a transitive dependency of ours. The right version of this
// library is part of almond itself, there's no need to pull a particular version
// ourselves as a transitive dependency.
libraryDependencies += ("sh.almond" %% "scala-kernel-api" % "0.1.12" % Provided).cross(CrossVersion.full)

scalaVersion := "2.12.7"

organization := "sh.almond.example"
name := "example-library-jupyter-api"
version := "0.1.0-SNAPSHOT"
