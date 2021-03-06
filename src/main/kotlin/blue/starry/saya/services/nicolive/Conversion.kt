package blue.starry.saya.services.nicolive

import blue.starry.saya.models.Comment
import blue.starry.saya.services.comments.nicolive.models.Chat

fun Chat.toSayaComment(source: String): Comment {
    val (commands, parsed) = parseMail(mail)
    val (color, type, size) = parsed

    return Comment(
        source = source,
        no = no,
        time = date + dateUsec / 1000000.0,
        author = userId,
        text = content,
        color = color,
        type = type,
        size = size,
        commands = commands
    )
}

private fun parseMail(mail: String): Pair<List<String>, Triple<String, String, String>> {
    val commands = mail.split(' ').filterNot { it == "184" }.filterNot { it.isBlank() }

    var color = "#ffffff"
    var position = "right"
    var size = "normal"
    commands.forEach { command ->
        val c = parseColor(command)
        if (c != null) {
            color = c
        }

        val p = parsePosition(command)
        if (p != null) {
            position = p
        }

        val s = parseSize(command)
        if (s != null) {
            size = s
        }
    }

    return commands to Triple(color, position, size)
}

private fun parseColor(command: String): String? {
    return when (command) {
        "red" -> "#e54256"
        "pink" -> "#ff8080"
        "orange" -> "#ffc000"
        "yellow" -> "#ffe133"
        "green" -> "#64dd17"
        "cyan" -> "#39ccff"
        "blue" -> "#0000ff"
        "purple" -> "#d500f9"
        "black" -> "#000000"
        "white" -> "#ffffff"
        "white2" -> "#cccc99"
        "niconicowhite" -> "#cccc99"
        "red2" -> "#cc0033"
        "truered" -> "#cc0033"
        "pink2" -> "#ff33cc"
        "orange2" -> "#ff6600"
        "passionorange" -> "#ff6600"
        "yellow2" -> "#999900"
        "madyellow" -> "#999900"
        "green2" -> "#00cc66"
        "elementalgreen" -> "#00cc66"
        "cyan2" -> "#00cccc"
        "blue2" -> "#3399ff"
        "marineblue" -> "#3399ff"
        "purple2" -> "#6633cc"
        "nobleviolet" -> "#6633cc"
        "black2" -> "#666666"
        else -> null
    }
}

private fun parsePosition(command: String): String? {
    return when (command) {
        "ue" -> "top"
        "naka" -> "right"
        "shita" -> "bottom"
        else -> null
    }
}

private fun parseSize(command: String): String? {
    if (command == "small" || command == "medium" || command == "big") {
        return command
    }

    return null
}
