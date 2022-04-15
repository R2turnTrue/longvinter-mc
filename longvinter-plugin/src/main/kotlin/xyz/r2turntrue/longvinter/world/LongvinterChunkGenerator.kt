package xyz.r2turntrue.longvinter.world

import org.bukkit.Material
import org.bukkit.block.Biome
import org.bukkit.generator.BiomeProvider
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import org.bukkit.util.noise.SimplexOctaveGenerator
import java.util.*


class LongvinterChunkGenerator: ChunkGenerator() {

    override fun shouldGenerateStructures(): Boolean =
        false

    override fun shouldGenerateDecorations(): Boolean =
        false

    override fun shouldGenerateCaves(): Boolean =
        false

    override fun shouldGenerateMobs(): Boolean =
        false

    override fun shouldGenerateNoise(): Boolean =
        false

    override fun shouldGenerateSurface(): Boolean =
        false

    var currentHeight = 5

    override fun generateBedrock(worldInfo: WorldInfo, random: Random, chunkX: Int, chunkZ: Int, chunk: ChunkData) {
        val generator = SimplexOctaveGenerator(Random(worldInfo.seed), 3)
        generator.setScale(0.010)

        for (x in 0 until 16) {
            for (z in 0 until 16) {
                val rawX = chunkX * 16 + x
                val rawZ = chunkZ * 16 + z
                currentHeight = (generator.noise(rawX + 0.0, rawZ + 0.0, 0.5, 0.5) * 6.5 + 50.0).toInt()
                val isWater = currentHeight < 50
                if(isWater) {
                    for (i in currentHeight..50) {
                        chunk.setBlock(x, i, z, Material.WATER)
                    }
                } else {
                    chunk.setBlock(x, currentHeight, z, if(currentHeight == 51) Material.SAND else Material.GREEN_CONCRETE)
                }
                chunk.setBlock(x, currentHeight-1, z, if(!isWater) Material.DIRT else Material.WATER)
                for (i in currentHeight - 2 downTo 1) {
                    chunk.setBlock(x, i, z, if(!isWater) Material.STONE else Material.GRASS_BLOCK)
                }
                chunk.setBlock(x, 0, z, Material.BEDROCK)
            }
        }
    }

    override fun getDefaultBiomeProvider(worldInfo: WorldInfo): BiomeProvider =
        object: BiomeProvider() {
            override fun getBiome(worldInfo: WorldInfo, x: Int, y: Int, z: Int): Biome =
                if(y < 50) Biome.LUKEWARM_OCEAN else Biome.PLAINS

            override fun getBiomes(worldInfo: WorldInfo): MutableList<Biome> =
                mutableListOf(Biome.PLAINS, Biome.LUKEWARM_OCEAN)

        }

}