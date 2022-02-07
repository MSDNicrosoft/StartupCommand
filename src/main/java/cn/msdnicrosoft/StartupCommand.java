package cn.msdnicrosoft;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

public class StartupCommand extends JavaPlugin {
    public StartupCommand() {
    }

    public void onEnable() {
        this.saveDefaultConfig();
        final List<String> commands = this.getConfig().getStringList("Commands");
        long delay = this.getConfig().getLong("Delay");
        this.getServer().getScheduler().runTaskLater(this, () -> {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

            for (String command : commands) {
                Bukkit.dispatchCommand(console, command);
            }

        }, delay * 20L);
        this.getLogger().info("启动命令已经准备就绪，将在" + delay + "秒后执行" + commands.size() + "条指令");
    }
}
