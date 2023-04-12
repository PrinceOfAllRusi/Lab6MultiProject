package tools

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.input.Input
import tools.input.InputFile
import tools.result.Result
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

/**
 * Command processor
 *
 * @constructor Create empty Command processor
 */
class CommandProcessor: KoinComponent {

    private val commandsList: CommandsList by inject()

    /**
     * Process
     *
     * @param input
     */
    fun process(input: Input) {

        var result: Result? = Result(false)
        var mapData: Map<String, Any>?

        var command = ""

        val port = 6789
        val host: InetAddress = InetAddress.getLocalHost()
        val clientSocket  = DatagramSocket()
        var sendingDataBuffer = ByteArray(1024)
        val receivingDataBuffer = ByteArray(1024)
        var sendingPacket: DatagramPacket
        var receivingPacket: DatagramPacket
        var receivedData = ""



        while ( true ) {

            result?.setMessage("")
            command = input.getNextWord(null).lowercase()

//            sendingDataBuffer = command.toByteArray()
//
//            sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
//            clientSocket.send(sendingPacket)
//            receivingPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
//            clientSocket.receive(receivingPacket)
//            receivedData = String(receivingPacket.data)

            if ( !commandsList.containsCommand(command) ) {
                input.outMsg("Такой команды не существует\n")
            }
            else {
                try {
                    val type = commandsList.getCommand(command)?.getType()
                    if ( type == null ) {
                        continue
                    }
                    mapData = commandsList.getType(type)?.processing(input)
                    result = commandsList.getCommand(command)?.action(mapData)

                } catch ( e: NumberFormatException ) {
                    input.outMsg("Неверные данные\n")
                    if ( input.javaClass == InputFile("").javaClass ) {
                        continue
                    }
                } catch ( e: NullPointerException ) {
                    input.outMsg("Введены не все данные\n")
                }
            }
            if ( result != null ) {
                input.outMsg(result.getMessage())
            }
            if (result?.getExit() == true) {
                break
            }

        }

    }
}