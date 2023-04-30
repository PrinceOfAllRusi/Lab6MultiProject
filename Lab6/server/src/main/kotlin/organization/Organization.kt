package organization

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Organization {
    private var id: Int? = null //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private var name: String? = null //Поле не может быть null, Строка не может быть пустой
    private var coordinates: Coordinates? = Coordinates(0, 0L) //Поле не может быть null
    private var creationDate: LocalDateTime? = LocalDateTime.now() //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private var annualTurnover: Double? = null //Поле может быть null, Значение поля должно быть больше 0
    private var employeesCount: Int? = null //Значение поля должно быть больше 0
    private var type: OrganizationType? = null //Поле может быть null
    private var postalAddress: Address? = Address("", "")//Поле может быть null


    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val s = StringBuilder()
        s.append("Id организации: ").append(id)
        s.append("\nИмя организации: ").append(name)
        s.append("\nКоординаты организации: ").append(coordinates.toString())
        s.append("\nВремя создания организации: ").append(creationDate!!.format(formatter))
        s.append("\nГодовой оборот организации: ").append(annualTurnover)
        s.append("\nКоличество сотрудников в организации: ").append(employeesCount)
        s.append("\nТип организации: ").append(type)
        s.append("\nНазвание и код улицы, на которой расположена организация: ").append(postalAddress.toString()).append("\n")
        return s.toString()
    }
    fun getEmployeesCount(): Int? = employeesCount
    fun setEmployeesCount(employeesCount: Int) {
        this.employeesCount = employeesCount
    }
    fun getCoordinates(): Coordinates? = coordinates
    fun setCoordinates(coordinates: Coordinates) {
        this.coordinates = coordinates
    }
    fun getCoordinatesX(): String? {
        return coordinates!!.getX().toString()
    }
    fun getCoordinatesY(): String? {
        return coordinates!!.getY().toString()
    }
    fun setCoordinatesX(x: Int) {
        coordinates!!.setX(x)
    }
    fun setCoordinatesY(y: Long) {
        coordinates!!.setY(y)
    }
    fun getPostalAddress(): Address? = postalAddress
    fun setPostalAddress(postalAddress: Address?) {
        this.postalAddress = postalAddress
    }
    fun getPostalAddressStreet(): String = postalAddress!!.getStreet()
    fun getPostalAddressZipCode(): String = postalAddress!!.getZipCode()
    fun setPostalAddressStreet(street: String) {
        postalAddress!!.setStreet(street)
    }
    fun setPostalAddressZipCode(zipCode: String) {
        postalAddress!!.setZipCode(zipCode)
    }
    fun getType(): OrganizationType? = type
    fun setType(type: OrganizationType?) {
        this.type = type
    }
    fun getAnnualTurnover(): Double? = annualTurnover
    fun setAnnualTurnover(annualTurnover: Double) {
        this.annualTurnover = annualTurnover
    }
    fun getName(): String? = name
    fun setName(name: String) {
        this.name = name
    }
    fun getId(): Int? = id
    fun setId(id: Int?) {
        this.id = id
    }
    fun getCreationDate(): LocalDateTime? = creationDate
    fun setCreationDate(creationDate: LocalDateTime?) {
        this.creationDate = creationDate
    }
}