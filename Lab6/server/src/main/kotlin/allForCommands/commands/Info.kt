package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result

class Info : AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести информацию о коллекции"
    private var fields: Map<String, Map<String, String>> = mapOf()
    override fun action(data: Map<String, String?>): Result {
        val s = StringBuilder()
        s.append( "Тип коллекции " + orgs.javaClass.toString() + "\n" )
        s.append( "Дата инициализации " + orgs.getCreationDate() + "\n" )
        s.append( "Количество элементов " + orgs.size + "\n" )
        val result = Result()
        result.setMessage(s.toString())

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}