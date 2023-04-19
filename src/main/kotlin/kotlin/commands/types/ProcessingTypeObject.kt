package commands.types

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.CreateOrganization
import tools.input.Input
import tools.ConcreteCommand

class ProcessingTypeObject : ProcessingType, KoinComponent {

    private val creator: CreateOrganization by inject()
    override fun processing(input: Input, abstractCommand: ConcreteCommand): ConcreteCommand {

        val org = creator.create(input, null)
        abstractCommand.setOrganization(org)

        return abstractCommand
    }
}