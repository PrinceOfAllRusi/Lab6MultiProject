package organization

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Organization
 *
 * @constructor Create empty Organization
 */
class Organization {
    private var id: Int? = null //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private var name: String? = null //Поле не может быть null, Строка не может быть пустой
    private var coordinates: Coordinates? = Coordinates(0, 0L) //Поле не может быть null
    private var creationDate: LocalDateTime? = LocalDateTime.now() //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private var annualTurnover: Double? = null //Поле может быть null, Значение поля должно быть больше 0
    private var employeesCount: Int? = null //Значение поля должно быть больше 0
    private var type: OrganizationType? = null //Поле может быть null
    private var postalAddress: Address? = Address("", "")//Поле может быть null


    /**
     * To string
     *
     * @return
     */
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

    /**
     * Get employees count
     *
     * @return
     */
    fun getEmployeesCount(): Int? = employeesCount

    /**
     * Set employees count
     *
     * @param employeesCount
     */
    fun setEmployeesCount(employeesCount: Int) {
        this.employeesCount = employeesCount
    }

    /**
     * Get coordinates
     *
     * @return
     */
    fun getCoordinates(): Coordinates? = coordinates

    /**
     * Set coordinates
     *
     * @param coordinates
     */
    fun setCoordinates(coordinates: Coordinates) {
        this.coordinates = coordinates
    }

    /**
     * Get coordinates x
     *
     * @return
     */
    fun getCoordinatesX(): String? {
        return coordinates!!.getX().toString()
    }

    /**
     * Get coordinates y
     *
     * @return
     */
    fun getCoordinatesY(): String? {
        return coordinates!!.getY().toString()
    }

    /**
     * Set coordinates x
     *
     * @param x
     */
    fun setCoordinatesX(x: Int) {
        coordinates!!.setX(x)
    }

    /**
     * Set coordinates y
     *
     * @param y
     */
    fun setCoordinatesY(y: Long) {
        coordinates!!.setY(y)
    }

    /**
     * Get postal address
     *
     * @return
     */
    fun getPostalAddress(): Address? = postalAddress

    /**
     * Set postal address
     *
     * @param postalAddress
     */
    fun setPostalAddress(postalAddress: Address?) {
        this.postalAddress = postalAddress
    }

    /**
     * Get postal address street
     *
     * @return
     */
    fun getPostalAddressStreet(): String = postalAddress!!.getStreet()

    /**
     * Get postal address zip code
     *
     * @return
     */
    fun getPostalAddressZipCode(): String = postalAddress!!.getZipCode()

    /**
     * Set postal address street
     *
     * @param street
     */
    fun setPostalAddressStreet(street: String) {
        postalAddress!!.setStreet(street)
    }

    /**
     * Set postal address zip code
     *
     * @param zipCode
     */
    fun setPostalAddressZipCode(zipCode: String) {
        postalAddress!!.setZipCode(zipCode)
    }

    /**
     * Get type
     *
     * @return
     */
    fun getType(): OrganizationType? = type

    /**
     * Set type
     *
     * @param type
     */
    fun setType(type: OrganizationType) {
        this.type = type
    }

    /**
     * Get annual turnover
     *
     * @return
     */
    fun getAnnualTurnover(): Double? = annualTurnover

    /**
     * Set annual turnover
     *
     * @param annualTurnover
     */
    fun setAnnualTurnover(annualTurnover: Double) {
        this.annualTurnover = annualTurnover
    }

    /**
     * Get name
     *
     * @return
     */
    fun getName(): String? = name

    /**
     * Set name
     *
     * @param name
     */
    fun setName(name: String) {
        this.name = name
    }

    /**
     * Get id
     *
     * @return
     */
    fun getId(): Int? = id

    /**
     * Set id
     *
     * @param id
     */
    fun setId(id: Int?) {
        this.id = id
    }

    /**
     * Get creation date
     *
     * @return
     */
    fun getCreationDate(): LocalDateTime? = creationDate

    /**
     * Set creation date
     *
     * @param creationDate
     */
    fun setCreationDate(creationDate: LocalDateTime?) {
        this.creationDate = creationDate
    }
}