package tools

import CommandsData.ClientCommandsData
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.koin.core.component.KoinComponent
import serializ.TimeSerializer
import tools.input.Input
import tools.result.Result
import serializ.TimeDeserializer
import transmittedData.ServerCommandsData
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.time.LocalDateTime

class CommandProcessor: KoinComponent {


    fun process(input: Input) {

        var result: Result = Result()

        var command = ""
        var commandsList = ServerCommandsData()
        var sendCommandsData: ClientCommandsData = ClientCommandsData()
        val dataProcessor = DataProcessing()

        val port = 6789
        val host: InetAddress = InetAddress.getLocalHost()
        val clientSocket  = DatagramSocket()
        var sendingDataBuffer = ByteArray(65535)
        val receivingDataBuffer = ByteArray(65535)
        var sendingPacket: DatagramPacket
        var receivingPacket: DatagramPacket
        var receivedData = ""

        val mapper = XmlMapper()
        val module = SimpleModule()
        module.addSerializer(LocalDateTime::class.java, TimeSerializer())
        module.addDeserializer(LocalDateTime::class.java, TimeDeserializer())
        mapper.registerModule(module)
        var xml = ""

        sendingDataBuffer = xml.toByteArray()
        sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
        clientSocket.send(sendingPacket)

        receivingPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)

        clientSocket.receive(receivingPacket)

        xml = String(receivingPacket.data, 0, receivingPacket.length)

        commandsList = mapper.readValue<ServerCommandsData>(xml)

        while ( true ) {

            result.setMessage("")

            command = input.getNextWord(null).lowercase()

            if ( !commandsList.getMapCommands().containsKey(command) ) {
                input.outMsg("Такой команды не существует\n")
            }
            else {
                try {
                    sendCommandsData = dataProcessor.setData(input, commandsList.getMapCommands()[command]!!)

                    sendCommandsData.setName(command)

                    xml = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sendCommandsData)

                    sendingDataBuffer = xml.toByteArray()
                    sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
                    clientSocket.send(sendingPacket)

                    receivingPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
                    clientSocket.receive(receivingPacket)
                    receivedData = String(receivingPacket.data, 0, receivingPacket.length)

                    result = mapper.readValue<Result>(receivedData)

                    input.outMsg(result.getMessage())

                } catch ( e: NumberFormatException ) {
                    input.outMsg("Неверные данные\n")
                } catch ( e: NullPointerException ) {
                    input.outMsg("Введены не все данные\n")
                }
            }
            if (result.getExit() == true) {
                break
            }
        }

    }
}