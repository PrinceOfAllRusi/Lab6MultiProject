package tools

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationType
import tools.input.Input
import tools.input.InputFile
import java.lang.Integer.max


class CreateOrganization: KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private var counter: Int = 0

    fun create(data: Map<String, String?>, lastOrganization: Organization?): Organization {

        val org: Organization = Organization()

        val name: String
        val annualTurnover: Double
        val employeesCount: Int
        val x: Int
        val y: Long
        val type: OrganizationType
        val street: String
        val zipCode: String

        if (data["name"]!!.isBlank()) {
            name = lastOrganization!!.getName()!!
        } else name = data["name"]!!
        if (data["annualTurnover"]!!.isBlank()) {
            annualTurnover = lastOrganization!!.getAnnualTurnover()!!
        } else annualTurnover = data["annualTurnover"]!!.toDouble()
        if (data["employeesCount"]!!.isBlank()) {
            employeesCount = lastOrganization!!.getEmployeesCount()!!
        } else employeesCount = data["employeesCount"]!!.toInt()
        if (data["x"]!!.isBlank()) {
            x = lastOrganization!!.getCoordinatesX()!!.toInt()
        } else x = data["x"]!!.toInt()
        if (data["y"]!!.isBlank()){
            y = lastOrganization!!.getCoordinatesY()!!.toLong()
        } else y = data["y"]!!.toLong()
        if (data["type"]!!.isBlank()){
            type = lastOrganization!!.getType()!!
        } else type = OrganizationType.valueOf(data["type"]!!.uppercase())
        if (data["street"]!!.isBlank()){
            street = lastOrganization!!.getPostalAddressStreet()
        } else street = data["street"]!!
        if (data["zipCode"]!!.isBlank()){
            zipCode = lastOrganization!!.getPostalAddressZipCode()
        } else zipCode = data["zipCode"]!!


        org.setName(name)
        org.setAnnualTurnover(annualTurnover)
        org.setEmployeesCount(employeesCount)
        org.setCoordinatesX(x)
        org.setCoordinatesY(y)
        org.setType(type)
        org.setPostalAddressStreet(street)
        org.setPostalAddressZipCode(zipCode)

        if ( orgs.size != 0 ) {
            for ( organization in orgs ) {
                counter = max( counter, organization.getId()!!  )
            }
        }

        if (lastOrganization != null) {
            org.setId(lastOrganization.getId())
        } else {
            org.setId(counter)
            counter++
        }

        return org
    }
    fun setCounter(counter: Int) {
        this.counter = counter
    }
}
