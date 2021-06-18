package ch.epfl.scala.action

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobalScope
import scala.scalajs.js.JSConverters.given
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@js.native
trait Process extends js.Object:
  var env: js.Dictionary[String] = js.native
  
@js.native
@JSGlobalScope
object Globals extends js.Object:
  val process: Process = js.native

class CoreTest extends munit.FunSuite:
  override def beforeEach(context: BeforeEach): Unit =
    Globals.process.env = js.Dictionary(
      "INPUT_SIMPLE_STRING" -> "astring",
      "INPUT_MULTILINE_STRING" -> "this sentence is very\nlong so I am breaking\nover multiple lines",
      "INPUT_BOOLEAN_CONDITION" -> "TRUE",
    )

  test("getInput") {
    assertEquals(core.getInput("simple string"), "astring")
  }

  test("getMultilineInput") {
    assertEquals(core.getMultilineInput("multiline string").toList, List(
      "this sentence is very",
      "long so I am breaking",
      "over multiple lines"
    ))
  }

  test("getBooeleanInput") {
    assert(core.getBooleanInput("boolean condition"))
  }

  test("Call other methods") {
    core.setOutput("output1", "testaction")
    core.exportVariable("OUTPUT2", "testaction")
    core.exportVariable("OUTPUT3", "testaction")
    core.setSecret("OUTPUT3")
    core.addPath("funky")
    core.setFailed("my error message")
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
  }
end CoreTest

 