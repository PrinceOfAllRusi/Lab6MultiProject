package transmittedData

import commands.types.ArgsType
import organization.Organization

class CommandsData {
    private var commandsList: Map<String, Map<String, Any>>
    private var typesList: Map<ArgsType, Int>

    constructor() {
        commandsList = mapOf()
        typesList = mapOf()
    }

    fun getCommandsList() = commandsList
    fun setCommandsList(commandsList: Map<String, Map<String, Any>>) {
        this.commandsList = commandsList
    }
    fun getTypeList() = typesList
    fun setTypeList(typesList: Map<ArgsType, Int>) {
        this.typesList = typesList
    }

}