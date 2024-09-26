package org.echo.disablecommands.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.echo.disablecommands.DisableCommands;

public class CommandsListener implements Listener {
    private final DisableCommands main;
    public CommandsListener(DisableCommands main) {
        this.main = main;
    }

    @EventHandler
    public void onList(PlayerCommandSendEvent event) {

        // Commande interdite
        if (event.getPlayer().hasPermission("disablecommands.bypass.*")) {
            return;
        }

        event.getCommands().removeIf(str -> str.contains(":"));

        if (this.main.isDisableAll) {
            event.getCommands().removeIf(str -> event.getPlayer().hasPermission("disablecommands.bypass." + str));
            return;
        }

        for (String command : this.main.disableCommands) {
            if (!event.getPlayer().hasPermission("disablecommands.bypass." + command)) {
                event.getCommands().remove(command);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("disablecommands.bypass.*")) {
            return;
        }

        String command = event.getMessage().split(" ")[0].substring(1);

        // If the command isn't disabled, don't go for the more expensive permission check.
        if (!this.main.isDisableAll && !this.main.disableCommands.contains(command)) {
            return;
        }

        // Command bypass
        if (player.hasPermission("disablecommands.bypass." + command)) {
            return;
        }

        event.setCancelled(true);
        player.sendMessage(this.main.disableMessage);
    }
}
