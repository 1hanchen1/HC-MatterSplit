package io.github.hanchen.hcmattersplit.data.matching

import org.bukkit.inventory.ItemStack

interface Matching {
    fun isMatching(itemStack: ItemStack, matchingString: String): Boolean
}