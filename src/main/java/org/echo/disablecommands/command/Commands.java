package org.echo.disablecommands.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.echo.disablecommands.DisableCommands;

import java.util.List;

public class Commands implements CommandExecutor {

    private DisableCommands main;

    public Commands(DisableCommands main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            // Gérer la commande /disablecommands sans argument
            return handleDisableCommands(sender);
        } else if (args.length == 1) {
            String subCommand = args[0];
            if (subCommand.equalsIgnoreCase("list")) {
                // Gérer la sous-commande /disablecommands list
                return handleListDisabledCommands(sender);
            } else if (subCommand.equalsIgnoreCase("reload")) {
                // Gérer la sous-commande /disablecommands reload
                return handleReloadPlugin(sender);
            }
        } else if (args.length >= 2) {
            String action = args[0];
            String targetCommand = args[1];

            if (action.equalsIgnoreCase("add")) {
                // Gérer la commande /disablecommands add <command>
                return handleAddDisabledCommand(sender, targetCommand);
            } else if (action.equalsIgnoreCase("remove")) {
                // Gérer la commande /disablecommands remove <command>
                return handleRemoveDisabledCommand(sender, targetCommand);
            }
        }
        return handleDisableCommands(sender);
    }

    private boolean handleDisableCommands(CommandSender sender) {

        String permission = "disablecommands.manage";
        if (!sender.hasPermission(permission)) {
            if (!this.main.noPermMessage.isEmpty())
                sender.sendMessage(this.main.noPermMessage.replace("{perm}", permission));
            return false;
        }
        // Explanation of the usage
        sender.sendMessage(ChatColor.GOLD + "[DisableCommands]");
        sender.sendMessage(ChatColor.WHITE + "Usage: " + ChatColor.YELLOW + "/dc [add|remove] <command>");
        sender.sendMessage(ChatColor.WHITE + " - " + ChatColor.YELLOW +  "/dc add <command>" + ChatColor.WHITE + " : Adds a command to the disabled list");
        sender.sendMessage(ChatColor.WHITE + " - " + ChatColor.YELLOW +  "/dc remove <command>" + ChatColor.WHITE + " : Removes a command from the disabled list");
        sender.sendMessage(ChatColor.WHITE + " - " + ChatColor.YELLOW +  "/dc list" + ChatColor.WHITE + " : Print list of disabled commands");
        sender.sendMessage(ChatColor.WHITE + " - " + ChatColor.YELLOW +  "/dc reload" + ChatColor.WHITE + " : Reload DisableCommands plugin");
        return true;
    }

    private boolean handleListDisabledCommands(CommandSender sender) {

        String permission = "disablecommands.list";
        if (!sender.hasPermission(permission)) {
            if (!this.main.noPermMessage.isEmpty())
                sender.sendMessage(this.main.noPermMessage.replace("{perm}", permission));
            return false;
        }

        List<String> disabledCommands = this.main.disableCommands;
        sender.sendMessage(ChatColor.YELLOW + "List of Disabled Commands:");
        String commands = "";

        for (int i = 0; i < disabledCommands.size(); i++) {
            if (i != 0)
                commands = commands + ", " + disabledCommands.get(i);
            else
                commands = disabledCommands.get(i);
        }
        sender.sendMessage(ChatColor.RED + commands);
        return true;
    }

    private boolean handleReloadPlugin(CommandSender sender) {

        String permission = "disablecommands.reload";
        if (!sender.hasPermission(permission)) {
            if (!this.main.noPermMessage.isEmpty())
                sender.sendMessage(this.main.noPermMessage.replace("{perm}", permission));
            return false;
        }

        this.main.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "[DisableCommands] Reload success");
        return true;
    }

    private boolean handleAddDisabledCommand(CommandSender sender, String targetCommand) {

        String permission = "disablecommands.manage";
        if (!sender.hasPermission(permission)) {
            if (!this.main.noPermMessage.isEmpty())
                sender.sendMessage(this.main.noPermMessage.replace("{perm}", permission));
            return false;
        }

        this.main.getMyConfig().addDisabledCommand(targetCommand);
        sender.sendMessage(ChatColor.YELLOW + "The command " + ChatColor.GREEN + targetCommand + ChatColor.YELLOW + " has been added.");
        return true;
    }

    private boolean handleRemoveDisabledCommand(CommandSender sender, String targetCommand) {

        String permission = "disablecommands.manage";
        if (!sender.hasPermission(permission)) {
            if (!this.main.noPermMessage.isEmpty())
                sender.sendMessage(this.main.noPermMessage.replace("{perm}", permission));
            return false;
        }

        this.main.getMyConfig().removeDisabledCommand(targetCommand);
        sender.sendMessage(ChatColor.YELLOW + "The command " + ChatColor.RED + targetCommand + ChatColor.YELLOW + " has been removed.");
        return true;
    }
}
