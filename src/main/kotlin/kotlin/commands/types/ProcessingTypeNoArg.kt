package commands.types

import tools.input.Input

class ProcessingTypeNoArg : ProcessingType {
    override fun processing(input: Input): Map<String, Any>? {
        return null
    }
}