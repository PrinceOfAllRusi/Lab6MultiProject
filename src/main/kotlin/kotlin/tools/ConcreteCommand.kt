package tools

import organization.Organization

class ConcreteCommand {
    private var name: String = ""
    private var index: Int = -1
    private var counter: Int = 0
    private var organization: Organization = Organization()
    private var script: String = ""
    private var message: String = ""

    fun getName(): String = name
    fun setName(name: String) {
        this.name = name
    }
    fun getIndex(): Int = index
    fun setIndex(index: Int) {
        this.index = index
    }
    fun getOrganization(): Organization = organization
    fun setOrganization(org: Organization) {
        this.organization = org
    }
    fun getScript(): String = script
    fun setScript(script: String) {
        this.script = script
    }
    fun getMessage(): String = message
    fun setMessage(message: String) {
        this.message = message
    }

}