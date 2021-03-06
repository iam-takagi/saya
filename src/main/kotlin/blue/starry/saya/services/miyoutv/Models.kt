package blue.starry.saya.services.miyoutv

import blue.starry.jsonkt.JsonObject
import blue.starry.jsonkt.delegation.*

data class Login(override val json: JsonObject): JsonModel {
    val token by string
}

data class Channels(override val json: JsonObject): JsonModel {
    val status by string
    val data by model { Data(it) }

    data class Data(override val json: JsonObject): JsonModel {
        val nHits by int("n_hits")
        val channels by modelList { Channel(it) }

        data class Channel(override val json: JsonObject): JsonModel {
            val id by string
            val type by string
        }
    }
}

data class Intervals(override val json: JsonObject): JsonModel {
    val status by string
    val data by model { Data(it) }

    data class Data(override val json: JsonObject): JsonModel {
        val nHits by int("n_hits")
        val intervals by modelList { Interval(it) }

        data class Interval(override val json: JsonObject): JsonModel {
            val nHits by int("n_hits")
            val start by long
        }
    }
}

data class Comments(override val json: JsonObject): JsonModel {
    val status by string
    val data by model { Data(it) }

    data class Data(override val json: JsonObject): JsonModel {
        val nHits by int("n_hits")
        val comments by modelList { Comment(it) }

        data class Comment(override val json: JsonObject): JsonModel {
            val title by string
            val name by string
            val email by string
            val text by string
            val time by long
            val id by string
            val channels by stringList
        }
    }
}

