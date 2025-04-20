package io.github.hanchen.hcmattersplit.listener

import io.github.hanchen.hcmattersplit.HC_MatterSplit
import io.github.hanchen.hcmattersplit.data.fenjie.FenJieManage
import io.github.hanchen.hcmattersplit.util.checkChange
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent

object InventoryClickListener {

    @SubscribeEvent(priority = EventPriority.HIGH)
    fun onInventoryClick(event: InventoryClickEvent) {
        val inventory = event.clickedInventory ?: return
        val player = event.whoClicked as? Player ?: return

        if (inventory.holder == null && inventory.title == HC_MatterSplit.plugin.inventoryTitle) {
            event.isCancelled = true

            if (event.slot == 49) {
                var successCount = 0
                var failedCount = 0

                inventory.contents.forEachIndexed { index, item ->
                    if (index != 49 && item?.type != Material.AIR && item?.hasItemMeta() == true) {
                        FenJieManage.allFenJie.values.forEach { fenJie ->
                            if (fenJie.isMatching(item)) {
                                repeat(item.amount) {
                                    if (checkChange(fenJie.fenJieChange)) {
                                        successCount++
                                        fenJie.exeReward(player)
                                    } else {
                                        failedCount++
                                    }
                                }
                                inventory.setItem(index, null)
                            }
                        }
                    }
                }

                with(HC_MatterSplit.plugin) {
                    if (message1 != "none") player.sendMessage(message1.replace("<number>", "$successCount"))
                    if (message2 != "none") player.sendMessage(message2.replace("<number>", "$failedCount"))
                }
            }
        }
    }
}