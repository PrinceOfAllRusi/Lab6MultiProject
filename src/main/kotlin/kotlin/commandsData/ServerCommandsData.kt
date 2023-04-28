package transmittedData

class ServerCommandsData {
    private var mapCommands: Map<String, Map<String, Map<String, String>>>
    constructor() {
        mapCommands = mapOf()
    }
    fun getMapCommands() = mapCommands
    fun setMapCommands(mapCommands: Map<String, Map<String, Map<String, String>>>){
        this.mapCommands = mapCommands
    }

}