package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.Input
import tools.result.Result

/**
 * Info
 *
 * @constructor Create empty Info
 */
class Info : Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести информацию о коллекции"
    private val type: ArgsType = ArgsType.NO_ARG

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result {
        val s = StringBuilder()
        s.append( "Тип коллекции " + orgs.javaClass.toString() + "\n" )
        s.append( "Дата инициализации " + orgs.getCreationDate() + "\n" )
        s.append( "Количество элементов " + orgs.size + "\n" )
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
    override fun getType(): ArgsType = type
}