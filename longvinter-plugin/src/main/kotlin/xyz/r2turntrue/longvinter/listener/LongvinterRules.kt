package xyz.r2turntrue.longvinter.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityToggleSwimEvent

class LongvinterRules: Listener {

    @EventHandler
    fun noSwim(event: EntityToggleSwimEvent) {
        event.isCancelled = true
    }

}