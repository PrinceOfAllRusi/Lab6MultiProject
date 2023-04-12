package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result

/**
 * Clear
 *
 * @constructor Create empty Clear
 */
class Clear: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "очистить коллекцию"
    private val type: ArgsType = ArgsType.NO_ARG

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result? {
        orgs.clear()
        return null
    }
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
}