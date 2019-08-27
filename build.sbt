import CrossVersion.partialVersion

name := "f0"

organization := "com.clarifi"

description := "Multi-language serialization protocol."

version := "1.1.3"

scalaVersion := "2.13.0"

crossScalaVersions := Seq("2.9.2", "2.9.3", "2.10.5", "2.11.6", "2.13.0")

scalacOptions ++= Seq("-deprecation", "-unchecked")

scalacOptions ++= {
  val non29 = Seq("-feature", "-language:implicitConversions", "-language:higherKinds", "-language:existentials")
  partialVersion(scalaVersion.value) match {
    case Some((2, 9)) => Seq("-Ydependent-method-types")
    case Some((2, 10)) => non29
    case Some((2, 11)) => non29 ++ Seq("-Ywarn-unused-import")
    case sv => Seq("-Xlint", "-feature", "-language:implicitConversions", "-language:higherKinds")
  }
}

libraryDependencies += {
  val scalacheck = if (scalaVersion.value == "2.9.2") "1.10.1" else "1.14.0"
  "org.scalacheck" %% "scalacheck" % scalacheck % "test"
}

Seq(bintraySettings:_*)

licenses ++= (version)(v => Seq("MIT" -> url("https://github.com/joshcough/f0/blob/%s/LICENSE".format(v)))).value

publishMavenStyle := true
