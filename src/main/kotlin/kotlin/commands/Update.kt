package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import tools.result.Result

/**
 * Update
 *
 * @constructor Create empty Update
 */
class Update: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "обновить значение элемента коллекции, id которого равен заданному"
    private val type: ArgsType = ArgsType.ARG_WITH_OBJECT

    /**
     * Action
     *
     * @param data
     * @return
     */
    override fun action(data: Map<String, Any>?): Result? {
        val orgComp = OrganizationComparator()
        val map: Map<String, Any>? = data

        if ( map == null ) {
            return null
        }

        val lastOrganization: Organization = map.get("lastOrganization") as Organization
        val newOrganization: Organization = map.get("newOrganization") as Organization

        orgs.remove( lastOrganization )
        orgs.add( newOrganization )

        orgs.sortWith( orgComp )

        return null
    }

    /**
     * Get description
     *
     * @return
     */
    override fun getDescription(): String = description

    override fun getType(): ArgsType = type
}