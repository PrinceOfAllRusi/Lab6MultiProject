package tools

import tools.input.Input
import tools.result.Result
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException

class WriteFile {

    fun write(way: String, dataStr: String): Result? {
        val env = way
        val result = Result()

        val path = System.getenv(env)
        if (path == null) {
            result.setMessage("Данной переменной не существует\n")
            return result
        }

        val writter: BufferedWriter = BufferedWriter( FileWriter(path) )
        try {
            writter.write(dataStr)
        } catch (e: IOException) {
            result.setMessage("Отказано в доступе\n")
            return result
        } finally {
            if ( writter != null ) {
                writter.close()
            }
        }

        result.setMessage("Запись выполнена успешно")

        return result
    }
}

