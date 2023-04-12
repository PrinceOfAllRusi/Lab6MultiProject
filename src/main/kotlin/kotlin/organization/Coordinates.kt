package organization

/**
 * Coordinates
 *
 * @constructor Create empty Coordinates
 */
class Coordinates {
    private var x: Int //Значение поля должно быть больше -312, Поле не может быть null
    private var y: Long //Максимальное значение поля: 212
    constructor(x: Int, y: Long) {
        this.x = x
        this.y = y
    }

    /**
     * Get x
     *
     * @return
     */
    fun getX(): Int = x

    /**
     * Get y
     *
     * @return
     */
    fun getY(): Long = y

    /**
     * Set x
     *
     * @param x
     */
    fun setX(x: Int) {
        this.x = x
    }

    /**
     * Set y
     *
     * @param y
     */
    fun setY(y: Long) {
        this.y = y
    }

    /**
     * To string
     *
     * @return
     */
    override fun toString(): String = "(" + x + ";" + y + ")"
}