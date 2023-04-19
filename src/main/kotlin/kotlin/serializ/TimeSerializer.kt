package serializ

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneOffset


class TimeSerializer @JvmOverloads constructor(t: Class<LocalDateTime>? = null) : StdSerializer<LocalDateTime>(t) {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(value: LocalDateTime, jgen: JsonGenerator, provider: SerializerProvider) {
        jgen.writeNumber(value.toEpochSecond(ZoneOffset.UTC))
    }
}