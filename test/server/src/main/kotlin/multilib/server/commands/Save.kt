package allForCommands.commands

import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import serializ.TimeSerializer
import tools.file.WriteFile
import tools.result.Result
import tools.serializ.TimeDeserializer
import java.time.LocalDateTime

class Save: AbstractCommand(), KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private val description: String = "сохранить коллекцию в файл"

    override fun action(data: Map<String, String?>): Result {
        val writer = WriteFile()

        val mapper = XmlMapper()
        val module = SimpleModule()
        module.addSerializer(LocalDateTime::class.java, TimeSerializer())
        module.addDeserializer(LocalDateTime::class.java, TimeDeserializer())
        mapper.registerModule(module)

        val collection = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orgs)

        writer.write("D:/JavaP/lab6Ktln/Collection.txt", collection)

        val result = Result()
        result.setExit(true)

        return result
    }
    override fun getDescription(): String = description
}