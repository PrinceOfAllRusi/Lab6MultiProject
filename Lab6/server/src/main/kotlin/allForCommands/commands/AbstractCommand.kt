package allForCommands.commands

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
}