package commands.types

import tools.input.Input
import tools.ConcreteCommand

class ProcessingTypeNoArg : ProcessingType {
    override fun processing(input: Input, abstractCommand: ConcreteCommand): ConcreteCommand {
        return abstractCommand
    }
}