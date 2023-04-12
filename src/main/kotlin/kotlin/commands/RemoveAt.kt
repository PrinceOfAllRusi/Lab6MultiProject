package commands

import com.sun.org.apache.xpath.internal.Arg
import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.Input
import tools.result.Result


/**
 * Remove at
 *
 * @constructor Create empty Remove at
 */
class RemoveAt: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить элемент, находящийся в заданной позиции коллекции"
    private val type: ArgsType = ArgsType.ARG

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result? {
        val indexOrg: String = data!!["value"].toString()
        val index = indexOrg.toInt()
        orgs.removeAt(index)

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
