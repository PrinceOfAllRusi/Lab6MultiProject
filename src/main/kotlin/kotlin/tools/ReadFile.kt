package tools

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import tools.input.Input
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader


/**
 * Read file
 *
 * @constructor Create empty Read file
 */
class ReadFile: KoinComponent {

    private val absoluteWay: ArrayList<String> by inject()

    /**
     * Read
     *
     * @param input
     * @return
     */
    fun read(input: Input): String? {

        val env = input.getNextWord("Введите переменную окружения, содержащую путь к файлу\n")
        val s = StringBuilder()

        for(i in absoluteWay) {
            if (i == env) {
                return null
            }
        }
        absoluteWay.add(env)


        val path = System.getenv(env)
        if (path == null) {
            input.outMsg("Данной переменной не существует\n")
            return null
        }
        var reader: InputStreamReader? = null

        try {
            reader = InputStreamReader(FileInputStream(path), "UTF8")
            var i = -1

            while ((reader.read().also { i = it }) != -1) {
                s.append(i.toChar())
            }

            s.append("\nexit")

        } catch (e: FileNotFoundException) {
            input.outMsg("Данного файла не существует\n")
        } catch (e: IOException) {
            input.outMsg("Некорректный ввод данных\n")
        } finally {
            if (reader != null) {
                reader.close()
            }
        }

        return s.toString()
    }
}