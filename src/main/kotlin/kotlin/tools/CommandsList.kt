package tools

import commands.Command
import commands.types.ArgsType
import commands.types.ProcessingType

class CommandsList (var mapCommands: Map<String, Command>, val mapType: Map<ArgsType, ProcessingType>) {
    fun containsCommand(command: String): Boolean = mapCommands.containsKey(command)
    fun getCommand(command: String): Command? = mapCommands[command]
    fun getType(type: ArgsType): ProcessingType? = mapType[type]
    fun getDescription(): String = mapCommands.map { "${it.key} : ${it.value.getDescription()}" }.joinToString(separator = "\n")

}