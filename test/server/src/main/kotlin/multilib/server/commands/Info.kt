package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result

class Info : AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "display information about the collection"
    private var fields: Map<String, Map<String, String>> = mapOf()
    override fun action(data: Map<String, String?>): Result {
        val s = StringBuilder()
        s.append( "Collection type " + orgs.javaClass.toString() + "\n" )
        s.append( "Initialization date " + orgs.getCreationDate() + "\n" )
        s.append( "Amount of elements " + orgs.size + "\n" )
        val result = Result()
        result.setMessage(s.toString())

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}