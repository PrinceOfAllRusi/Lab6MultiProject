package organization

/**
 * Address
 *
 * @constructor Create empty Address
 */
class Address {
    private var street: String //Строка не может быть пустой, Поле не может быть null
    private var zipCode: String //Длина строки не должна быть больше 27, Поле не может быть null

    constructor(street: String, zipCode: String) {
        this.street = street
        this.zipCode = zipCode
    }

    /**
     * Get street
     *
     * @return
     */
    fun getStreet(): String = street

    /**
     * Set street
     *
     * @param street
     */
    fun setStreet(street: String) {
        this.street = street
    }

    /**
     * Get zip code
     *
     * @return
     */
    fun getZipCode(): String = zipCode

    /**
     * Set zip code
     *
     * @param zipCode
     */
    fun setZipCode(zipCode: String) {
        this.zipCode = zipCode
    }

    /**
     * To string
     *
     * @return
     */
    override fun toString(): String = street + ", " + zipCode
}
