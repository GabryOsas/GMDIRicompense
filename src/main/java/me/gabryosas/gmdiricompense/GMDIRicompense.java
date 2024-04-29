package me.gabryosas.gmdiricompense;

import me.gabryosas.gmdiricompense.commands.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GMDIRicompense extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        r();
    }

    @Override
    public void onDisable() {
    }
    private void r(){
        this.getCommand("ricompense").setExecutor(new CommandManager());
    }
    public static GMDIRicompense getInstance(){
        return getPlugin(GMDIRicompense.class);
    }
}
