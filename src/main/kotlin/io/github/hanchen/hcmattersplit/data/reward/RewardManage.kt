package io.github.hanchen.hcmattersplit.data.reward

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.warning
import java.util.concurrent.ConcurrentHashMap

object RewardManage {
    private val rewardMap = ConcurrentHashMap<String, Reward>()

    @Awake(LifeCycle.ENABLE)
    fun registerDefaults() {
        register("cmd", CmdReward())
        // 在此添加其他默认奖励类型
    }

    fun register(type: String, reward: Reward) {
        rewardMap.putIfAbsent(type, reward)
        println("[HC-MatterSplit] §a成功注册奖励类型: $type")
    }

    fun getReward(type: String): Reward? {
        return rewardMap[type].also {
            if (it == null) warning("未找到奖励类型: $type")
        }
    }

    val allRewards: Map<String, Reward>
        get() = rewardMap
}