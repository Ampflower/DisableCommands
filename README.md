# DisableCommands Minecraft Plugin

**Plugin Page:** [Download .jar](https://www.spigotmc.org/resources/disablecommands-1-13-x-1-20-x.110823/)

**Tested Minecraft Versions:** 1.13 - 1.20

**Languages Supported:** Custom

**Donation Link:** [Support us on PayPal](https://www.paypal.com/donate/?hosted_button_id=J4Y27JYWLYLBG)

Join our Discord community for support and updates: [Discord Invite](https://discord.com/invite/Xf3PjwXzKg)

## Description

DisableCommands is a simple yet powerful plugin for Minecraft that allows you to take full control of commands on your server. With this plugin, you can disable chat and list commands of your choice and set specific permissions for each command.

## Features

- Customize the plugin behavior using the "config.yml" configuration file.
- Modify error messages for disabled commands, non-existent commands, and insufficient permissions.
- Easily add or remove commands from the list of disabled commands to suit your specific needs.
- Create a customized gameplay experience for your Minecraft community.

## Commands

- `/disablecommands` (aliases: `/disablecmd`, `/dc`): Displays the plugin's help.
- `/dc add <command>`: Adds a command to the list of disabled commands.
- `/dc remove <command>`: Removes a command from the list of disabled commands.
- `/dc list`: Displays the list of disabled commands.
- `/dc reload`: Reloads the DisableCommands plugin.

## Permissions

- `disablecommands.*`: Grants complete access to the plugin.
- `disablecommands.manage`: Allows adding and removing disabled commands.
- `disablecommands.list`: Allows printing the list of disabled commands.
- `disablecommands.reload`: Allows reloading the DisableCommands plugin.
- `disablecommands.bypass.*`: Allows bypassing all disabled commands.
- `disablecommands.bypass.<command>`: Allows bypassing a specific disabled command.

## Configuration

The `config.yml` file allows you to customize various aspects of the plugin:

```yaml
# --------- Config -----------

# Disable all commands
disable_all: false

# Message for disabled commands
disable_message: '&cThis command is disabled'

# Message for commands that do not exist
no_exist_message: "&cThe command doesn't exist."

# Message for players without permission
# {perm} is replaced by the permission
no_permission_message: "&cYou don't have the permission &e{perm}"

# List of disabled commands
disable_commands:
 - '?'
 - 'plugins'
 - 'pl'
 - 'help'
```
## Get Started

Get Started
With DisableCommands, take full control of commands on your Minecraft server and offer a unique gameplay experience to your players. Customize the plugin to fit your server's needs and create a tailored gaming environment for your community.

Note: The plugin's visuals are in French, but English subtitles are provided for your convenience.

If you encounter any issues or have questions, feel free to reach out to us in English.

Don't forget to like and subscribe to our content if you enjoy it. Thank you for your support!

Happy gaming!
