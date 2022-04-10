plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

allprojects {
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    repositories {
        mavenCentral()
        maven(url = "https://papermc.io/repo/repository/maven-public/")
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")

        implementation("net.kyori:adventure-text-minimessage:4.10.1")
        implementation(kotlin("stdlib"))
    }

    tasks {
        processResources {
            filesMatching("**/*.yml") {
                expand(project.properties)
                expand(project.extra.properties)
            }
        }

        test {
            useJUnitPlatform()
        }

        create<Jar>("paperJar") {
            from(sourceSets["main"].output)
            archiveBaseName.set(project.name)
            archiveVersion.set("") // For bukkit plugin update

            doLast {
                copy {
                    from(archiveFile)
                    val plugins = File(rootDir, ".debug/plugins/")
                    into(if (File(plugins, archiveFileName.get()).exists()) File(plugins, "update") else plugins)
                }
            }
        }

        shadowJar {
            from(sourceSets["main"].output)
            archiveBaseName.set(project.name)
            archiveVersion.set("") // For bukkit plugin update

            doLast {
                copy {
                    from(archiveFile)
                    val plugins = File(rootDir, ".debug/plugins/")
                    into(if (File(plugins, archiveFileName.get()).exists()) File(plugins, "update") else plugins)
                }
            }
        }
    }
}
