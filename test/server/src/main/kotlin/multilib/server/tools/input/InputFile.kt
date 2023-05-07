package tools.input

import java.util.*
import java.util.LinkedList


class InputFile(s: String?) : Input {

    private var input: Scanner = Scanner(s)
    private var list: LinkedList<String> = LinkedList()
    private var index = 0

    override fun getNextWord(qw: String?): String {
        if (index < list.size) {
            return list[index++]
        }
        index = 0
        list = LinkedList()
        val words: List<String> = input.nextLine().split(" ")
        for (str in words) {
            if (!str.isBlank()) {
                list.add(str)
            }
        }
        if (list.size < 1) {
//            list.add("blank")
            list.add("")
        }
        return list[index++]
    }
    override fun outMsg(s: String?) {
        return
    }
    override fun close() {
        input.close()
    }
}
