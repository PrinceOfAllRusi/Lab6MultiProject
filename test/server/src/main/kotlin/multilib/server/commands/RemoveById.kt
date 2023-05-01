package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


class RemoveById: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "remove element from collection by its id"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "value" to mapOf<String, String>(
            "type" to "Int",
            "min" to "0"
        )
    )

    override fun action(data: Map<String, String?>): Result {

        val id: Int = data["value"]!!.toInt()
        var condition: Boolean = false

        for ( org in orgs ) {
            if ( org.getId() == id ) {
                orgs.remove( org )
                condition = true
                break
            }
        }
        val result = Result()
        if (condition) result.setMessage("Done\n")
        else result.setMessage("This organization is not in the collection\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}