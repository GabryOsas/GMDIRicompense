package me.gabryosas.gmdiricompense.commands.subcommands;

import me.gabryosas.gmdiricompense.GMDIRicompense;
import me.gabryosas.gmdiricompense.commands.SubCommand;
import me.gabryosas.gmdiricompense.utils.Messaggi;
import me.gabryosas.gmdiricompense.utils.RicompenseBuilder;
import org.bukkit.entity.Player;

public class Riscatta extends SubCommand {
    private final GMDIRicompense instance = GMDIRicompense.getInstance();
    @Override
    public String getName() {
        return "riscatta";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 2){
            String id = args[1];
            if (!RicompenseBuilder.checkID(id)){
                player.sendMessage(Messaggi.RICOMPENSA_NON_TROVATA.getMessage().replaceAll("%name%", id));
                return;
            }
            String permission = instance.getConfig().getString("Ricompense." + id + ".Permission");
            if (permission != null && !permission.equalsIgnoreCase("none") && !player.hasPermission(permission)) {
                player.sendMessage(Messaggi.RICOMPENSA_PERMESSI.getMessage());
                return;
            }
            RicompenseBuilder.build(id, player);
            player.sendMessage(Messaggi.RICOMPENSA_RISCATTATA.getMessage().replaceAll("%name%", id));
        }
    }
}
