package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.InputFile
import tools.result.Result


class RemoveAllByEmployeesCount : AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "remove from the collection all elements whose employeesCount field value is equivalent to the given one"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int",
            "min" to "0"
        )
    )
    override fun action(data: Map<String, String?>): Result {

        val count = data["value"]!!.toInt()
        val newOrgs = MyCollection<Organization>()
        val result = Result()

        for ( org in orgs ) {
            if ( org.getEmployeesCount()!! == count ) {
                newOrgs.add(org)
            }
        }
        if (newOrgs.size == 0) {
            result.setMessage("No such organizations found\n")
            return result
        }
        for ( org in newOrgs ) {
            orgs.remove(org)
        }
        result.setMessage("Done\n")

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
