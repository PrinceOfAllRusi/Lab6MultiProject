package multilib.client.tools

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ScriptProcessor: KoinComponent {
    private val absoluteWay: ArrayList<String> by inject()
    private val string: StringBuilder by inject()
    fun action(env: String): String {
        absoluteWay.add(env)
        val way = System.getenv(env)
        val file = File(way)
        val fr = FileReader(file)
        val reader = BufferedReader(fr)

        var line = reader.readLine()
        while (line != null) {
            if (line == "execute_script") {
                line = reader.readLine()
                if (absoluteWay.contains(line)) {
                    line = reader.readLine()
                    continue
                }
                absoluteWay.add(line)
                action(line)
                line = reader.readLine()
                absoluteWay.removeLast()
                continue
            }
            if (line == "exit") break

            string.append(line + " ")
            line = reader.readLine()
        }
        absoluteWay.removeLast()
        
        return string.toString()
    }
    fun clearScript() {
        string.clear()
    }
}