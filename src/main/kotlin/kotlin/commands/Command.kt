package commands

import commands.types.ArgsType
import tools.result.Result

/**
 * Command
 *
 * @constructor Create empty Command
 */
interface Command {
    /**
     * Action
     *
     * @param input
     * @return
     */
    fun action(map: Map<String, Any>?): Result?

    /**
     * Get description
     *
     * @return
     */
    fun getDescription(): String

    /**
     * Get type
     *
     * @return
     */
    fun getType(): ArgsType
}