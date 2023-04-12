package commands.types

import tools.input.Input

interface ProcessingType {
    fun processing(input: Input): Map<String, Any>?
}