package tools

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationType
import tools.input.Input
import tools.input.InputFile
import java.lang.Integer.max


/**
 * Create organization
 *
 * @constructor Create empty Create organization
 */
class CreateOrganization: KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private var counter: Int = 0

    /**
     * Create
     *
     * @param input
     * @param org
     * @return
     */
    fun create(input: Input, org: Organization? ): Organization? {

        var organization: Organization = Organization()
        organization.setId(-1)

        if ( org != null ) {
            organization = org
        }

        var newName: String? = ""
        var name: String? = ""
        if ( org != null ) {
            name = organization.getName()
        }
        while ( true ) {
            newName = input.getNextWord("Введите название вашей организации\n")

            if ( newName.isBlank() ) {
                if ( org != null ) {
                    organization.setName(name!!)
                    break
                }
                input.outMsg("Неподходящие данные\n")
                if ( input.javaClass == InputFile("").javaClass ) {
                    return null
                }
            } else {
                organization.setName(newName!!)
                break
            }
        }

        var newAnnualTurnover: String = ""
        var annualTurnover: String = ""
        if ( org != null ) {
            annualTurnover = organization.getAnnualTurnover().toString()
        }
        while ( true ) {
            try {
                newAnnualTurnover = input.getNextWord( "Введите годовой оборот вашей организации\n" )

                if ( newAnnualTurnover.isBlank() || newAnnualTurnover.toInt() <= 0 ) {
                    if ( org != null ) {
                        organization.setAnnualTurnover( annualTurnover!!.toDouble() )
                        break
                    }
                    input.outMsg( "Неподходящие данные\n")
                    if ( input.javaClass == InputFile("").javaClass ) {
                        return null
                    }
                }
                else {
                    organization.setAnnualTurnover( newAnnualTurnover!!.toDouble() )
                    break
                }
            } catch ( e: NumberFormatException ) {
                input.outMsg( "Неверный тип данных\n" )
                if ( input.javaClass == InputFile("").javaClass ) {
                    return null
                }
            }
        }

        var newEmployeesCount: String = ""
        var employeesCount: String = ""
        if ( org != null ) {
            employeesCount = organization.getEmployeesCount().toString()
        }
        while ( true ) {
            try {
                newEmployeesCount = input.getNextWord( "Введите количество сотрудников в вашей организации\n" )

                if ( newEmployeesCount.isBlank() || newEmployeesCount.toInt() <= 0 ) {
                    if ( org != null ) {
                        organization.setEmployeesCount( employeesCount!!.toInt() )
                        break
                    }
                    input.outMsg( "Неподходящие данные\n" )
                    if ( input.javaClass == InputFile("").javaClass ) {
                        return null
                    }
                }
                else {
                    organization.setEmployeesCount(newEmployeesCount!!.toInt())
                    break
                }
            } catch (e: NumberFormatException) {
                input.outMsg( "Неверный тип данных\n" )
                if ( input.javaClass == InputFile("").javaClass ) {
                    return null
                }
            }
        }

        var newX: String? = ""
        var newY: String? = ""
        var x: String? = ""
        var y: String? = ""
        if ( org != null ) {
            x = organization.getCoordinatesX()
            y = organization.getCoordinatesY()
        }
        while ( true ) {
            try {
                newX = input.getNextWord( "Введите координаты вашей организации по оси X\n" )

                if ( newX.isBlank() || newX.toInt() <= -312 ) {
                    if ( org != null ) {
                        organization.setCoordinatesX(x!!.toInt())
                        break
                    }
                    input.outMsg( "Неподходящие данные\n" )
                    if ( input.javaClass == InputFile("").javaClass ) {
                        return null
                    }
                }
                else {
                    organization.setCoordinatesX(newX!!.toInt())
                    break
                }
            } catch ( e: NumberFormatException ) {
                input.outMsg( "Неверный тип данных\n" )
                if ( input.javaClass == InputFile("").javaClass ) {
                    return null
                }
            }
        }
        while ( true ) {
            try {
                newY = input.getNextWord( "Введите координаты вашей организации по оси Y\n" )

                if ( newY.isBlank() || newY.toInt() > 212 ) {
                    if ( org != null ) {
                        organization.setCoordinatesY(y!!.toLong())
                        break
                    }
                    input.outMsg( "Неподходящие данные\n" )
                    if ( input.javaClass == InputFile("").javaClass ) {
                        return null
                    }
                }
                else {
                    organization.setCoordinatesY(newY!!.toLong())
                    break
                }
            } catch ( e: NumberFormatException ) {
                input.outMsg( "Неверный тип данных\n" )
                if ( input.javaClass == InputFile("").javaClass ) {
                    return null
                }
            }
        }

        val typeStr = StringBuilder()
        typeStr.append( "Выбирете тип вашей организации из данных вариантов\n" )
        val organizationType = OrganizationType.values()
        for ( i in organizationType.indices ) {
            typeStr.append( organizationType[i].toString() + "\n" )
        }

        var newTypeOrganization: String = ""
        var typeOrganization: String = ""
        if ( org != null ) {
            typeOrganization = organization.getType()!!.toString()
        }
        while ( true ) {
            try {
                newTypeOrganization = input.getNextWord( typeStr.toString() )
                if ( newTypeOrganization.isBlank() ) {
                    if ( org != null ) {
                        organization.setType( OrganizationType.valueOf( typeOrganization.uppercase() ) )
                        break
                    }
                    input.outMsg( "Неподходящие данные\n" )
                    if ( input.javaClass == InputFile("").javaClass ) {
                        return null
                    }
                } else {
                    organization.setType( OrganizationType.valueOf( newTypeOrganization.uppercase() ) )
                    break
                }
            } catch ( e: IllegalArgumentException ) {
                input.outMsg( "Неподходящие данные\n" )
                if ( input.javaClass == InputFile("").javaClass ) {
                    return null
                }
            }
        }

        var newStreet: String = ""
        var newCode: String = ""
        var street: String = ""
        var code: String = ""
        if ( org != null ) {
            street = organization.getPostalAddressStreet()
            code = organization.getPostalAddressZipCode()
        }
        while ( true ) {
            newStreet = input.getNextWord( "Введите название улицы, на которой расположена ваша организация\n" )
            if ( newStreet.isBlank() ) {
                if ( org != null ) {
                    organization.setPostalAddressStreet(street)
                    break
                }
                input.outMsg( "Неподходящие данные\n" )
                if ( input.javaClass == InputFile("").javaClass ) {
                    return null
                }
            }
            else {
                organization.setPostalAddressStreet(newStreet)
                break
            }
        }
        while ( true ) {
            newCode = input.getNextWord( "Введите код улицы, на которой расположена ваша организация\n" )
            if ( newCode.isBlank() || code.length > 27 ) {
                if ( org != null ) {
                    organization.setPostalAddressZipCode(code)
                    break
                }
                input.outMsg( "Неподходящие данные\n" )
                if ( input.javaClass == InputFile("").javaClass ) {
                    return null
                }
            }
            else {
                organization.setPostalAddressZipCode(newCode)
                break
            }
        }

        if ( orgs.size != 0 ) {
            for ( org in orgs ) {
                counter = max( counter, org.getId()!!  )
            }
            counter++
        }

        organization.setId(counter)

        counter++


        return organization
    }
}
