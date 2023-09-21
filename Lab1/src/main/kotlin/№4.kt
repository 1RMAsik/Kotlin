fun main(){

    println("Введи денюжку (желательно еще дайте)!")
    val num: Int = readlnOrNull()?.toInt() ?: 0
    val rubles = when {
        num % 10 == 1 && num % 100 != 11 -> "рубль"
        num % 10 in 2..4 && num % 100 !in 12..14 -> "рубля"
        num % 10 in 5..9 && num % 100 !in 15..19 -> "рублей"

        else -> "рублей"
    }


    println("$num $rubles")
}