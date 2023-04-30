package tools.input

interface Input {
    fun getNextWord(qw: String?): String
    fun outMsg(s: String?)
    fun close()
}