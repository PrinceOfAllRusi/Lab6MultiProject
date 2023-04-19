package commands.types

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.Organization
import tools.CreateOrganization
import tools.input.Input
import tools.ConcreteCommand

class ProcessingTypeIdWithObject : ProcessingType, KoinComponent {

    private val creator: CreateOrganization by inject()
    override fun processing(input: Input, abstractCommand: ConcreteCommand): ConcreteCommand {
        val id = input.getNextWord(null).toInt()
        val lastOrganization: Organization = Organization()
        lastOrganization.setId(id)

        val newOrganization: Organization = creator.create( input, lastOrganization )

        abstractCommand.setOrganization(newOrganization)

        return abstractCommand

    }
}