package io.github.hanchen.hcmattersplit.data.fenjie.imp

import io.github.hanchen.hcmattersplit.data.fenjie.FenJie
import io.github.hanchen.hcmattersplit.data.matching.MatchingManage
import io.github.hanchen.hcmattersplit.data.reward.RewardManage
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class FenJieObject(
    override val fenJieName: String,
    override var fenJieChange: Double,
    override var fenJieMatching: List<String>,
    override var fenJieReward: List<String>
) : FenJie {

    constructor() : this("", 0.0, emptyList(), emptyList())

    override fun exeReward(player: Player) {
        fenJieReward.forEach { rewardString ->
            rewardString.split("<->").takeIf { it.size >= 1 }?.let {
                val rewardType = it[0]
                RewardManage.getReward(rewardType)?.exeReward(player, rewardString)
            }
        }
    }

    override fun isMatching(itemStack: ItemStack): Boolean {
        return fenJieMatching.any { matchingString ->
            matchingString.split("<->").takeIf { it.size >= 1 }?.let {
                val matchingType = it[0]
                MatchingManage.getMatching(matchingType)?.isMatching(itemStack, matchingString) ?: false
            } ?: false
        }
    }
}