import modul.SingletonObject.mod
import tools.CommandProcessor
import tools.input.InputSystem
import org.koin.core.context.GlobalContext.startKoin

fun main() {

    startKoin {
        modules(mod)
    }
    val input= InputSystem()

    val commandProcessor: CommandProcessor = CommandProcessor()
    commandProcessor.process(input)
}