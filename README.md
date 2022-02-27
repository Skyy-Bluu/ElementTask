# ElementTask

# Instruction to run the task

Use the following command to create a .jar 

`kotlinc src/main/kotlin/Main.kt src/main/kotlin/CommandRuntimeUtils.kt src/main/kotlin/DateTimeUtils.kt -include-runtime -d ElementRunable.jar`

Run the created .jar using the following command 

`cat input | kotlin -classpath ElementRunable.jar MainKt <simulated time to run against>`

Note: Change the contents of the _input.txt_ file to test various cofigs
