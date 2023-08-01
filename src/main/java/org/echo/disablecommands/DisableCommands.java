package org.echo.disablecommands;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.plugin.java.JavaPlugin;
import org.echo.disablecommands.command.Commands;
import org.echo.disablecommands.config.Config;
import org.echo.disablecommands.listener.CommandsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class DisableCommands extends JavaPlugin {

    private Config config;

    public boolean isDisableAll= false;
    public String disableMessage;
    public String noPermMessage;
    public String noExistMessage;
    public List<String> disableCommands = new ArrayList<String>();

    @Override
    public void onEnable() {

        this.config = new Config(this, "config.yml");

        getServer().getPluginManager().registerEvents(new CommandsListener(this), this);
        getCommand("disablecommands").setExecutor(new Commands(this));
        getCommand("dc").setExecutor(new Commands(this));
        getCommand("disablecmd").setExecutor(new Commands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public File getDirectory() {
        return getDataFolder();
    }

    public Config getMyConfig() {
        return this.config;
    }

    public void Reload() {
        this.config = new Config(this, "config.yml");
    }
}
