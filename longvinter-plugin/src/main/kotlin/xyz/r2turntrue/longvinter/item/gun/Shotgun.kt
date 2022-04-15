package xyz.r2turntrue.longvinter.item.gun

import org.bukkit.Material
import org.bukkit.entity.Player

object Shotgun: GunItem("shotgun", "산탄총", Material.GOLDEN_HOE) {
    override fun launch(player: Player) {
        player.run {
        }
    }
}