fun main() {
    println("Введите время начала работы в формате H1:M1")
    val startTime = readln().split(":").map{ it.toInt() }
    println("Введите время окончания работы в формате H2:M2")
    val endTime = readln().split(":").map{ it.toInt() }

    val startMinutes = startTime[0] * 60 + startTime[1]
    val endMinutes = endTime[0] * 60 + endTime[1]
    val totalMinutes = endMinutes - startMinutes

    if (totalMinutes < 0) {
        println("Время начала работы не может быть позже времени окончания работы")
    }
    else if(totalMinutes / 60 >= 24){
        println("Студент учился слишком много!")
    }
    else {
        val hours = totalMinutes / 60
        val minutes = totalMinutes % 60
        println("Студент поработал: $hours часов и $minutes минут")
    }
}
