package xyz.r2turntrue.longvinter.item.gun

import org.bukkit.Material
import org.bukkit.entity.Player

abstract class GunItem(val id: String, val itemName: String, val material: Material) {

    abstract fun launch(player: Player)

}