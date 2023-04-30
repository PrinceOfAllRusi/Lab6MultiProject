package allForCommands.commands

import tools.result.Result

class Exit : AbstractCommand() {

    private val description: String = "завершить программу (без сохранения в файл)"
    private var fields: Map<String, Map<String, String>> = mapOf()

    override fun action(data: Map<String, String?>): Result {

        val result = Result()
        result.setExit(true)

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}