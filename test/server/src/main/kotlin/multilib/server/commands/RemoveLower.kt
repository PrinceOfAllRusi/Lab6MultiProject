package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.InputFile
import tools.result.Result


class RemoveLower: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "remove from the collection all elements smaller than the given one"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int",
            "length" to "27"
        )
    )

    override fun action(data: Map<String, String?>): Result {

        val count = data["value"]!!.toInt()
        val newOrgs = MyCollection<Organization>()

        for ( org in orgs ) {
            if ( org.getPostalAddressStreet().length < count ) {
                newOrgs.add(org)
            }
        }
        for ( org in newOrgs ) {
            orgs.remove(org)
        }

        val result = Result()
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