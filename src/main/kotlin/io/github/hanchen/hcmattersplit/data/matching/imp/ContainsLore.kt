package io.github.hanchen.hcmattersplit.data.matching.imp

import io.github.hanchen.hcmattersplit.data.matching.Matching
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class ContainsLore : Matching {
    override fun isMatching(itemStack: ItemStack, matchingString: String): Boolean {
        return matchingString.split("<->").let {
            if (it.size < 2 || it[0] != "containsLore") return false
            val targetLore = it[1]
            itemStack.itemMeta?.lore?.any { lore -> lore.contains(targetLore) } ?: false
        }
    }
}