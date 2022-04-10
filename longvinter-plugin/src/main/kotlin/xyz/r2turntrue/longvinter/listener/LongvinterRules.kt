package xyz.r2turntrue.longvinter.listener

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityToggleSwimEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class LongvinterRules: Listener {

    @EventHandler
    fun noSwim(event: EntityToggleSwimEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        event.run {
            joinMessage(null)
            player.gameMode = GameMode.ADVENTURE
        }
    }

    @EventHandler
    fun damage(event: EntityDamageEvent) {
        if(event.entity is Player) {
            if(event.cause == EntityDamageEvent.DamageCause.FALL) {
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun quit(event: PlayerQuitEvent) {
        event.quitMessage(null)
    }

}