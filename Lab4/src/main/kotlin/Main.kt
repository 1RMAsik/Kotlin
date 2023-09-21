import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.util.Scanner

fun task1(){
    println("Введите число Х: ")
    val x = readln().toInt()
    val factorialResult = factorial(x)
    println("Сумма факториала $x равен $factorialResult")

    var  sumOfFactorials = 0L
    for (i in 5..10){
        val factorialValue = factorial(i)
        sumOfFactorials += factorialValue
    }
    println("Сумма факториалов чисел от 5 до 10 равна $sumOfFactorials")
}

fun task2(){
    val scanner = Scanner(System.`in`)
    val numbers = mutableListOf<Double>()

    println("Введите числа (для завершения ввода введите пустую строку)")

    while (true){
        print("Введите число: ")
        val input = scanner.nextLine()

        if (input.isBlank()){
            break
        }

        try {
            val number = input.toDouble()
            numbers.add(number)
        }catch (e: NumberFormatException){
            println("Некорректный ввод. Введите число.")
        }
    }

    if (numbers.isEmpty()){
        println("Вы не ввели ни одного числа.")
    }else{
        println("Введеенные числа: $numbers")
        val minimum = findMinimum(*numbers.toDoubleArray())
        println("Минимальное значение: $minimum")
    }



}

fun task3(){
    val scanner = Scanner(System.`in`)
    print("Введите длину: ")
    val length = scanner.nextDouble()
    print("Введите ширину: ")
    val width = scanner.nextDouble()
    print("Введите высоту(опционально): ")
    val height = scanner.nextDouble()

    if (height > 0) {
        val surfaceArea = calculateSurfaceArea(length, width, height)
        println("Площадь поверхности параллелепипеда: $surfaceArea")
    } else {
        val surfaceArea = calculateSurfaceArea(length, width)
        println("Площадь поверхности прямоугольника: $surfaceArea")
    }

}

fun task4(){

    print("Введите размер оклада Светланы Геннадьевне: ")
    val salary = readln().toDoubleOrNull() ?: 0.0

    print("Введите стаж работы (в годах): ")
    val experience = readln().toIntOrNull() ?: 0

    print("Введите категорию учителя (0 - без категории, 1 - 2я, 2 - 1я, 3 - высшая): ")
    val category = when (readln().toIntOrNull()){
        0-> 0.0
        1-> 1.1
        2-> 1.3
        3-> 1.5
        else -> 0.0
    }

    val experienceCoefficient = when{
        experience < 5-> 0.0
        experience in 5..9 -> 1.2
        experience in 10..14 -> 1.4
        else -> 1.6
    }

    val totalSalary = calculateTeacherSalary(salary, experienceCoefficient, category)

    println("Зарплата Светланы Геннадьевны: $totalSalary")
}



fun factorial(x:Int): Long{
    if(x<0){
        throw IllegalArgumentException("Факториал определен только для неотрицательных целых чисел.")

    }
    var result: Long = 1
    for (i in 1..x) {
        result *= i
    }
    return result
}

fun findMinimum(vararg numbers: Double): Double{
    var minimum = Double.MAX_VALUE
    for (number in numbers){
        if(number< minimum){
            minimum = number
        }
    }
    return minimum
}

fun calculateSurfaceArea(length: Double, width: Double): Double{
    return 2 * (length * width)
}

fun calculateSurfaceArea(length: Double, width: Double, height: Double): Double{
    return 2 * (length * width + length * height + width * height)
}

fun calculateTeacherSalary(salary: Double, experienceCoefficient: Double, categoryCoefficient: Double): Double{
    val bonus = salary * 0.05 // 5% премия Светлане Геннадьевне
    val totalSalary = salary  * experienceCoefficient * categoryCoefficient + bonus
    return totalSalary
}

fun main(){

    println("Выбери задание: ")
    val n = readln().toInt()

    when(n){
        1 -> task1()
        2 -> task2()
        3 -> task3()
        4 -> task4()
        else -> println("Не то ввёл!")
    }
}