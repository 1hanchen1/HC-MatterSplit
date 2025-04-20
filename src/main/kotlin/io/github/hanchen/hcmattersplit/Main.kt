package io.github.hanchen.hcmattersplit

import io.github.hanchen.hcmattersplit.command.MainCommand
import io.github.hanchen.hcmattersplit.config.YamlConfig
import io.github.hanchen.hcmattersplit.data.fenjie.FenJieManage
import io.github.hanchen.hcmattersplit.data.fenjie.loadFromDirectory
import io.github.hanchen.hcmattersplit.data.matching.MatchingManage
import io.github.hanchen.hcmattersplit.data.reward.RewardManage
import io.github.hanchen.hcmattersplit.listener.InventoryClickListener
import io.github.hanchen.hcmattersplit.listener.InventoryCloseListener
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import taboolib.common.platform.*
import taboolib.common.platform.function.info
import taboolib.library.xseries.getItemStack
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import java.io.File

@TabooLibPlugin
class Main : JavaPlugin() {

    companion object {
        lateinit var instance: Main
            private set
    }

    @Config("config.yml", autoSave = true)
    lateinit var config: Configuration
        private set

    lateinit var inventoryTitle: String
    lateinit var inventoryItem: ItemStack
    lateinit var successMessage: String
    lateinit var failMessage: String

    private val fenJieDir by lazy { File(dataFolder, "FenJie").apply { mkdirs() } }

    override fun onEnable() {
        instance = this
        loadConfig()
        registerComponents()
        info("§a[HC-MatterSplit] 插件已启用")
    }

    override fun onDisable() {
        info("§c[HC-MatterSplit] 插件已禁用")
    }

    private fun loadConfig() {
        inventoryTitle = config.getString("inventory.title", "§6物品分解台")!!
        successMessage = config.getString("messages.success", "§a成功分解了<number>件物品!")!!
        failMessage = config.getString("messages.failed", "§c有<number>次分解失败!")!!
        inventoryItem = config.getItemStack("inventory.item", ItemStack(Material.DIAMOND))!!.apply {
            editMeta {
                it.displayName(config.getString("inventory.item-name", "§a确认分解"))
                it.lore = config.getStringList("inventory.item-lore")
            }
        }
    }

    private fun registerComponents() {
        // 注册命令和监听器
        MainCommand.register()
        InventoryClickListener.register()
        InventoryCloseListener.register()

        // 加载分解配置
        FenJieManage.loadFromDirectory(fenJieDir)

        // 注册默认匹配和奖励类型
        MatchingManage.registerDefaults()
        RewardManage.registerDefaults()
    }
}