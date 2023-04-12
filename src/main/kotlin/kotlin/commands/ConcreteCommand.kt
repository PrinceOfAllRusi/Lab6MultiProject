package kotlin.commands

import organization.Organization

class ConcreteCommand {
    var name: String = ""
    var id: Int? = 0
    var organization: Organization? = null

    fun getName() = name

    fun setName(name: String) {
        this.name = name
    }

    fun getId(): Int? = id

    fun setId(id: Int) {
        this.id = id
    }

    fun getOrganization(): Organization? = organization

    fun setOrganization(org: Organization) {
        this.organization = org
    }

}