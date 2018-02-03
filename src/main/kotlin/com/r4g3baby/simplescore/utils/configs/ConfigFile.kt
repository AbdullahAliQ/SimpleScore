package com.r4g3baby.simplescore.utils.configs

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

@Suppress("LeakingThis")
open class ConfigFile(plugin: JavaPlugin, name: String) : File(plugin.dataFolder, "$name.yml") {
    protected val config: FileConfiguration

    init {
        if (!plugin.dataFolder.exists()) {
            plugin.dataFolder.mkdirs()
        }

        if (!this.exists()) {
            if (plugin.getResource("$name.yml") != null) {
                plugin.saveResource("$name.yml", true)
            } else {
                this.createNewFile()
            }
        }

        config = YamlConfiguration.loadConfiguration(this)
    }

    protected fun saveConfig() = config.save(this)
}