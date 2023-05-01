package organization

import java.time.LocalDateTime
import java.util.LinkedList

class MyCollection<T>: LinkedList<T>() {
    private var creationDate : LocalDateTime? = LocalDateTime.now()
    fun getCreationDate(): LocalDateTime? = creationDate
    fun setCreationDate(creationDate: LocalDateTime? ) {
        this.creationDate = creationDate
    }

}