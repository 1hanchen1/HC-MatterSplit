package io.github.hanchen.hcmattersplit.data.reward.imp

import io.github.hanchen.hcmattersplit.data.reward.Reward
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class CmdReward : Reward {
    override fun exeReward(player: Player, exeString: String) {
        exeString.split("<->", limit = 2).takeIf { it.size == 2 }?.let { parts ->
            if (parts[0] == "cmd") {
                val command = parts[1].replace("<player>", player.name)
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)
            }
        }
    }
}