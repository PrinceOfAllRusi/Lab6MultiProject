package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationType
import tools.CreateOrganization
import tools.result.Result

class InsertAt: AbstractCommand, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val create: CreateOrganization by inject()
    private val description: String = "добавить новый элемент в заданную позицию"
    private val type: String
    private var fields: Map<String, Map<String, String>>

    constructor() {
        val typeStr = StringBuilder()
        typeStr.append( "Выбирете тип вашей организации из данных вариантов\n" )
        val organizationType = OrganizationType.values()
        for ( i in organizationType.indices ) {
            typeStr.append( organizationType[i].toString() + "\n" )
        }
        type = typeStr.toString()

        fields = mapOf(
            "value" to mapOf<String, String>(
                "type" to "Int"
            ),
            "name" to mapOf<String, String>(
                "title" to "Введите название вашей организации\n",
                "type" to "String"
            ),
            "annualTurnover" to  mapOf<String, String>(
                "title" to "Введите годовой оборот вашей организации\n",
                "type" to "Double",
                "min" to "1"
            ),
            "employeesCount" to mapOf<String, String>(
                "title" to "Введите количество сотрудников в вашей организации\n",
                "type" to "Int",
                "min" to "1"
            ),
            "x" to mapOf<String, String>(
                "title" to "Введите координаты вашей организации по оси X\n",
                "type" to "Int",
                "min" to "-312"
            ),
            "y" to mapOf<String, String>(
                "title" to "Введите координаты вашей организации по оси Y\n",
                "type" to "Long",
                "max" to "212"
            ),
            "type" to mapOf<String, String>(
                "title" to type,
                "type" to "OrganizationType"
            ),
            "street" to mapOf<String, String>(
                "title" to "Введите название улицы, на которой расположена ваша организация\n",
                "type" to "String"
            ),
            "zipCode" to mapOf<String, String>(
                "title" to "Введите код улицы, на которой расположена ваша организация\n",
                "type" to "String",
                "length" to "27"
            )
        )
    }

    override fun action(data: Map<String, String?>): Result {

        val index = data["value"]!!.toInt()
        val org = create.create(data, null)
        val result = Result()
        try {
            orgs.add(index, org)
            result.setMessage("Done\n")
        } catch (e: IndexOutOfBoundsException) {
            result.setMessage("Невозможно добавить в данную позицию\n")
        }
        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}