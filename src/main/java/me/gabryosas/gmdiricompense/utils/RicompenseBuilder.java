package me.gabryosas.gmdiricompense.utils;

import me.gabryosas.gmdiricompense.GMDIRicompense;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;
import java.util.Set;

public class RicompenseBuilder {
    private static final GMDIRicompense instance = GMDIRicompense.getInstance();
    public static void build(String id, Player player) {
        ConfigurationSection ricompensaConfig = instance.getConfig().getConfigurationSection("Ricompense." + id);
        if (ricompensaConfig == null) return;
        Set<String> keys = ricompensaConfig.getKeys(false);
        for (String key : keys) {
            ConfigurationSection itemConfig = ricompensaConfig.getConfigurationSection(key);
            if (itemConfig == null) continue;
            if (itemConfig.contains("command")) {
                String command = itemConfig.getString("command");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
            } else {
                ItemStack item = buildI(itemConfig);
                if (item != null) {
                    player.getInventory().addItem(item);
                }
            }
        }
    }
    private static ItemStack buildI(ConfigurationSection itemConfig) {
        if (!itemConfig.contains("Material")) {
            return null;
        }
        Material material = Material.valueOf((String) itemConfig.get("Material"));
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (itemConfig.contains("Name")) {
            meta.setDisplayName(Color.translateHexColorCodes((String) itemConfig.get("Name")));
        }
        if (itemConfig.contains("Amount")) {
            item.setAmount((int) itemConfig.get("Amount"));
        }
        if (itemConfig.contains("Lore")) {
            List<String> loreList = (List<String>) itemConfig.get("Lore");
            for (int i = 0; i < loreList.size(); i++) {
                loreList.set(i, Color.translateHexColorCodes(loreList.get(i)));
            }
            meta.setLore(loreList);
        }
        if (itemConfig.contains("Flags")) {
            List<String> flags = (List<String>) itemConfig.get("Flags");
            for (String flag : flags) {
                meta.addItemFlags(ItemFlag.valueOf(flag));
            }
        }
        if (itemConfig.contains("Unbreakable")) {
            boolean unbreakable = (boolean) itemConfig.get("Unbreakable");
            meta.setUnbreakable(unbreakable);
        }
        if (iL(material.name()) && itemConfig.contains("Color")) {
            LeatherArmorMeta leatherMeta = (LeatherArmorMeta) meta;
            String[] rgb = ((String) itemConfig.get("Color")).split(" ");
            int red = Integer.parseInt(rgb[0]);
            int green = Integer.parseInt(rgb[1]);
            int blue = Integer.parseInt(rgb[2]);
            org.bukkit.Color color = org.bukkit.Color.fromRGB(red, green, blue);
            leatherMeta.setColor(color);
            item.setItemMeta(leatherMeta);
        } else {
            item.setItemMeta(meta);
        }
        if (itemConfig.contains("Model-Data")) {
            int modelData = (int) itemConfig.get("Model-Data");
            meta.setCustomModelData(modelData);
        }
        return item;
    }
    private static boolean iL(String name){
        return name.toUpperCase().startsWith("LEATHER_");
    }
    public static boolean checkID(String id){
        return instance.getConfig().contains("Ricompense." + id);
    }
}