package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.CreateOrganization
import tools.result.Result

class Clear: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val creator: CreateOrganization by inject()
    private val description: String = "очистить коллекцию"
    private var fields: Map<String, Map<String, String>> = mapOf()

    override fun action(data: Map<String, String?>): Result {
        orgs.clear()
        creator.setCounter(0)
        val result = Result()
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}