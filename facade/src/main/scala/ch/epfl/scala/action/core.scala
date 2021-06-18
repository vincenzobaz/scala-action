package ch.epfl.scala.action

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

trait InputOptions:
  val required: Boolean

object InputOptions:
  val default: InputOptions = new InputOptions:
    override val required = false

@js.native
@JSImport("@actions/core", JSImport.Namespace)
object core extends js.Object:
  def getInput(name: String, inputParams: InputOptions = InputOptions.default): String = js.native
  def getBooleanInput(name: String, inputOptions: InputOptions = InputOptions.default): Boolean = js.native
  def getMultilineInput(name: String, inputOptions: InputOptions = InputOptions.default): String = js.native
  def setOutput(key: String, value: String): Unit = js.native
  def exportVariable(key: String, value: String): Unit = js.native
  def setSecret(name: String): Unit = js.native
  def addPath(path: String): Unit = js.native
  def setFailed(message: String): Unit = js.native

  def debug(message: String): Unit = js.native
  def warning(message: String): Unit = js.native
  def info(message: String): Unit = js.native
  def error(message: String): Unit = js.native
  def isDebug(): Boolean = js.native

  def startGroup(message: String): Unit = js.native
  def endGroup(): Unit = js.native
  def group[A](name: String, p: () => js.Promise[A]): js.Promise[A] = js.native

  def saveState(name: String, content: js.Any): Unit = js.native
