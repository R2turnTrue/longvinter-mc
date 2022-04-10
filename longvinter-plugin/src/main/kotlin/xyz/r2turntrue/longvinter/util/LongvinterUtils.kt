package xyz.r2turntrue.longvinter.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

fun String.asMini(): Component =
    MiniMessage.miniMessage().deserialize(this)

fun Component.asMini(): String =
    MiniMessage.miniMessage().serialize(this)