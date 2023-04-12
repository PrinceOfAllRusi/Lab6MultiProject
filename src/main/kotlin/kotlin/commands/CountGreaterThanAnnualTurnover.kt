package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.Input
import tools.result.Result

/**
 * Count greater than annual turnover
 *
 * @constructor Create empty Count greater than annual turnover
 */
class CountGreaterThanAnnualTurnover: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести количество элементов, значение поля annualTurnover которых больше заданного"
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
        val strTurnover =  data.get("value").toString()
        val turnover: Double = strTurnover.toDouble()
        var count = 0
        for (org in orgs) {
            if (org.getAnnualTurnover()!! > turnover) {
                count++
            }
        }

        val result = Result(false)
        result.setMessage(count.toString() + "\n")

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