package me.gabryosas.gmdiricompense.commands;

import org.bukkit.entity.Player;

public abstract class SubCommand {
    public abstract String getName();

    public abstract void perform(Player player, String args[]);

}
