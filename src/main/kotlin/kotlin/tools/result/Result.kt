package tools.result

class Result {
    private var exit: Boolean?
    private var message: String

    constructor() {
        this.exit = false
        this.message = ""
    }
    fun getExit(): Boolean? {
        return exit
    }
    fun setExit(exit: Boolean?) {
        this.exit = exit
    }

    fun getMessage(): String = message

    fun setMessage(message: String) {
        this.message = message
    }
}