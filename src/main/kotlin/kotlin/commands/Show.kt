package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.Input
import tools.result.Result


/**
 * Show
 *
 * @constructor Create empty Show
 */
class Show: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести все элементы коллекции"
    private val type: ArgsType = ArgsType.NO_ARG

    /**
     * Action
     *
     * @param data
     * @return
     */
    override fun action(data: Map<String, Any>?): Result {
        val s = StringBuilder()
        for (org in orgs) {
            s.append(org.toString() + "\n")
        }
        val result = Result(false)
        result.setMessage(s.toString())
        return result
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