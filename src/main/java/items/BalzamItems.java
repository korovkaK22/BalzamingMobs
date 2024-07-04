package items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BalzamItems {

    public static ItemStack getNeedle(){
        ItemStack needle = new ItemStack(Material.BLAZE_ROD,1);
        ItemMeta needleMeta = needle.getItemMeta();
        needleMeta.addEnchant(Enchantment.MENDING,1,false);
        needleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        needleMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Balzam needle"));
        needleMeta.setCustomModelData(1);

        List<String> lores = new ArrayList<>();
        lores.add(ChatColor.translateAlternateColorCodes('&', "&0&dRight click to mob"));
        lores.add(ChatColor.translateAlternateColorCodes('&', "&0&dfor balzaming it"));
        needleMeta.setLore(lores);

        needle.setItemMeta(needleMeta);
        return needle;
    }

    public static ItemStack getEmptyNeedle(){
        ItemStack needle = new ItemStack(Material.GLASS_BOTTLE,1);
        ItemMeta needleMeta = needle.getItemMeta();
        needleMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Empty needle"));
        needleMeta.setCustomModelData(1);
        needleMeta.addEnchant(Enchantment.MENDING,1,false);
        needleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        List<String> lores = new ArrayList<>();
        lores.add(ChatColor.translateAlternateColorCodes('&', "&0&4Right click on the Warden"));
        lores.add(ChatColor.translateAlternateColorCodes('&', "&0&4for reload it"));
        needleMeta.setLore(lores);

        needle.setItemMeta(needleMeta);
        return needle;
    }

    public static ItemStack getWardenNeedle(){
        ItemStack item = new ItemStack(Material.RABBIT_HIDE,1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addEnchant(Enchantment.MENDING,1,false);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Warden amulet"));
        itemMeta.setCustomModelData(1);

        List<String> lores = new ArrayList<>();
        lores.add(ChatColor.translateAlternateColorCodes('&', "&0&1Right click on the Warden"));
        lores.add(ChatColor.translateAlternateColorCodes('&', "&0&1to balzam it"));
        itemMeta.setLore(lores);

        item.setItemMeta(itemMeta);
        return item;
    }

}
