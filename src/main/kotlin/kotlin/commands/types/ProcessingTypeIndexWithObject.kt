package commands.types

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.Organization
import tools.CreateOrganization
import tools.input.Input
import tools.ConcreteCommand

class ProcessingTypeIndexWithObject : ProcessingType, KoinComponent {

    private val creator: CreateOrganization by inject()
    override fun processing(input: Input, abstractCommand: ConcreteCommand): ConcreteCommand {
        val index = input.getNextWord(null).toInt()
        val newOrganization: Organization = creator.create( input, null )
        abstractCommand.setIndex(index)
        abstractCommand.setOrganization(newOrganization)
        return abstractCommand
    }
}