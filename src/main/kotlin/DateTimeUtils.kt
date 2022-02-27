import java.time.LocalTime

object DateTimeUtils {
    private fun String.isMinutesInvalid(): Boolean {
        return !(this == "*" || this.toInt() in 0..59)
    }

    private fun String.isHourInvalid(): Boolean {
        return !(this == "*" || this.toInt() in 0..23)
    }

    fun String.hasNotPassed(minute: Int): Boolean {
        return minute > this.toInt()
    }

    fun isInputValid(minutes: String, hour: String, index: Int): Boolean {
        return when {
            minutes.isMinutesInvalid() -> {
                println("value for minutes is invalid for index $index in input, must be * or between 0 to 59")
                false
            }
            hour.isHourInvalid() -> {
                println("value for hour is invalid for index $index in input, must be * or between 0 to 23")
                false
            }
            else -> true
        }
    }

    fun isNextRunTodayOrTomorrow(hour: Int, minutes: Int, simulatedCurrentTimeFormatted: LocalTime): String {
        val commandTime = LocalTime.of(hour, minutes)
        val isAfter = commandTime.isAfter(simulatedCurrentTimeFormatted)
        return if (isAfter) "today" else "tomorrow"
    }
}