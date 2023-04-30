package CommandsData

class ClientCommandsData {
    private var name: String
    private var mapData: MutableMap<String, String>
    constructor(){
        name = ""
        mapData = mutableMapOf()
    }
    constructor (name: String, mapCommands: MutableMap<String, String>) {
        this.name = name
        this.mapData = mapCommands
    }
    fun getName() = name
    fun setName(name: String) {
        this.name = name
    }
    fun getMapData() = mapData
    fun setMapData(mapCommands: MutableMap<String, String>) {
        this.mapData = mapCommands
    }
}