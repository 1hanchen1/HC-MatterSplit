package io.github.hanchen.hcmattersplit.listener

import io.github.hanchen.hcmattersplit.HC_MatterSplit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack
import taboolib.common.platform.event.SubscribeEvent

object InventoryCloseListener {

    @SubscribeEvent
    fun onInventoryClose(event: InventoryCloseEvent) {
        val inventory = event.inventory
        val player = event.player as? Player ?: return

        if (inventory.holder == null && inventory.title == HC_MatterSplit.plugin.inventoryTitle) {
            inventory.contents
                .filterIndexed { index, _ -> index != 49 }
                .filterNotNull()
                .filter { it.type != Material.AIR }
                .forEach { item ->
                    player.inventory.addItem(item).values.forEach {
                        player.world.dropItem(player.location, it)
                    }
                }
        }
    }
}