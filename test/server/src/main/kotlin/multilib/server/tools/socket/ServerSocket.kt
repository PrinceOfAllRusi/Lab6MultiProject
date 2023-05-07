package multilib.server.tools.socket

import multilib.server.commandsData.ServerCommandsData
import multilib.utilities.serializ.Serializer
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class ServerSocket {
    private var port = 1313
    private lateinit var host: InetAddress
    private val serverSocket = DatagramSocket(port)
    private val receivingDataBuffer = ByteArray(65535)
    private var sendingDataBuffer = ByteArray(65535)
    private val inputPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
    private lateinit var outputPacket: DatagramPacket
    private var receivedData = ""
    private var s = ""
    private val commandsData = ServerCommandsData()
    private val serializer = Serializer()

    fun sendCommandsData() {
        val xmlCommands = serializer.serialize(commandsData)
        sendingDataBuffer = xmlCommands.toByteArray()

        outputPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
        serverSocket.send(outputPacket)
    }
    fun send(s: String) {
        sendingDataBuffer = s.toByteArray()

        outputPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
        serverSocket.send(outputPacket)
    }
    fun receive() {
        serverSocket.receive(inputPacket)
        port = inputPacket.port
        host = inputPacket.address
    }
    fun getPort() = port
    fun getHost() = host
    fun getXmlData() = String(inputPacket.data, 0, inputPacket.length)
}