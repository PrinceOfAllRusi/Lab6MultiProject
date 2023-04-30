package modul

import allForCommands.commands.*
import org.koin.dsl.module
import organization.MyCollection
import organization.Organization
import tools.CommandsList
import tools.CreateOrganization
import kotlin.collections.ArrayList
import tools.DataList

object SingletonObject {

    val mod = module {
        single<MyCollection<Organization>> { MyCollection() }
        single<ArrayList<String>> { ArrayList() }
        single<CreateOrganization> { CreateOrganization() }
        single<DataList> { DataList() }

        val listCommand: Map<String, AbstractCommand> = mapOf("help" to Help(),
            "info" to Info(), "show" to Show(), "add" to Add(),
            "update" to Update(), "remove_by_id" to RemoveById(),
            "clear" to Clear(), "execute_script" to ExecuteScript(),
            "exit" to Save(), "insert_at" to InsertAt(),
            "remove_at" to RemoveAt(), "remove_lower" to RemoveLower(),
            "remove_all_by_employees_count" to RemoveAllByEmployeesCount(),
            "count_greater_than_annual_turnover" to CountGreaterThanAnnualTurnover(),
            "filter_starts_with_name" to FilterStartsWithName())

        val CommandsList: CommandsList = CommandsList( listCommand )
        single<CommandsList> { CommandsList }
    }
}