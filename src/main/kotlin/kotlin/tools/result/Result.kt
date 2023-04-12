package tools.result

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization

/**
 * Result
 *
 * @constructor @param exit
 */
class Result: KoinComponent {
    private val orgs: MyCollection<Organization> by inject()
    private var exit: Boolean?
    private var message: String = ""

    constructor(exit: Boolean?) {
        this.exit = exit
    }

    /**
     * Get exit
     *
     * @return
     */
    fun getExit(): Boolean? {
        return exit
    }

    /**
     * Get message
     *
     * @return
     */
    fun getMessage(): String = message

    /**
     * Set message
     *
     * @param message
     */
    fun setMessage(message: String) {
        this.message = message
    }
}