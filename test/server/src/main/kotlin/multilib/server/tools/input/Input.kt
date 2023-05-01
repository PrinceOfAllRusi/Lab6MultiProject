package tools.input

/**
 * Input
 *
 * @constructor Create empty Input
 */
interface Input {

    /**
     * Get next word
     *
     * @param qw
     * @return
     */
    fun getNextWord(qw: String?): String

    /**
     * Out msg
     *
     * @param s
     */
    fun outMsg(s: String?)

    /**
     * Close
     *
     */
    fun close()
}