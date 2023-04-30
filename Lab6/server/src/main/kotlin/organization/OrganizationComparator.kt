package organization

class OrganizationComparator : Comparator<Organization?> {
    override fun compare(a: Organization?, b: Organization?): Int {
        if (a!!.getPostalAddressStreet().length == b!!.getPostalAddressStreet().length) {
            return 0
        }
        return if ( a.getPostalAddressStreet().length > b.getPostalAddressStreet().length ) {
            1
        } else {
            -1
        }
    }
}