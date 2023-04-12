package organization

import java.time.LocalDateTime
import java.util.LinkedList

/**
 * My collection
 *
 * @param T
 * @constructor Create empty My collection
 */
class MyCollection<T>: LinkedList<T>() {
    private var creationDate : LocalDateTime? = LocalDateTime.now()

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
    fun setCreationDate(creationDate: LocalDateTime? ) {
        this.creationDate = creationDate
    }

}