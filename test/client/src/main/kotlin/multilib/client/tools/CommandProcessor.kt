package tools

import org.koin.core.component.KoinComponent
import tools.input.Input
import tools.result.Result
import multilib.utilities.commandsData.*
import multilib.client.commandsData.ServerCommandsData
import multilib.client.tools.socket.ClientSocket
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import multilib.utilities.serializ.Serializer

class CommandProcessor: KoinComponent {


    fun process(input: Input) {

        var result: Result = Result()
        val serializer = Serializer()
        val socket = ClientSocket()

        var command = ""
        var commandsList = ServerCommandsData()
        var sendCommandsData: ClientCommandsData = ClientCommandsData()
        val dataProcessor = DataProcessing()
        var xml = ""
        var receivedData = ""

        socket.send(xml)
        xml = socket.receive()

        commandsList = serializer.deserialize(xml)

        while ( true ) {

            result.setMessage("")

            command = input.getNextWord(null).lowercase()

            if ( !commandsList.getMapCommands().containsKey(command) ) {
                input.outMsg("This command does not exist\n")
            }
            else {
                try {
                    sendCommandsData = dataProcessor.setData(input, commandsList.getMapCommands()[command]!!)

                    sendCommandsData.setName(command)

                    xml = serializer.serialize(sendCommandsData)

                    socket.send(xml)
                    receivedData = socket.receive()

                    result = serializer.deserialize(receivedData)

                    input.outMsg(result.getMessage())

                } catch ( e: NumberFormatException ) {
                    input.outMsg("Wrong data\n")
                } catch ( e: NullPointerException ) {
                    input.outMsg("Not all data entered\n")
                }
            }
            if (result.getExit() == true) {
                break
            }
        }

    }
}