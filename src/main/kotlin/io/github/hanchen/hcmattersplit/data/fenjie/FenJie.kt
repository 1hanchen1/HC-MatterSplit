package io.github.hanchen.hcmattersplit.data.fenjie

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface FenJie {
    val fenJieName: String
    var fenJieChange: Double
    var fenJieReward: List<String>
    var fenJieMatching: List<String>

    fun exeReward(player: Player)
    fun isMatching(itemStack: ItemStack): Boolean
}