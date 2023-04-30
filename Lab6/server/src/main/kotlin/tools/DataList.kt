package tools
class DataList {
    private var scriptList: ArrayList<String>
    private var addressList: ArrayList<String>
    constructor() {
        scriptList = ArrayList()
        addressList = ArrayList()
    }
    constructor(scriptList: ArrayList<String>, addressList: ArrayList<String>) {
        this.scriptList = scriptList
        this.addressList = addressList
    }
    fun getScriptList() = scriptList
    fun getAddressList() = addressList
}