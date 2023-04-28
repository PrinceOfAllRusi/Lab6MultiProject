package tools

import CommandsData.ClientCommandsData
import organization.OrganizationType
import tools.input.Input

class DataProcessing {

    fun setData(input: Input, data: Map<String, Map<String, String>>): ClientCommandsData? {
        val sendCommandsData = ClientCommandsData()

        if (data.size == 0) {
            return sendCommandsData
        }

        if (data.containsKey("value")) {
            val value = input.getNextWord("")

            try {
                when (data["value"]!!["type"]) {
                    "String" -> {
                        sendCommandsData.getMapData().put("value", value)
                    }
                    "Int" -> {
                        value.toInt()
                        if (data["value"]!!.containsKey("min")) {
                            if (value.toInt() < data["value"]!!["min"]!!.toInt()) {
                                input.outMsg("Неверные данные")
                                return null
                            }
                        }
                        sendCommandsData.getMapData().put("value", value)
                    }

                }
            } catch (e: NullPointerException) {
                input.outMsg("Неверный тип данных")
                return null
            }
        }

        var value = ""

        for (key in data.keys) {
            while (true) {
                value = input.getNextWord(data[key]!!["title"])
                if (value.isBlank() && !data[key]!!.containsKey("null")) {
                    input.outMsg("Неверный тип данных")
                    continue
                }
                try {
                    when (data[key]!!["type"]) {
                        "Int" -> value.toInt()
                        "Long" -> value.toLong()
                        "Double" -> value.toDouble()
                        "OrganizationType" -> OrganizationType.valueOf(value.uppercase())
                    }
                } catch (e: NullPointerException) {
                    input.outMsg("Неверный тип данных")
                    continue
                } catch (e: IllegalArgumentException) {
                    input.outMsg("Неверный тип данных")
                    continue
                }
                if (data[key]!!.containsKey("min")) {
                    if (value.toInt() < data[key]!!["min"]!!.toInt()) {
                        input.outMsg("Слишком маленькое значение")
                        continue
                    }
                } else if (data[key]!!.containsKey("max")) {
                    if (value.toInt() > data[key]!!["max"]!!.toInt()) {
                        input.outMsg("Слишком большое значение")
                        continue
                    }
                } else if (data[key]!!.containsKey("length")) {
                    if (value.length > data[key]!!["length"]!!.toInt()) {
                        input.outMsg("Слишком большое значение")
                        continue
                    }
                }

                sendCommandsData.getMapData().put(key, value)

            }






        }


        return sendCommandsData
    }
}