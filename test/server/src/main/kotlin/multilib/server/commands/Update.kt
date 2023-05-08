package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationComparator
import organization.OrganizationType
import tools.CreateOrganization
import tools.input.InputFile
import tools.result.Result

class Update: AbstractCommand, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val creator: CreateOrganization by inject()
    private val type: String
    private val description: String = "update the value of the collection element whose id is equal to the given one"
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
                "type" to "String",
                "null" to "true"
            ),
            "annualTurnover" to  mapOf<String, String>(
                "title" to "Enter the annual turnover of your organization\n",
                "type" to "Double",
                "min" to "1",
                "null" to "true"
            ),
            "employeesCount" to mapOf<String, String>(
                "title" to "Enter the number of employees in your organization\n",
                "type" to "Int",
                "min" to "1",
                "null" to "true"
            ),
            "x" to mapOf<String, String>(
                "title" to "Enter your organization's X coordinates\n",
                "type" to "Int",
                "min" to "-312",
                "null" to "true"
            ),
            "y" to mapOf<String, String>(
                "title" to "Enter your organization's Y coordinates\n",
                "type" to "Long",
                "max" to "212",
                "null" to "true"
            ),
            "type" to mapOf<String, String>(
                "title" to type,
                "type" to "OrganizationType",
                "null" to "true"
            ),
            "street" to mapOf<String, String>(
                "title" to "Enter the name of the street where your organization is located\n",
                "type" to "String",
                "null" to "true"
            ),
            "zipCode" to mapOf<String, String>(
                "title" to "Enter the street code where your organization is located\n",
                "type" to "String",
                "length" to "27",
                "null" to "true"
            )
        )
    }

    override fun action(data: Map<String, String?>): Result {

        val id = data["value"]!!.toInt()
        var lastOrganization = Organization()
        for ( org in orgs ) {
            if ( id == org.getId() ) {
                lastOrganization = org
                break
            }
        }
        val newOrganization: Organization = creator.create(data, lastOrganization)
        val orgComp = OrganizationComparator()

        orgs.remove( lastOrganization )
        orgs.add( newOrganization )

        orgs.sortWith( orgComp )

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