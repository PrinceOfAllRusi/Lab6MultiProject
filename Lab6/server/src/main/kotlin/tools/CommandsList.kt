package tools

import allForCommands.commands.AbstractCommand

class CommandsList {
    private var listCommands: Map<String, AbstractCommand> = mapOf()
    constructor(listCommands: Map<String, AbstractCommand>) {
        this.listCommands = listCommands
    }
    fun containsCommand(command: String): Boolean = listCommands.containsKey(command)
    fun getCommand(command: String): AbstractCommand? = listCommands[command]
    fun getDescription(): String {
        val s = StringBuilder()
        for (command in listCommands.keys) {
            s.append(command)
            s.append(" : ")
            s.append(listCommands[command]!!.getDescription())
            s.append("\n")
        }
        return s.toString()
    }
}