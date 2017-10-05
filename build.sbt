organization := "com.github.biopet"
name := "biopet-spark-utils"

scalaVersion := "2.11.11"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "com.github.biopet" %% "biopet-common-utils" % "0.1.0-SNAPSHOT" changing()
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"

libraryDependencies += "com.github.biopet" %% "biopet-test-utils" % "0.1.0-SNAPSHOT" % Test changing()

useGpg := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommand("publishSigned"),
  setNextVersion,
  commitNextVersion,
  releaseStepCommand("sonatypeReleaseAll"),
  pushChanges
)
