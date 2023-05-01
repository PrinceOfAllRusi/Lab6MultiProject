package multilib.client.commandsData

class ServerCommandsData {
    private var mapCommands: Map<String, MutableMap<String, Map<String, String>>>
    constructor() {
        mapCommands = mapOf()
    }
    fun getMapCommands() = mapCommands
    fun setMapCommands(mapCommands: Map<String, MutableMap<String, Map<String, String>>>){
        this.mapCommands = mapCommands
    }

}