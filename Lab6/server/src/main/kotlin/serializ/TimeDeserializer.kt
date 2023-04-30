package tools.serializ

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.io.IOException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class TimeDeserializer @JvmOverloads constructor(vc: Class<*>? = null) :
    StdDeserializer<LocalDateTime?>(vc) {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext?): LocalDateTime {
        val node: JsonNode = jp.getCodec().readTree(jp)
        val seconds: Long = node.asLong()
        val creationDate: LocalDateTime =  LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneOffset.UTC)

        return creationDate
    }
}