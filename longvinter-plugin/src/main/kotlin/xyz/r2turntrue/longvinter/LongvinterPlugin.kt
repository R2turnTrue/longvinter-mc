package xyz.r2turntrue.longvinter

import org.bukkit.generator.ChunkGenerator
import org.bukkit.plugin.java.JavaPlugin
import xyz.r2turntrue.longvinter.listener.LongvinterRules
import xyz.r2turntrue.longvinter.world.LongvinterChunkGenerator

class LongvinterPlugin: JavaPlugin() {

    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator =
        LongvinterChunkGenerator()

    override fun onEnable() {
        server.pluginManager.registerEvents(LongvinterRules(), this)
    }

}