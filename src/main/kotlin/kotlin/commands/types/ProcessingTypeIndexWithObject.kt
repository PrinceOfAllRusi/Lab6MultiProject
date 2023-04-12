package commands.types

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.Organization
import tools.CreateOrganization
import tools.input.Input

class ProcessingTypeIndexWithObject : ProcessingType, KoinComponent {

    private val creator: CreateOrganization by inject()
    override fun processing(input: Input): Map<String, Any>? {
        val value = input.getNextWord(null)

        val newOrganization: Organization = creator.create( input, null )!!

        if ( newOrganization == null ) {
            return null
        }

        val map: Map<String, Any> = mapOf("value" to value, "organization" to newOrganization)

        return map
    }
}