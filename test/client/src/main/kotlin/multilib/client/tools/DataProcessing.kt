package tools

import multilib.utilities.commandsData.ClientCommandsData
import organization.OrganizationType
import tools.input.Input

class DataProcessing {

    fun setData(input: Input, data: MutableMap<String, Map<String, String>>): ClientCommandsData {
        val sendCommandsData = ClientCommandsData()

        if (data.size == 0) {
            return sendCommandsData
        }

        if (data.containsKey("value")) {
            val value = input.getNextWord(null)

            try {
                when (data["value"]!!["type"]) {
                    "String" -> {
                        sendCommandsData.getMapData().put("value", value)
                    }
                    "Int" -> {
                        value.toInt()
                        if (data["value"]!!.containsKey("min")) {
                            if (value.toInt() < data["value"]!!["min"]!!.toInt()) {
                                input.outMsg("Слишком маленькое значение\n")
                                return sendCommandsData
                            }
                        }
                        sendCommandsData.getMapData().put("value", value)

                        if (data.size == 1) {
                            return sendCommandsData
                        }
                    }
                }
            } catch (e: NullPointerException) {
                input.outMsg("Неверный тип данных\n")
                return sendCommandsData
            }
        }

        var value = ""
        val commandData = data.filterKeys { !it.contains("value") }
        var map: Map<String, String>

        for (key in commandData.keys) {
            map = commandData[key]!!
            while (true) {
                value = input.getNextWord(map["title"])
                if (value.isBlank()) {
                    if (map.containsKey("null")) {
                        sendCommandsData.getMapData().put(key, value)
                        break
                    } else {
                        input.outMsg("Поле не может быть пустым\n")
                        continue
                    }
                }
                try {
                    when (map["type"]) {
                        "Int" -> value.toInt()
                        "Long" -> value.toLong()
                        "Double" -> value.toDouble()
                        "OrganizationType" -> OrganizationType.valueOf(value.uppercase())
                    }
                } catch (e: NullPointerException) {
                    input.outMsg("Поле не может быть пустым\n")
                    continue
                } catch (e: IllegalArgumentException) {
                    input.outMsg("Неверный тип данных\n")
                    continue
                }
                if (map.containsKey("min")) {
                    if (value.toInt() < map["min"]!!.toInt()) {
                        input.outMsg("Слишком маленькое значение\n")
                        continue
                    }
                } else if (map.containsKey("max")) {
                    if (value.toInt() > map["max"]!!.toInt()) {
                        input.outMsg("Слишком большое значение\n")
                        continue
                    }
                } else if (map.containsKey("length")) {
                    if (value.length > map["length"]!!.toInt()) {
                        input.outMsg("Слишком большое значение\n")
                        continue
                    }
                }

                sendCommandsData.getMapData().put(key, value)
                break
            }
        }

        return sendCommandsData
    }
}