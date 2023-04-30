package organization


class Address {
    private var street: String //Строка не может быть пустой, Поле не может быть null
    private var zipCode: String //Длина строки не должна быть больше 27, Поле не может быть null
    constructor() {
        this.street = ""
        this.zipCode = ""
    }
    constructor(street: String, zipCode: String) {
        this.street = street
        this.zipCode = zipCode
    }
    fun getStreet(): String = street
    fun setStreet(street: String) {
        this.street = street
    }
    fun getZipCode(): String = zipCode
    fun setZipCode(zipCode: String) {
        this.zipCode = zipCode
    }
    override fun toString(): String = street + ", " + zipCode
}
