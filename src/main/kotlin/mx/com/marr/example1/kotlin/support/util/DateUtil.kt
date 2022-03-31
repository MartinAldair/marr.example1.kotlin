package mx.com.marr.example1.kotlin.support.util

import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*

@Component("dateUtil")
class DateUtil: Serializable {

    private val serialVersionUID = 1L

    val LOCALE_MX = Locale("es", "MX")

    val TIMEZONE_MX: TimeZone = TimeZone.getTimeZone("America/Mexico_City")

    fun getCurrentCalendar(): Calendar {
        return GregorianCalendar.getInstance(TIMEZONE_MX, LOCALE_MX)
    }

    fun getCurrentDateLocaleMX(): Date {
        return getCurrentCalendar().time
    }

}