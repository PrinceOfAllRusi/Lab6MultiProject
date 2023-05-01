package tools

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationType
import tools.input.Input
import tools.input.InputFile
import java.time.LocalDateTime
import tools.file.ReadFile

class ConversionXMLtoObject: KoinComponent {

    private val orgs: MyCollection<Organization> by inject()

    fun convert(input: Input) {

        val reader: ReadFile = ReadFile()
        var str = StringBuilder()

        var env = input.getNextWord("Введите переменную окружения, содержащую путь к файлу с данными о коллекции\n")
        var inputNew: InputFile = InputFile(env)
        str.append(reader.read(inputNew))

        env = input.getNextWord("Введите переменную окружения, содержащую путь к файлу с данными об организациях\n")
        inputNew = InputFile(env)
        str.append(reader.read(inputNew))

        var s: String? = str.toString()
        str.clear()

        var org: Organization = Organization()

        if (s != null) {
            for ( char in s ) {
                str.append(char)

                if ( str.contains( ".*<dateOfCreation>.*</dateOfCreation>".toRegex()) ) {
                    var list: List<String> = str.toString().split("<dateOfCreation>")
                    list = list.get(1).split("</dateOfCreation>")

                    var time = LocalDateTime.parse(list.get(0))

                    orgs.setCreationDate(time)

                    str.clear()
                    continue
                }

                if ( str.contains( ".*<organization".toRegex()) ) {
                    org = Organization()
                    str.clear()
                    continue
                }

                if ( str.contains( "<organization".toRegex()) ) {
                    org = Organization()
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<id>\\d*</id>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<id>")
                    list = list.get(1).split("</id>")

                    org.setId(list.get(0).toInt())
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<name>.*</name>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<name>")
                    list = list.get(1).split("</name>")

                    org.setName(list.get(0))
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<x>\\d*</x>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<x>")
                    list = list.get(1).split("</x>")

                    org.setCoordinatesX(list.get(0).toInt())
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<y>\\d*</y>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<y>")
                    list = list.get(1).split("</y>")

                    org.setCoordinatesY(list.get(0).toLong())
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<creationDate>.*</creationDate>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<creationDate>")
                    list = list.get(1).split("</creationDate>")

                    var time = LocalDateTime.parse(list.get(0))

                    org.setCreationDate(time)
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<annualTurnover>.*</annualTurnover>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<annualTurnover>")
                    list = list.get(1).split("</annualTurnover>")

                    org.setAnnualTurnover(list.get(0).toDouble())
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<employeesCount>\\d*</employeesCount>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<employeesCount>")
                    list = list.get(1).split("</employeesCount>")

                    org.setEmployeesCount(list.get(0).toInt())
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<type>.*</type>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<type>")
                    list = list.get(1).split("</type>")

                    org.setType( OrganizationType.valueOf( list.get(0) ) )
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<street>.*</street>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<street>")
                    list = list.get(1).split("</street>")

                    org.setPostalAddressStreet(list.get(0))
                    str.clear()
                    continue
                }

                if ( str.contains( ".*<zipCode>.*</zipCode>".toRegex() ) )  {
                    var list: List<String> = str.toString().split("<zipCode>")
                    list = list.get(1).split("</zipCode>")

                    org.setPostalAddressZipCode(list.get(0))
                    str.clear()
                    continue
                }

                if ( str.contains( "</organization".toRegex() ) ) {
                    orgs.add(org)
                    str.clear()
                    continue
                }

            }
        }
    }
}