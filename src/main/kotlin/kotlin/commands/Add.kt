package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import tools.CreateOrganization
import tools.result.Result

/**
 * Add
 *
 * @constructor Create empty Add
 */
class Add : Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "добавить новый элемент в коллекцию"
    private val type: ArgsType = ArgsType.OBJECT

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result? {
        val orgComp = OrganizationComparator()
        var org: Organization? = null
        if ( data != null ) {
            org = data.get("organization") as Organization?
        }

        if ( org != null ){
            orgs.add(org)
            orgs.sortWith(orgComp)
        }
        return null
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
}