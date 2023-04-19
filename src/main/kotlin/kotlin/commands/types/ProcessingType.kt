package commands.types

import tools.input.Input
import tools.ConcreteCommand

interface ProcessingType {
    fun processing(input: Input, concreteCommand: ConcreteCommand): ConcreteCommand
}