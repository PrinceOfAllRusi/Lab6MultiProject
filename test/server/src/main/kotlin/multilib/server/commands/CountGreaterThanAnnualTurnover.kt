package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.InputFile
import tools.result.Result

class CountGreaterThanAnnualTurnover: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "display the number of elements whose annualTurnover field value is greater than the given one"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int"
        )
    )
    override fun action(data: Map<String, String?>): Result {

        val turnover: Double = data["value"]!!.toDouble()
        var count = 0
        for (org in orgs) {
            if (org.getAnnualTurnover()!! > turnover) {
                count++
            }
        }

        val result = Result()
        result.setMessage(count.toString() + "\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
    override fun commandBuilding(mapData: MutableMap<String, String>, data: String): MutableMap<String, String> {
        val input = InputFile(data)

        for (key in fields.keys) {
            mapData.put(key, input.getNextWord(null))
        }

        return mapData
    }
}