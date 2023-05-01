package multilib.utilities.serializ


import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.time.LocalDateTime

class Serializer {
    val mapper: XmlMapper
    val module: SimpleModule
    var xml: String
    constructor() {
        mapper = XmlMapper()
        module = SimpleModule()
        module.addSerializer(LocalDateTime::class.java, TimeSerializer())
        module.addDeserializer(LocalDateTime::class.java, TimeDeserializer())
        mapper.registerModule(module)
        xml = ""
    }
    fun <T> serialize(obj: T): String {
        xml = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj)
        return xml
    }
    inline fun <reified T> deserialize(s: String): T {
        return mapper.readValue(s)
    }
}