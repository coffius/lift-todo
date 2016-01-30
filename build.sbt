//import com.earldouglas.xsbtwebplugin.WarPlugin
//import com.earldouglas.xsbtwebplugin.WebappPlugin

organization := "io.koff"

name := "lift-todo"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.1"

WarPlugin.projectSettings

WebappPlugin.projectSettings

libraryDependencies ++= {
  val liftVersion = "2.6-RC1"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
    "org.eclipse.jetty"       %  "jetty-webapp"                       % "9.2.7.v20150116"     % "compile",
    "org.eclipse.jetty"       %  "jetty-plus"                         % "9.2.7.v20150116"     % "compile", // For Jetty Config
    "org.eclipse.jetty.orbit" %  "javax.servlet"                      % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"          %  "logback-classic"                    % "1.0.6"
  )
}

lazy val root = (project in file(".")).enablePlugins(JettyPlugin).enablePlugins(JavaAppPackaging)

mainClass in Compile := Some("bootstrap.liftweb.MainClass")