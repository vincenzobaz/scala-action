val scalaV = "3.0.0"

lazy val example = project
  .in(file("example"))
  .settings(
    scalaVersion := scalaV,
    scalaJSUseMainModuleInitializer := true,
    Compile / fastLinkJS / scalaJSLinkerOutputDirectory := baseDirectory.value / "dist_js" ,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
  )
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(facade)

lazy val facade = project
  .in(file("facade"))
  .settings(
    scalaVersion := scalaV,
    libraryDependencies += "org.scalameta" %%% "munit" % "0.7.26" % Test,
    Test / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
  )
  .enablePlugins(ScalaJSPlugin)

lazy val root = project
  .in(file("."))
  .aggregate(example, facade)
