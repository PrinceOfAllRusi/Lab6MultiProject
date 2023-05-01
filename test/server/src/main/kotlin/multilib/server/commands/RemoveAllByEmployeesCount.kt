package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


class RemoveAllByEmployeesCount : AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "удалить из коллекции все элементы, значение поля employeesCount которого эквивалентно заданному"
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
            result.setMessage("Таких организаций не найдено\n")
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
}
