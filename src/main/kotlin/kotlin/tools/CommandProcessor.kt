package tools

import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import serializ.TimeSerializer
import tools.input.Input
import tools.result.Result
import serializ.TimeDeserializer
import transmittedData.CommandsData
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.InetSocketAddress
import java.time.LocalDateTime

class CommandProcessor: KoinComponent {

    private val commandsList: CommandsList by inject()

    fun process(input: Input) {

        val result: Result = Result(false)

        var command = ""
        val abstractCommand = ConcreteCommand()
        var concreteCommand = ConcreteCommand()

        val port = 6789
        val host: InetAddress = InetAddress.getLocalHost()
//        val host: InetSocketAddress = InetSocketAddress("172.28.28.21", 3032)
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
//                    sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host)
        clientSocket.send(sendingPacket)

        receivingPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
        clientSocket.receive(receivingPacket)
        receivedData = String(receivingPacket.data, 0, receivingPacket.length)

//        System.out.println(receivedData)

        val commandsData = mapper.readValue<CommandsData>(receivedData)

//        System.out.println("!!!!!!!!!!!!!!!!!")
//        System.out.println(commandsData.getCommandsList())




        while ( true ) {

            concreteCommand.setMessage("")
            command = input.getNextWord(null).lowercase()

            if ( !commandsList.containsCommand(command) ) {
                input.outMsg("Такой команды не существует\n")
            }
            else {
                try {
                    val type = commandsList.getCommand(command)?.getType()
                    if ( type == null ) {
                        continue
                    }
                    abstractCommand.setName(command)
                    concreteCommand = commandsList.getType(type)?.processing(input, abstractCommand)!!
//                    result = commandsList.getCommand(command)?.action(mapData)
                    xml = mapper.writeValueAsString(concreteCommand)

                    sendingDataBuffer = xml.toByteArray()
                    sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
//                    sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host)
                    clientSocket.send(sendingPacket)

                    receivingPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
                    clientSocket.receive(receivingPacket)
                    receivedData = String(receivingPacket.data, 0, receivingPacket.length)

//                    System.out.println(receivedData)

                    concreteCommand = mapper.readValue<ConcreteCommand>(receivedData)

                    input.outMsg(concreteCommand.getMessage())

                } catch ( e: NumberFormatException ) {
                    input.outMsg("Неверные данные\n")
                } catch ( e: NullPointerException ) {
                    input.outMsg("Введены не все данные\n")
                }
            }
            if ( result != null ) {
                input.outMsg(result.getMessage())
            }
            if (result.getExit() == true) {
                break
            }

        }

    }
}