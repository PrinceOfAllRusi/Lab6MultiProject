package organization

/**
 * Organization comparator
 *
 * @constructor Create empty Organization comparator
 */
class OrganizationComparator : Comparator<Organization?> {
    /**
     * Compare
     *
     * @param a
     * @param b
     * @return
     */
    override fun compare(a: Organization?, b: Organization?): Int {
        if (a!!.getEmployeesCount() === b!!.getEmployeesCount()) {
            return 0
        }
        return if ( a!!.getEmployeesCount()!! > b!!.getEmployeesCount()!! ) {
            1
        } else {
            -1
        }
    }
}