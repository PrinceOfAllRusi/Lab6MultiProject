package commands.types

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.CreateOrganization
import tools.input.Input

class ProcessingTypeIdWithObject : ProcessingType, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val creator: CreateOrganization by inject()
    override fun processing(input: Input): Map<String, Any>? {
        val id = input.getNextWord(null).toInt()
        var lastOrganization: Organization? = null
        for ( org in orgs ) {
            if ( org.getId() == id ) {
                lastOrganization = org
                break
            }
        }
        val newOrganization: Organization = creator.create( input, lastOrganization )!!

        if ( newOrganization == null || lastOrganization == null ) {
            return null
        }

        val map: Map<String, Any> = mapOf("value" to id, "lastOrganization" to lastOrganization, "newOrganization" to newOrganization)

        return map

    }
}