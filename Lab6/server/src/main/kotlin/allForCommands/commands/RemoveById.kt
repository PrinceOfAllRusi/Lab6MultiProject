package allForCommands.commands

import com.sun.org.apache.xpath.internal.operations.Bool
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result
import javax.swing.text.StyledEditorKit.BoldAction


class RemoveById: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить элемент из коллекции по его id"
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
        else result.setMessage("Такой огранизации нет в колекции\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}