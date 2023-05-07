package multilib.client.tools.socket

import multilib.utilities.serializ.Serializer
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class ClientSocket {
    private val port = 1313
    private val host: InetAddress = InetAddress.getLocalHost()
    private val clientSocket  = DatagramSocket()
    private var sendingDataBuffer = ByteArray(65535)
    private val receivingDataBuffer = ByteArray(65535)
    private lateinit var sendingPacket: DatagramPacket
    private var receivingPacket: DatagramPacket = DatagramPacket(receivingDataBuffer, receivingDataBuffer.size)
    private var receivedData = ""
    private var s = ""

    fun send(s: String) {
        sendingDataBuffer = s.toByteArray()
        sendingPacket = DatagramPacket(sendingDataBuffer, sendingDataBuffer.size, host, port)
        clientSocket.send(sendingPacket)
    }
    fun receive(): String {
        clientSocket.receive(receivingPacket)
        s = String(receivingPacket.data, 0, receivingPacket.length)
        return s
    }
}
