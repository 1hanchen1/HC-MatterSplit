package io.github.hanchen.hcmattersplit.command

import io.github.hanchen.hcmattersplit.HC_MatterSplit
import io.github.hanchen.hcmattersplit.inventory.openInventory
import taboolib.common.platform.*
import taboolib.common.platform.command.*
import taboolib.common.platform.function.submit
import taboolib.module.lang.sendLang
import kotlin.system.measureTimeMillis

@CommandHeader(
    name = "hcmsp",
    aliases = ["hcfj"],
    permission = "hcmattersplit.use",
    permissionMessage = "command.no-permission"
)
object MainCommand {

    @CommandBody(permission = "hcmattersplit.open")
    val open = subCommand {
        dynamic(optional = true) {
            suggestion<Player>(uncheck = true) { _, _ ->
                listOf("gui", "menu")
            }
            execute<Player> { sender, _, _ ->
                sender.openInventory()
            }
        }
    }

    @CommandBody(permission = "hcmattersplit.reload")
    val reload = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            val timeCost = measureTimeMillis {
                submit(async = true) {
                    HC_MatterSplit.reload()
                }.join() // 等待异步任务完成
            }
            sender.sendLang("command.reload-success", timeCost)
        }
    }

    @CommandBody
    val help = subCommand {
        execute<ProxyCommandSender> { sender, _, _ ->
            sender.sendLang("command.help")
        }
    }

    init {
        registerCommand()
    }

    private fun registerCommand() {
        commandHelper.register()
    }

    object {
        private val commandHelper = commandHelper {
            // 自定义命令帮助格式
            header {
                sender.sendMessage("§6===== HC-MatterSplit Help =====")
            }
            footer {
                sender.sendMessage("§7输入 /hcmsp help 查看详细帮助")
            }
        }
    }
}