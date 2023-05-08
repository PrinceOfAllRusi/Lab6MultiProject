package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.input.InputFile
import java.lang.StringBuilder
import tools.result.Result


class FilterStartsWithName: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "display elements whose name field value starts with the given substring"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "String"
        )
    )
    override fun action(data: Map<String, String?   >): Result {
        val str = data["value"]!!
        val s: StringBuilder = StringBuilder()

        val name = str.toCharArray()
        var orgName: CharArray
        var condition: Boolean

        for (org in orgs) {
            if ( str.length > org.getName()!!.length) {
                continue
            }

            orgName = org.getName()!!.toCharArray()
            condition = true

            for (i in 0..name.size-1) {
                if ( name[i] != orgName[i] ) {
                    condition = false
                    break
                }
            }

            if ( condition ) {
                s.append( org.toString() + "\n" )
            }
        }

        val result: Result = Result()
        result.setMessage(s.toString())

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