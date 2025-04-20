package io.github.hanchen.hcmattersplit.config

import taboolib.common.platform.ProxyPlugin
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Type
import java.io.File

class YamlConfig(
    val name: String,
    val plugin: ProxyPlugin,
    val type: Type = Type.YAML,
    val autoSave: Boolean = true
) {
    val file by lazy { File(plugin.dataFolder, name) }
    val config by lazy {
        if (!file.exists()) plugin.saveResource(name, false)
        Configuration.loadFromFile(file, type)
    }

    fun reload() {
        config.reload()
    }

    fun save() {
        if (autoSave) config.saveToFile(file)
    }
}