package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationType
import tools.CreateOrganization
import tools.input.InputFile
import tools.result.Result

class InsertAt: AbstractCommand, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val create: CreateOrganization by inject()
    private val description: String = "add a new element at a given position"
    private val type: String
    private var fields: Map<String, Map<String, String>>

    constructor() {
        val typeStr = StringBuilder()
        typeStr.append( "Select your organization type from these options\n" )
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
                "title" to "Enter the name of your organization\n",
                "type" to "String"
            ),
            "annualTurnover" to  mapOf<String, String>(
                "title" to "Enter the annual turnover of your organization\n",
                "type" to "Double",
                "min" to "1"
            ),
            "employeesCount" to mapOf<String, String>(
                "title" to "Enter the number of employees in your organization\n",
                "type" to "Int",
                "min" to "1"
            ),
            "x" to mapOf<String, String>(
                "title" to "Enter your organization's X coordinates\n",
                "type" to "Int",
                "min" to "-312"
            ),
            "y" to mapOf<String, String>(
                "title" to "Enter your organization's Y coordinates\n",
                "type" to "Long",
                "max" to "212"
            ),
            "type" to mapOf<String, String>(
                "title" to type,
                "type" to "OrganizationType"
            ),
            "street" to mapOf<String, String>(
                "title" to "Enter the name of the street where your organization is located\n",
                "type" to "String"
            ),
            "zipCode" to mapOf<String, String>(
                "title" to "Enter the street code where your organization is located",
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
            result.setMessage("Cannot be added to this position\n")
        }
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