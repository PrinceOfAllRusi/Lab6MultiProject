package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.InputFile
import tools.result.Result

class RemoveAt: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "remove the element at the given position in the collection"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int",
            "min" to "-1"
        )
    )

    override fun action(data: Map<String, String?>): Result {

        val index = data["value"]!!.toInt()
        val result = Result()
        try {
            orgs.removeAt(index)
            result.setMessage("Done\n")
        } catch (e: IndexOutOfBoundsException) {
            result.setMessage("Wrong data\n")
        }

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
