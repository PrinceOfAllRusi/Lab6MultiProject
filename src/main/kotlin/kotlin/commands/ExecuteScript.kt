package commands

import commands.types.ArgsType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.*
import tools.input.Input
import tools.input.InputFile
import tools.result.Result
import kotlin.collections.ArrayList

/**
 * Execute script
 *
 * @constructor Create empty Execute script
 */
class ExecuteScript: Command, KoinComponent {

    private val absoluteWay: ArrayList<String> by inject()
    private val description: String = "считать и исполнить скрипт из указанного файла"
    private val type: ArgsType = ArgsType.SCRIPT

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

        val s = data.get("script").toString()

        val input = InputFile(s)

        val commandProcessor = CommandProcessor()
            commandProcessor.process(input)

        input.close()

        absoluteWay.removeLast()

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