package multilib.client.modul

import org.koin.dsl.module
import kotlin.collections.ArrayList

object SingletonObject {

    val mod = module {
        single<ArrayList<String>> { ArrayList() }
        single<StringBuilder> { StringBuilder() }
    }
}