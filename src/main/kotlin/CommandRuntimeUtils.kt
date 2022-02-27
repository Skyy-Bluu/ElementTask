import DateTimeUtils.hasNotPassed
import DateTimeUtils.isNextRunTodayOrTomorrow
import java.time.LocalTime

object CommandRuntimeUtils {
    fun printCommandRuntTime(
        minutes: String,
        hour: String,
        simulatedCurrentTimeFormatted: LocalTime,
        command: String
    ) {
        when {
            minutes == "*" && hour == "*" -> runStartingFromSimulatedCurrentTime(
                simulatedCurrentTimeFormatted,
                command
            )
            hour == "*" -> runStartingOnTheNthHour(
                simulatedCurrentTimeFormatted,
                minutes.toInt(),
                command,
                minutes.hasNotPassed(simulatedCurrentTimeFormatted.minute)
            )
            minutes == "*" -> runStartingAtTheNthMinute(
                hour,
                command,
                isNextRunTodayOrTomorrow(hour.toInt(), 0, simulatedCurrentTimeFormatted)
            )
            else -> runAtSpecifiedTimeStartingTodayOrTomorrow(
                hour, minutes, command, isNextRunTodayOrTomorrow(
                    hour.toInt(),
                    minutes.toInt(),
                    simulatedCurrentTimeFormatted
                )
            )
        }
    }

    private fun runAtSpecifiedTimeStartingTodayOrTomorrow(
        hour: String,
        minutes: String,
        command: String,
        isTodayOrTomorrow: String
    ) {
        println("$hour:$minutes $isTodayOrTomorrow - $command")
    }

    private fun runStartingAtTheNthMinute(hour: String, command: String, nextRunTodayOrTomorrow: String) {
        println("$hour:00 $nextRunTodayOrTomorrow - $command")
    }

    private fun runStartingOnTheNthHour(
        simulatedCurrentTimeFormatted: LocalTime,
        minutes: Int,
        command: String,
        hasPassed: Boolean
    ) {
        val timeToStartRunning =
            if (hasPassed) LocalTime.of(simulatedCurrentTimeFormatted.hour, minutes).plusHours(1) else LocalTime.of(
                simulatedCurrentTimeFormatted.hour,
                minutes
            )

        val runTodayOrTomorrow =
            isNextRunTodayOrTomorrow(timeToStartRunning.hour, timeToStartRunning.minute, simulatedCurrentTimeFormatted)
        println("$timeToStartRunning $runTodayOrTomorrow - $command")
    }

    private fun runStartingFromSimulatedCurrentTime(simulatedCurrentTimeFormatted: LocalTime, command: String) =
        println("$simulatedCurrentTimeFormatted today - $command")
}