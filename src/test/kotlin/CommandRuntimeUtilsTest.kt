import CommandRuntimeUtils.printCommandRuntTime
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.time.LocalTime
import kotlin.test.assertEquals


class CommandRuntimeUtilsTest {
    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun printCommandRuntTimeReturnsFormattedStringOfTheCurrentTimeAndCommandWhenBothInputsAreWildCards() {
        val hour = "*"
        val minutes = "*"
        val currentSimulatedTime = LocalTime.of(13, 0)
        val currentCommand = "test command"
        printCommandRuntTime(minutes, hour, currentSimulatedTime, currentCommand)
        assertEquals(
            "13:00 today - test command", outputStreamCaptor.toString()
                .trim()
        )
    }

    @Test
    fun printCommandRuntTimeReturnsFormattedStringOfTheCurrentHourAndGivenMinutesAndCommandWhenGivenHourIsWildCardAndCurrentMinutesAreLessThanGivenMinutes() {
        val hour = "*"
        val minutes = "45"
        val currentSimulatedTime = LocalTime.of(13, 0)
        val currentCommand = "test command"
        printCommandRuntTime(minutes, hour, currentSimulatedTime, currentCommand)
        assertEquals(
            "13:45 today - test command", outputStreamCaptor.toString()
                .trim()
        )
    }

    @Test
    fun printCommandRuntTimeReturnsFormattedStringOfTheNextHourAndGivenMinutesAndCommandWhenGivenHourIsWildCardAndCurrentMinutesAreGreaterThanGivenMinutes() {
        val hour = "*"
        val minutes = "45"
        val currentSimulatedTime = LocalTime.of(13, 50)
        val currentCommand = "test command"
        printCommandRuntTime(minutes, hour, currentSimulatedTime, currentCommand)
        assertEquals(
            "14:45 today - test command", outputStreamCaptor.toString()
                .trim()
        )
    }

    @Test
    fun printCommandRuntTimeReturnsFormattedStringOfTheNextHourTomorrowAndGivenMinutesAndCommandWhenGivenHourIsWildCardAndCurrentMinutesAreGreaterThanGivenMinutes() {
        val hour = "*"
        val minutes = "45"
        val currentSimulatedTime = LocalTime.of(23, 50)
        val currentCommand = "test command"
        printCommandRuntTime(minutes, hour, currentSimulatedTime, currentCommand)
        assertEquals(
            "00:45 tomorrow - test command", outputStreamCaptor.toString()
                .trim()
        )
    }

    @Test
    fun printCommandRuntTimeReturnsFormattedStringOfTheGivenHourAnd00MinutesTodayAndCommandWhenGivenMinutesIsWildCardAndCurrentHourIsLessThanGivenHour() {
        val hour = "18"
        val minutes = "*"
        val currentSimulatedTime = LocalTime.of(13, 50)
        val currentCommand = "test command"
        printCommandRuntTime(minutes, hour, currentSimulatedTime, currentCommand)
        assertEquals(
            "18:00 today - test command", outputStreamCaptor.toString()
                .trim()
        )
    }

    @Test
    fun printCommandRuntTimeReturnsFormattedStringOfTheGivenHourAnd00MinutesTomorrowAndCommandWhenGivenMinutesIsWildCardAndCurrentHourIsGreaterThanGivenHour() {
        val hour = "18"
        val minutes = "*"
        val currentSimulatedTime = LocalTime.of(19, 50)
        val currentCommand = "test command"
        printCommandRuntTime(minutes, hour, currentSimulatedTime, currentCommand)
        assertEquals(
            "18:00 tomorrow - test command", outputStreamCaptor.toString()
                .trim()
        )
    }

    @Test
    fun printCommandRuntTimeReturnsFormattedStringOfTheGivenHourAndMinutesTodayAndCommandWhenCurrentTimeHasNotPassedGivenTime() {
        val hour = "18"
        val minutes = "00"
        val currentSimulatedTime = LocalTime.of(17, 50)
        val currentCommand = "test command"
        printCommandRuntTime(minutes, hour, currentSimulatedTime, currentCommand)
        assertEquals(
            "18:00 today - test command", outputStreamCaptor.toString()
                .trim()
        )
    }

    @Test
    fun printCommandRuntTimeReturnsFormattedStringOfTheGivenHourAndMinutesTomorrowAndCommandWhenCurrentTimeHasPassedGivenTime() {
        val hour = "18"
        val minutes = "00"
        val currentSimulatedTime = LocalTime.of(19, 50)
        val currentCommand = "test command"
        printCommandRuntTime(minutes, hour, currentSimulatedTime, currentCommand)
        assertEquals(
            "18:00 tomorrow - test command", outputStreamCaptor.toString()
                .trim()
        )
    }
}