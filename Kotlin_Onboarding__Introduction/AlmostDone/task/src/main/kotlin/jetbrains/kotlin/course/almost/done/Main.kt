package jetbrains.kotlin.course.almost.done

fun safeReadLine(): String =
    readln() ?: error("Null input")

fun trimPicture(picture: String) = picture.trimIndent()

fun applyBordersFilter(picture: String): String {
    val pictureRows = picture.lines()
    val pictureWidth = getPictureWidth(picture)
    val horizontalBorder = "$borderSymbol".repeat(pictureWidth + 4)

    val sb = StringBuilder()
    sb.append("$horizontalBorder$newLineSymbol")
    for (row in pictureRows) {
        sb.append("$borderSymbol$separator$row")
        if (row.length < pictureWidth) {
            sb.append("$separator".repeat(pictureWidth - row.length))
        }
        sb.append("$separator$borderSymbol$newLineSymbol")
    }
    sb.append("$horizontalBorder$newLineSymbol")
    return sb.toString()
}

fun applySquaredFilter(picture: String): String {
    val bordered = applyBordersFilter(picture.trimIndent())
    val pictureRows = bordered.lines()

    val sbTop = StringBuilder()
    val sbBottom = StringBuilder()
    for (index in pictureRows.indices) {
        val newRow = pictureRows[index].repeat(2)
        when (index) {
            0 -> sbTop.append("$newRow$newLineSymbol")
            pictureRows.indices.last -> sbBottom.append(newRow)
            else -> {
                sbTop.append("$newRow$newLineSymbol")
                sbBottom.append("$newRow$newLineSymbol")
            }
        }
    }
    return "$sbTop$sbBottom"
}

fun applyFilter(picture: String, filter: String): String {
    val trimmedPicture = trimPicture(picture)
    return when (filter) {
        "borders" -> applyBordersFilter(trimmedPicture)
        "squared" -> applySquaredFilter(trimmedPicture)
        else -> error("Unexpected filter")
    }
}

fun chooseFilter(): String {
    println("Please choose the filter: 'borders' or 'squared'.")
    var filter: String = safeReadLine()
    while (filter !in listOf("borders", "squared")) {
        println("Please input 'borders' or 'squared'")
        filter = safeReadLine()
    }
    return filter
}

fun choosePicture(): String {
    println("Please choose a picture. The possible options are: ${allPictures()}")
    var picture: String = safeReadLine()
    while (picture !in allPictures()) {
        println("Please choose a picture. The possible options are: ${allPictures()}")
        picture = safeReadLine()
    }
    return getPictureByName(picture)!!
}

fun getPicture(): String {
    println("Do you want to use a predefined picture or a custom one? Please input 'yes' for a predefined image or 'no' for a custom one")
    val choice = safeReadLine()
    when (choice) {
        "yes" -> return choosePicture()
        "no" -> {
            println("Please input a custom picture")
            val picture = safeReadLine()
            return picture
        }

        else -> {
            println("Please input 'yes' or 'no'")
            return getPicture()
        }
    }
}

fun photoshop():Unit {
    val userInputPic = getPicture()
    val userInputFilter = chooseFilter()
    print("The old image:\n$userInputPic")
    val picture = applyFilter(userInputPic,userInputFilter)
    print("The transformed picture:\n$picture")
}


fun main() {
    // Uncomment this code on the last step of the game

    photoshop()
}
