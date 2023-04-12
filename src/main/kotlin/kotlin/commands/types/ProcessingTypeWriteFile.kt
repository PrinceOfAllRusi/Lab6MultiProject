package commands.types

import com.thoughtworks.xstream.XStream
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import tools.WriteFile
import tools.input.Input

class ProcessingTypeWriteFile: ProcessingType, KoinComponent {
    private val orgs: MyCollection<Organization> by inject()
    override fun processing(input: Input): Map<String, Any>? {
        val xml: XStream = XStream()
        val orgsXML: StringBuilder = StringBuilder()
        var i: Int = 0
        var time: String
        var list: List<String>
        var creationDate: StringBuilder = StringBuilder()

        for ( org in orgs ) {
            xml.alias("organization_" + i, org.javaClass)
            orgsXML.append( xml.toXML(org) + "\n")
            ++i
        }

        val collectionXML: StringBuilder = StringBuilder()
        collectionXML.append("<Collection>\n")
        collectionXML.append("\t<dateOfCreation>" + orgs.getCreationDate() + "</dateOfCreation>\n")
        collectionXML.append("</Collection>\n")

        val writer: WriteFile = WriteFile()
        val wayCollection = input.getNextWord("Введите переменную окружения, содержащую путь к файлу, для записи данных коллекции\n")
        val wayOrgs = input.getNextWord("Введите переменную окружения, содержащую путь к файлу, для записи содержимого коллекции коллекции\n")

        val map: Map<String, Any> = mapOf("wayCollection" to wayCollection, "collectionXML" to collectionXML,
            "wayOrgs" to wayOrgs, "orgsXML" to orgsXML)

        return map
    }
}