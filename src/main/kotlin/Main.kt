import CommandRuntimeUtils.printCommandRuntTime
import DateTimeUtils.isInputValid
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.time.LocalTime
import java.time.format.DateTimeFormatter


fun main(simulatedCurrentTime: Array<String>) {
    var input: List<String>
    BufferedReader(
        InputStreamReader(System.`in`, Charset.defaultCharset())
    ).use { reader ->
        input = reader.readLines()
    }

    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val simulatedCurrentTimeFormatted = try {
        LocalTime.parse(simulatedCurrentTime[0], dateTimeFormatter)
    } catch (e: java.lang.Exception) {
        println("Invalid simulated time value, must be in HH:mm format")
        return
    }

    input.forEachIndexed { index, schedulerConfig ->
        val singleConfig = schedulerConfig.split(" ")
        val minutes = singleConfig[0]
        val hour = singleConfig[1]
        val command = singleConfig[2]

        if (!isInputValid(minutes, hour, index)) return
        printCommandRuntTime(minutes, hour, simulatedCurrentTimeFormatted, command)
    }
}
