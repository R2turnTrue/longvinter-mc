package xyz.r2turntrue.longvinter.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.util.Vector


fun String.asMini(): Component =
    MiniMessage.miniMessage().deserialize(this)

fun Component.asMini(): String =
    MiniMessage.miniMessage().serialize(this)

fun spawnParticleAlongLine(start: Location, end: Location, particle: Particle, pointsPerLine: Int, particleCount: Int, offsetX: Double, offsetY: Double, offsetZ: Double, extra: Double) {
    val d = start.distance(end) / pointsPerLine
    for (i in 0 until pointsPerLine) {
        val l = start.clone()
        val direction = end.toVector().subtract(start.toVector()).normalize()
        val v = direction.multiply(i * d)
        l.add(v.x, v.y, v.z)
        start.world.spawnParticle(particle, l, particleCount, offsetX, offsetY, offsetZ, extra)
    }
}