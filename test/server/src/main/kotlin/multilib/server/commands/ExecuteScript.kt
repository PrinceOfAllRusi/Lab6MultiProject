package allForCommands.commands

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.*
import tools.input.InputFile
import tools.result.Result
import tools.DataList

class ExecuteScript: AbstractCommand(), KoinComponent {

    private val absoluteWay: DataList by inject()
    private val description: String = "read and execute a script from the specified file"
    private var fields: Map<String, Map<String, String>> = mapOf(
        "script" to mapOf<String, String>(
            "title" to "Enter an environment variable containing the path to the file\n",
            "type" to "String"
        )
    )
    override fun action(data: Map<String, String?>): Result {

        val s = data["script"]


        val result = Result()
        result.setMessage("Done\n")

        return result
    }
    override fun getDescription(): String = description
    override fun getFields() = fields
}