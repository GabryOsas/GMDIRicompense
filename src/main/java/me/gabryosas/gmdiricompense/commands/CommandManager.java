package me.gabryosas.gmdiricompense.commands;

import me.gabryosas.gmdiricompense.GMDIRicompense;
import me.gabryosas.gmdiricompense.commands.subcommands.Reload;
import me.gabryosas.gmdiricompense.commands.subcommands.Riscatta;
import me.gabryosas.gmdiricompense.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subcommands = new ArrayList<>();
    private final GMDIRicompense instance = GMDIRicompense.getInstance();

    public CommandManager(){
        subcommands.add(new Riscatta());
        subcommands.add(new Reload());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (args.length > 0){
            for (int i = 0; i < getSubcommands().size(); i++){
                if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                    getSubcommands().get(i).perform(player, args);
                }
            }
        }else {
            player.sendMessage(help());
        }
        return true;
    }

    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }
    public String help() {
        List<String> messageList = instance.getConfig().getStringList("Messaggi.Help-Message");
        return Color.translateHexColorCodes(String.join("\n", messageList));
    }
}

