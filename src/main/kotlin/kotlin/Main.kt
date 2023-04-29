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

//fun main() {
//    val data: MutableMap<String, MutableMap<String, String>> = mutableMapOf(
//        "value" to mutableMapOf<String, String>(
//            "type" to "Int",
//            "min" to "1"
//        )
//    )
//
//    val s = "trust"
//    System.out.println(OrganizationType.valueOf(s.uppercase()))
//
//}