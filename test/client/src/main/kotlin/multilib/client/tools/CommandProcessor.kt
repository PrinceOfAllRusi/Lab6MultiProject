package tools

import org.koin.core.component.KoinComponent
import tools.input.Input
import tools.result.Result
import multilib.utilities.commandsData.*
import multilib.client.commandsData.ServerCommandsData
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import multilib.utilities.serializ.Serializer

class CommandProcessor: KoinComponent {


    fun process(input: Input) {

        var result: Result = Result()
        val serializer = Serializer()

        var command = ""
        var commandsList = ServerCommandsData()
        var sendCommandsData: ClientCommandsData = ClientCommandsData()
        val dataProcessor = DataProcessing()

        val port = 1313
        val host: InetAddress = InetAddress.getLocalHost()
        val clientSocket  = DatagramSocket()
        var sendingDataBuffer = ByteArray(65535)
        val receivingDataBuffer = ByteArray(65535)
        var sendingPacket: DatagramPacket
        var receivingPacket: DatagramPacket
        var receivedData = ""

        var xml = ""

        sendingDataBuffer = xml.toByteArray()
        sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
        clientSocket.send(sendingPacket)

        receivingPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)

        clientSocket.receive(receivingPacket)

        xml = String(receivingPacket.data, 0, receivingPacket.length)

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

                    sendingDataBuffer = xml.toByteArray()
                    sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
                    clientSocket.send(sendingPacket)

                    receivingPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
                    clientSocket.receive(receivingPacket)
                    receivedData = String(receivingPacket.data, 0, receivingPacket.length)

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