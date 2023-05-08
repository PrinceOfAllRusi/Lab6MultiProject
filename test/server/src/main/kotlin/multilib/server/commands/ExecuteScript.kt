package allForCommands.commands

import multilib.utilities.commandsData.ClientCommandsData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.CommandsList
import tools.input.InputFile
import tools.result.Result
import tools.DataList

class ExecuteScript: AbstractCommand(), KoinComponent {

    private val absoluteWay: DataList by inject()
    private val commandsList: CommandsList by inject()
    private val description: String = "read and execute a script from the specified file"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "script" to mapOf<String, String>(
            "title" to "Enter an environment variable containing the path to the file\n",
            "type" to "String"
        )
    )
    override fun action(data: Map<String, String?>): Result {

        var mapData: MutableMap<String, String> = mutableMapOf()
        val s = data["script"]
        val input = InputFile(s)
        var command = ""
        var data = ""
        var dataList = ""

        try {
            while(true) {
                if (commandsList.containsCommand(command)) {
                    data = input.getNextWord(null)
                    while(!commandsList.containsCommand(data)) {
                        dataList += data + "\n"
                        data = input.getNextWord(null)
                    }
                    mapData = commandsList.getCommand(command)!!.commandBuilding(mapData, dataList)
                    commandsList.getCommand(command)!!.action(mapData)
                    dataList = ""
                    mapData.clear()
                    command = data
                    continue
                }
                command = input.getNextWord(null)

            }
        } catch (e: NoSuchElementException) {
            mapData = commandsList.getCommand(command)!!.commandBuilding(mapData, dataList)
            commandsList.getCommand(command)!!.action(mapData)
        }

        val result = Result()
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}