package commands.types

import tools.ReadFile
import tools.input.Input

class ProcessingTypeScript: ProcessingType {
    override fun processing(input: Input): Map<String, Any>? {
        val reader = ReadFile()
        val s: String? = reader.read(input)
        if (s == null) {
            return null
        }
        val map: Map<String, Any> = mapOf("script" to s)
        return map
    }
}