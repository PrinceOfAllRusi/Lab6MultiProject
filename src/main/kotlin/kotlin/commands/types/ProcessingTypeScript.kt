package commands.types

import tools.ReadFile
import tools.input.Input
import tools.ConcreteCommand

class ProcessingTypeScript: ProcessingType {
    override fun processing(input: Input, abstractCommand: ConcreteCommand): ConcreteCommand {
        val reader = ReadFile()
        var s: String? = reader.read(input)
        if (s == null) {
            s = ""
        }
        abstractCommand.setScript(s)

        return abstractCommand
    }
}