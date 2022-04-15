package xyz.r2turntrue.longvinter

import org.bukkit.GameRule
import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.java.JavaPlugin
import xyz.r2turntrue.longvinter.core.data.connectDb
import xyz.r2turntrue.longvinter.listener.LongvinterRules
import xyz.r2turntrue.longvinter.world.LongvinterChunkGenerator

class LongvinterPlugin: JavaPlugin() {

    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator =
        LongvinterChunkGenerator()

    override fun onEnable() {
        server.pluginManager.registerEvents(LongvinterRules(), this)

        server.scheduler.scheduleSyncDelayedTask(this) {
            server.worlds.forEach { world ->
                world.setGameRule(GameRule.DO_MOB_SPAWNING, false)
                world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true)
                world.setGameRule(GameRule.DO_WEATHER_CYCLE, false)
            }
        }

        connectDb()
    }

}