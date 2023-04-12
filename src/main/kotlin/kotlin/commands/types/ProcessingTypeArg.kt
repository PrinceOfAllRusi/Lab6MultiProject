package commands.types

import tools.input.Input

class ProcessingTypeArg: ProcessingType {
    override fun processing(input: Input): Map<String, Any> {
        val value = input.getNextWord(null)
        val map: Map<String, Any> = mapOf("value" to value)
        return map
    }
}