package multilib.client

import multilib.utilities.modul.SingletonObject
import tools.CommandProcessor
import tools.input.InputSystem
import org.koin.core.context.GlobalContext.startKoin

fun main() {

    startKoin {
        modules(SingletonObject.mod)
    }
    val input= InputSystem()

    val commandProcessor: CommandProcessor = CommandProcessor()
    commandProcessor.process(input)
}