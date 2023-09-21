import kotlin.math.sqrt

fun task1(){

    println("For")
    for (i in 0..9) print("Никита")
    println("While")
    var i = 0
    while (i <= 9){
        print("Никита")
        i++
    }
    println("Repeat")
    repeat(10)
    {
        print("Никита")
    }
}

fun task2(){

    println("Выбери число")
    val n: Int = readln().toInt()
    fun isPrime(num: Int): Boolean {
        if (num == 2 || num == 3) {
            return true
        }
        if (num == 1 || num % 2 == 0) {
            return false
        }
        var i = 3
        while (i <= sqrt(num.toDouble())) {
            if (num % i == 0) {
                return false
            }
            i += 2
        }
        return true
    }
    for (i in 2..n) {
        if (isPrime(i)) {
            print("$i ")
        }
    }
}

fun task3(){

    val numbers = mutableListOf<Int>()
    while (true) {
        val input = readln().toInt()
        if (input == 0) {
            break
        }
        numbers.add(input)
    }


    val max = numbers.maxOrNull()!!


    var count = 0
    for (num in numbers) {
        if (num == max) {
            count++
        }
    }
    println(count)
}



fun main(){

    println("Выбери задание: ")
    val n = readln().toInt()

    when(n){
        1 -> task1()
        2 -> task2()
        3 -> task3()
        else -> println("Не то ввёл!")
    }
}