import DateTimeUtils.hasNotPassed
import DateTimeUtils.isInputValid
import DateTimeUtils.isNextRunTodayOrTomorrow
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalTime
import kotlin.test.assertEquals

class DateTimeUtilsTest {

    @Test
    fun hasPassedReturnsFalseIfMinutesGivenHaveNoBeenPassedByTheCurrentMinutes(){
        val currentMinutes = 20
        val givenMinutes = "50"
        val value = givenMinutes.hasNotPassed(currentMinutes)
        assertFalse(value)
    }

    @Test
    fun hasPassedReturnsTrueIfMinutesGivenHaveNoBeenPassedByTheCurrentMinutes(){
        val currentMinutes = 50
        val givenMinutes = "20"
        val value = givenMinutes.hasNotPassed(currentMinutes)
        assertTrue(value)
    }

    @Test
    fun isInputValidReturnsTrueWhenInputHasValidMinutesAndHourValue(){
        val minutes = "15"
        val hour = "3"
        val index = 2

        val value = isInputValid(minutes, hour, index)

        assertTrue(value)
    }

    @Test
    fun isInputValidReturnsFalseWhenInputHasInvalidMinutesValue(){
        val minutes = "99"
        val hour = "3"
        val index = 2

        val value = isInputValid(minutes, hour, index)

        assertFalse(value)
    }

    @Test
    fun isInputValidReturnsFalseWhenInputHasInvalidHourValue(){
        val minutes = "45"
        val hour = "100"
        val index = 2

        val value = isInputValid(minutes, hour, index)

        assertFalse(value)
    }

    @Test
    fun isNextRunTodayOrTomorrowReturnsTodayWhenGivenTimeDidntPassCurrentTime(){
        val minutes = 30
        val hour = 15
        val currentTime = LocalTime.of(13, 0)
        val value = isNextRunTodayOrTomorrow(hour, minutes, currentTime)
        assertEquals("today", value)
    }

    @Test
    fun isNextRunTodayOrTomorrowReturnsTomorrowWhenGivenTimeDidPassCurrentTime(){
        val minutes = 30
        val hour = 1
        val currentTime = LocalTime.of(13, 0)
        val value = isNextRunTodayOrTomorrow(hour, minutes, currentTime)
        assertEquals("tomorrow", value)
    }
}