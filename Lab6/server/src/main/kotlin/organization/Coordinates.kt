package organization


class Coordinates {
    private var x: Int //Значение поля должно быть больше -312, Поле не может быть null
    private var y: Long //Максимальное значение поля: 212
    constructor() {
        this.x = 0
        this.y = 0
    }
    constructor(x: Int, y: Long) {
        this.x = x
        this.y = y
    }

    fun getX(): Int = x
    fun getY(): Long = y
    fun setX(x: Int) {
        this.x = x
    }
    fun setY(y: Long) {
        this.y = y
    }
    override fun toString(): String = "(" + x + ";" + y + ")"
}