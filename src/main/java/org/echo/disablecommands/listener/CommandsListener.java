package org.echo.disablecommands.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.echo.disablecommands.DisableCommands;

import java.util.Iterator;

public class CommandsListener implements Listener {
    private DisableCommands main;
    public CommandsListener(DisableCommands main) {
        this.main = main;
    }

    @EventHandler
    public void onList(PlayerCommandSendEvent event) {

        // Commande interdite
        if (!event.getPlayer().hasPermission("disablecommands.bypass.*")) {

            if (this.main.isDisableAll) {
                event.getCommands().clear();
            }
            else {
                Iterator<String> it = event.getCommands().iterator();
                String str;

                while (it.hasNext()) {
                    str = (String) it.next();
                    if (str.contains(":")) {
                        it.remove();
                    }
                }

                for (int i = 0; i < this.main.disableCommands.size(); i++) {
                    if (!event.getPlayer().hasPermission("disablecommands.bypass." + this.main.disableCommands.get(i)))
                        event.getCommands().remove(this.main.disableCommands.get(i));
                }
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("disablecommands.bypass.*")) {

            if (this.main.isDisableAll) {
                event.setCancelled(true);
                player.sendMessage(this.main.disableMessage);
                return ;
            }
            else {
                String[] args = event.getMessage().split(" ");

                // Commande inexistante
                if(Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
                    event.setCancelled(true);
                    player.sendMessage(this.main.noExistMessage);
                    return ;
                }

                // Commande interdite
                for (int i = 0; i < this.main.disableCommands.size(); i++) {
                    if (!event.getPlayer().hasPermission("disablecommands.bypass." + this.main.disableCommands.get(i))) {
                        if (args[0].equalsIgnoreCase("/" + this.main.disableCommands.get(i))) {
                            event.setCancelled(true);
                            player.sendMessage(this.main.disableMessage);
                            return ;
                        }
                    }
                }
            }
        }
    }
}
