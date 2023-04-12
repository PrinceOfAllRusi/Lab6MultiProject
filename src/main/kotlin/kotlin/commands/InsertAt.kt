package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.result.Result


/**
 * Insert at
 *
 * @constructor Create empty Insert at
 */
class InsertAt: Command, KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "добавить новый элемент в заданную позицию"
    private val type: ArgsType = ArgsType.INDEX_WITH_OBJECT

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result? {

        if ( data == null ) {
            return null
        }

        val strIndex: String = data["value"].toString()
        val index = strIndex.toInt()
        val org = data.get("organization") as Organization

        orgs.add(index, org)


        return null
    }

    /**
     * Get description
     *
     * @return
     */
    override fun getDescription(): String = description

    /**
     * Get type
     *
     * @return
     */
    override fun getType(): ArgsType = type
}