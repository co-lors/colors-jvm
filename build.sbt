lazy val root = (project in file("."))
  .settings(
    name                 := "colors",
    organization         := "co.lors",
    managedScalaInstance := false,
    crossPaths           := false,
    version              := "0.2",
    versionScheme        := Some("semver-spec"),
    homepage             := Some(url("https://github.com/co-lors/colors-jvm")),
    licenses             := Seq("Apache License 2.0" -> url("https://opensource.org/licenses/Apache-2.0")),
    libraryDependencies  += "net.aichler" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
    doc / sources        := Seq(),
    publishTo            := {
      val nexus = "https://s01.oss.sonatype.org/"
      if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
      else Some("releases" at nexus + "service/local/staging/deploy/maven2")
    },
    pomIncludeRepository := { _ => false },
    pomExtra             :=
      <scm>
        <url>git@github.com:co-lors/colors-jvm.git</url>
        <connection>scm:git:git@github.com:co-lors/colors-jvm.git</connection>
      </scm>
      <developers>
        <developer>
          <id>soc</id>
          <name>Simon Ochsenreither</name>
          <url>https://github.com/soc</url>
          <roles>
            <role>Project Lead</role>
          </roles>
        </developer>
      </developers>
  )
