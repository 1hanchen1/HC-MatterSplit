package io.github.hanchen.hcmattersplit.data.reward

import org.bukkit.entity.Player

interface Reward {
    fun exeReward(player: Player, exeString: String)
}