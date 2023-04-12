package commands

import commands.types.ArgsType
import tools.input.Input
import tools.result.Result

/**
 * Exit
 *
 * @constructor Create empty Exit
 */
class Exit : Command {

    private val description: String = "завершить программу (без сохранения в файл)"
    private val type: ArgsType = ArgsType.NO_ARG

    /**
     * Action
     *
     * @param input
     * @return
     */
    override fun action(data: Map<String, Any>?): Result {

        val result = Result(true)

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