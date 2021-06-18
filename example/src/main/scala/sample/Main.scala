package sample

import ch.epfl.scala.action.core
import scala.scalajs.js
import scala.concurrent.Future
import scala.scalajs.js.JSConverters.given
import scala.concurrent.ExecutionContext.Implicits.global


@main def run =
  println("Hello world")
  assert(core.getInput("simpleString") == "Scala actions!")
  assert(core.getBooleanInput("condition"))

  assert(core.getMultilineInput("multiline") == 
   """this sentence is very
   |long so I am breaking
   |over multiple lines
    """.stripMargin
  )

  core.setOutput("output1", "testaction")

  core.exportVariable("OUTPUT2", "testaction")

  core.exportVariable("OUTPUT3", "testaction")
  core.setSecret("OUTPUT3")

  core.addPath("funky")

  //core.setFailed(message: String): Unit = js.native

  core.debug("debug message")
  core.warning("warning message")
  core.info("info message")
  core.error("error message")
  println("isDebug " + core.isDebug())

  core.startGroup("Test group")
  core.info("info message in group")
  core.endGroup()

  core.group[Int]("Test async group", () => Future(3).toJSPromise)

  core.saveState("AState", js.Array[String]("Many Strings", "are here"))
