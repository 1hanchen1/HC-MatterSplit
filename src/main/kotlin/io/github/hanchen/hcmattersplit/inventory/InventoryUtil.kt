package io.github.hanchen.hcmattersplit.inventory

import io.github.hanchen.hcmattersplit.Main
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

fun Player.openInventory() {
    val inventory = Bukkit.createInventory(null, 54, Main.plugin.inventoryTitle).apply {
        setItem(49, Main.plugin.inventoryItemStack)
    }
    openInventory(inventory)
}