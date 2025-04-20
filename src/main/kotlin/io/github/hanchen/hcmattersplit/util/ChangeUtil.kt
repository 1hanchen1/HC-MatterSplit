package io.github.hanchen.hcmattersplit.util

object ChangeUtil {
    fun check(probability: Double): Boolean {
        return Math.random() < probability
    }
}