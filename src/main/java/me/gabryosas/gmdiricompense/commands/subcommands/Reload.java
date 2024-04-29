package me.gabryosas.gmdiricompense.commands.subcommands;

import me.gabryosas.gmdiricompense.GMDIRicompense;
import me.gabryosas.gmdiricompense.commands.CommandManager;
import me.gabryosas.gmdiricompense.commands.SubCommand;
import me.gabryosas.gmdiricompense.utils.Messaggi;
import org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Reload extends SubCommand {
    private final GMDIRicompense instance = GMDIRicompense.getInstance();
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1){
            if (!player.hasPermission("ricompensa.admin")){
                player.sendMessage(Messaggi.RICOMPENSA_PERMESSI.getMessage());
                return;
            }
            reload();
            player.sendMessage(Messaggi.RICOMPENSA_RELOAD.getMessage());
        }
    }
    private void reload(){
        try {
            File originalConfig = new File(instance.getDataFolder(), "config.yml");
            File backupConfig = new File(instance.getDataFolder(), "config_backup.yml");
            FileUtils.copyFile(originalConfig, backupConfig);
            FileUtils.copyFile(backupConfig, originalConfig);
            instance.reloadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
