package io.github.hanchen.hcmattersplit.data.fenjie

import io.github.hanchen.hcmattersplit.Main
import io.github.hanchen.hcmattersplit.data.fenjie.imp.FenJieObject
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

fun FenJieManage.loadFromDirectory(dir: File) {
    fenJieMap.clear()
    dir.walk()
        .filter { it.isFile && it.extension.equals("yml", true) }
        .forEach { file ->
            YamlConfiguration.loadConfiguration(file).apply {
                val name = getString("name") ?: return@apply
                register(name, FenJieObject(
                    name = name,
                    change = getDouble("change", 0.5),
                    matching = getStringList("matching"),
                    rewards = getStringList("rewards")
                )
                )
            }
        }
}