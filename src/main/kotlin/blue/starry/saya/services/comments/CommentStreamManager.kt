package blue.starry.saya.services.comments

import blue.starry.jsonkt.parseObject
import blue.starry.jsonkt.toJsonObject
import blue.starry.saya.endpoints.Subscription
import blue.starry.saya.services.comments.nicolive.models.Channel
import java.nio.file.Paths
import kotlin.io.path.readText

object CommentStreamManager {
    private val Streams = Paths.get("docs", "channels.json").readText().toJsonObject().map { entry ->
        val channel = entry.value.parseObject { Channel(it) }

        CommentStream(entry.key, channel)
    }

    fun findBy(target: String): CommentStream? {
        val serviceId = target.toIntOrNull()

        return Streams.find {
            it.id == target || (serviceId !=null && it.channel.serviceIds.contains(serviceId))
        }
    }

    fun getSubscriptions(): List<Subscription> {
        return Streams.filter {
            it.nico.isActive
        }.map {
            Subscription(it.id, Subscription.Type.Comments, 1)
        }
    }
}
