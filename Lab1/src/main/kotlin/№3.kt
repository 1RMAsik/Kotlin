fun main(){

    println("Введите 3 числа: ")
    val n1: Int = readlnOrNull()?.toInt() ?: 0
    val n2: Int = readlnOrNull()?.toInt() ?: 0
    val n3: Int = readlnOrNull()?.toInt() ?: 0

    val max = if (n1>n2 && n1>3){
        n1
    }
    else if (n2>n1 && n2>n3){
        n2
    }
    else{
        n3
    }
    println("Максимальное число: $max")
}