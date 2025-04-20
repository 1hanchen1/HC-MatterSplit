package io.github.hanchen.hcmattersplit.data.fenjie

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import java.util.concurrent.ConcurrentHashMap

object FenJieManage {
    val fenJieMap = ConcurrentHashMap<String, FenJie>()

    @Awake(LifeCycle.ENABLE)
    fun registerDefaults() {
        // 在此处添加默认配置加载逻辑
    }

    fun register(fenJieName: String, fenJie: FenJie) {
        fenJieMap.putIfAbsent(fenJieName, fenJie)
        println("[HC-MatterSplit] §a成功注册分解配置: $fenJieName")
    }

    val allFenJie: Map<String, FenJie>
        get() = fenJieMap
}