package allForCommands.commands

import multilib.utilities.commandsData.ClientCommandsData
import tools.input.Input
import tools.input.InputFile
import tools.result.Result

abstract class AbstractCommand {
    private var description: String
    private var fields: Map<String, Map<String, String>>
    constructor() {
        this.description = ""
        this.fields = mapOf()
    }

    open fun action(map: Map<String, String?>): Result? {
        return null
    }
    open fun getDescription(): String = description
    open fun getFields() = fields
    open fun commandBuilding(mapData: MutableMap<String, String>, data: String): MutableMap<String, String> {
        val input = InputFile(data)

        for (key in fields.keys) {
            mapData.put(key, input.getNextWord(null))
        }

        return mapData
    }
}