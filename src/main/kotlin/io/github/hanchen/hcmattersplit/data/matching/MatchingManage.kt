package io.github.hanchen.hcmattersplit.data.matching

import io.github.hanchen.hcmattersplit.data.matching.imp.ContainsLore
import io.github.hanchen.hcmattersplit.data.matching.imp.EqualsName
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.warning
import java.util.concurrent.ConcurrentHashMap

object MatchingManage {
    private val matchingMap = ConcurrentHashMap<String, Matching>()

    @Awake(LifeCycle.ENABLE)
    fun registerDefaults() {
        register("containsLore", ContainsLore())
        register("equalsName", EqualsName())
    }

    fun register(type: String, matching: Matching) {
        matchingMap.putIfAbsent(type, matching)
        println("[HC-MatterSplit] §a成功注册匹配类型: $type")
    }

    fun getMatching(type: String): Matching? {
        return matchingMap[type].also {
            if (it == null) warning("未找到匹配类型: $type")
        }
    }

    val allMatchings: Map<String, Matching>
        get() = matchingMap
}