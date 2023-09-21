fun task1(){
    val array = IntArray(10)
    for (i in 0..<array.size/2){
        array[i] = (0..100).random()
    }
    for(i in array.size/2..<array.size){
        array[i]= i * 2
    }
    println("Массив в строку: " + array.joinToString())
    println("Массив в столбец: ")
    for (i in array.indices){
        println(array[i])
    }
}

fun task2(){
    val array = IntArray(10)
    for (i in array.indices){
        array[i] = (0..100).random()
    }
    println("Массив: " + array.joinToString())
    val min = array.minOrNull()!!
    val sum = array.slice(array.indexOf(min) + 1..<array.size).sum()
    println("Минимальный элемент в массиве: $min")
    println("Сумма после минимального элемента: $sum")
}

fun task3(){
    println("Введите размер массива: ")
    val n = readln().toInt()
    val array = IntArray(n)
    for (i in array.indices){
        array[i] = (0..100).random()
    }
    println("Массив: " + array.joinToString())
    val lastNum = array.lastOrNull { it %2 != 0 }
    if (lastNum != null){
        for (i in array.indices){
            if(array[i] %2 !=0){
                array[i]+= lastNum
            }
        }
    }
    println(array.contentToString())
}

fun task4(){

    println("Введите размер массива: ")
    val n = readln().toInt()
    val array = IntArray(n)
    for (i in array.indices){
        array[i] = (0..10).random()
    }
    println("Массив: " + array.joinToString())

    val counts = mutableMapOf<Int, Int>()
    for (i in array){
        counts[i] = counts.getOrDefault(i, 0) + 1
    }
    println("Количество различных элементов в массиве: ${counts.size}")
    for((k,v) in counts){
        if(v >=2){
        println("$k встречается в массиве: $v раза")
        }
        else{
            println("$k встречается в массиве: $v раз")
        }
    }
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