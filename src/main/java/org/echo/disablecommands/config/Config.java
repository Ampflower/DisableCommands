package org.echo.disablecommands.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.echo.disablecommands.DisableCommands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Config {

    private final DisableCommands main;
    private File file;
    private final YamlConfiguration yaml;

    public Config(DisableCommands main, String file_name) {
        this.main = main;
        this.yaml = loadConfig(file_name);
        readConfig();
    }

    private YamlConfiguration loadConfig(String file_name) {

        if(!this.main.getDirectory().exists()) {
            this.main.getDirectory().mkdir();
        }

        this.file = new File(this.main.getDataFolder(), file_name);

        if (!this.file.exists()) {
            this.main.saveResource(file_name, false);
        }

        return YamlConfiguration.loadConfiguration(this.file);
    }

    private void readConfig() {
        this.main.isDisableAll = this.yaml.getBoolean("disable_all");
        this.main.disableMessage = this.yaml.getString("disable_message").replace('&', '§');
        this.main.noPermMessage = this.yaml.getString("no_permission_message").replace('&', '§');
        this.main.disableCommands = new LinkedHashSet<>(this.yaml.getStringList("disable_commands"));
        this.main.noExistMessage = this.yaml.getString("no_exist_message").replace('&', '§');
    }

    public void addDisabledCommand(String command) {
        if (!this.main.disableCommands.add(command)) {
            this.yaml.set("disable_commands", new ArrayList<>(this.main.disableCommands));
            saveConfig();
        }
    }

    public void removeDisabledCommand(String command) {
        if (this.main.disableCommands.remove(command)) {
            this.yaml.set("disable_commands", new ArrayList<>(this.main.disableCommands));
            saveConfig();
        }
    }

    private void saveConfig() {
        try {
            this.yaml.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
