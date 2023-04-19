package commands.types

import tools.input.Input
import tools.ConcreteCommand

class ProcessingTypeArg: ProcessingType {
    override fun processing(input: Input, abstractCommand: ConcreteCommand): ConcreteCommand {
        val id = input.getNextWord(null).toInt()
        val org = abstractCommand.getOrganization()
        org.setId(id)
        abstractCommand.setOrganization(org)
        return abstractCommand
    }
}