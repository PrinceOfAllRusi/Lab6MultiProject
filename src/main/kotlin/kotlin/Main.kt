
import modul.SingletonObject.mod
import tools.CommandProcessor
import tools.input.InputSystem
import org.koin.core.context.GlobalContext.startKoin
import tools.ConversionXMLtoObject

/**
 * Main
 *
 */
fun main() {

    startKoin {
        modules(mod)
    }
//    val convertor = ConversionXMLtoObject()
    val input= InputSystem()
//    convertor.convert(input)

    val commandProcessor: CommandProcessor = CommandProcessor()
    commandProcessor.process(input)
}