package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.Input
import tools.result.Result


/**
 * Remove by id
 *
 * @constructor Create empty Remove by id
 */
class RemoveById: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить элемент из коллекции по его id"
    private val type: ArgsType = ArgsType.ARG

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result? {
        var strId: String = ""

        if (data != null) {
            strId = data["value"].toString()
        }

        val id: Int = strId.toInt()

        for ( org in orgs ) {
            if ( org.getId() == id ) {
                orgs.remove( org )
                break
            }
        }

        return null
    }

    /**
     * Get description
     *
     * @return
     */
    override fun getDescription(): String = description

    /**
     * Get type
     *
     * @return
     */
    override fun getType(): ArgsType = type
}