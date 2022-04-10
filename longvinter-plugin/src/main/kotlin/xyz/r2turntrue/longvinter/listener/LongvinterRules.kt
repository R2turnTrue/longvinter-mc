package xyz.r2turntrue.longvinter.listener

import net.kyori.adventure.text.Component
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
            /*
            [ 롱빈터 실험버전 ]
            롱빈터 실험버전에 오신걸 환영합니다!
            일부 기본 기능만 완성된 상태이며, 정상적인 플레이가
            불가능하실 수 있습니다! 진행상태는 github의 todo
            항목을 확인하세요!

            [ LONGVINTER-MC EXPERIMENTAL ]
            Welcome to LongvinterMC!
            Some basic features only completed,
            You may not play the game normally!
            Check github's todo to check progress
            of the project!

            [GITHUB](https://github.com/R2turnTrue/longvinter-mc)
             */
            player.sendMessage("<green><bold>[ 롱빈터 실험버전 ]</bold></green>\n" +
                    "<white>롱빈터 실험버전에 오신걸 환영합니다!\n" +
                    "일부 기본 기능만 완성된 상태이며, 정상적인 플레이가\n" +
                    "불가능하실 수 있습니다! 진행도는 github의 todo\n" +
                    "항목을 확인하세요!\n")
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