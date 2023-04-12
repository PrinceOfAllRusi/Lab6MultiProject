package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import java.lang.StringBuilder
import tools.result.Result


/**
 * Filter starts with name
 *
 * @constructor Create empty Filter starts with name
 */
class FilterStartsWithName: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "вывести элементы, значение поля name которых начинается с заданной подстроки"
    private val type: ArgsType = ArgsType.ARG

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result {
        val str: String = data?.get("value").toString()
        val s: StringBuilder = StringBuilder()
        for (org in orgs) {

            if ( str.length > org.getName()!!.length) {
                continue
            }

            val name = str.toCharArray()
            val orgName = org.getName()!!.toCharArray()
            var condition = true

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

        val result: Result = Result(false)
        result.setMessage(s.toString())

        return result
    }

    /**
     * Get description
     *
     * @return
     */
    override fun getDescription(): String = description
    override fun getType(): ArgsType = type
}