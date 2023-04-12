package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.Input
import tools.result.Result


/**
 * Remove lower
 *
 * @constructor Create empty Remove lower
 */
class RemoveLower: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить из коллекции все элементы, меньшие, чем заданный"
    private val type: ArgsType = ArgsType.ARG

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result? {
        if ( data == null ) {
            return null
        }

        val strCount: String = data["value"].toString()
        val count = strCount.toInt()
        val newOrgs = MyCollection<Organization>()

        for ( org in orgs ) {
            if ( org.getEmployeesCount()!! < count ) {
                newOrgs.add(org)
            }
        }
        for ( org in newOrgs ) {
            orgs.remove(org)
        }

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