fun main() {
    println("Введите длину комнаты в сантиметрах")
    val length = readlnOrNull()?.toInt() ?: 0
    println("Введите ширину комнаты в сантиметрах")
    val width = readlnOrNull()?.toInt() ?: 0

    val tileLength = 30 // длина плитки в сантиметрах
    val tileWidth = 30 // ширина плитки в сантиметрах

    val fullTilesCount = ((length / tileLength) * (width / tileWidth)).toInt()
    val partialTileArea = length*width - fullTilesCount*900; //((length % tileLength) * (width % tileWidth)) / 100.0
    val partialTileCount = partialTileArea.toInt()

    println("Количество целых плиток: $fullTilesCount")
    println("Необходимо заполнить $partialTileArea квадратных сантиметров разрезанной плиткой")
    println("Количество разрезанной плитки: $partialTileCount")
}
