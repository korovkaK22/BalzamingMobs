package events;

import balzamingmobs.balzamingmobs.BalzamingMobs;
import items.BalzamItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.text.html.parser.Entity;
import java.util.LinkedList;
import java.util.List;

public class BalzamEvents implements Listener {
    private LinkedList<EntityType> exceptionEntity = new LinkedList<>();


    public BalzamEvents(BalzamingMobs plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        exceptionEntity.addAll(List.of(EntityType.WITHER,
                EntityType.ENDER_DRAGON
        ));
    }


    //Перевірка на шприци та мобів
    @EventHandler
    private void interactMob(PlayerInteractAtEntityEvent event) {

        if (!(event.getRightClicked() instanceof Mob)) {
            return;
        }

        if (badMob(event.getRightClicked().getType())) {
            return;
        }


        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        LivingEntity entity = (LivingEntity) event.getRightClicked();

        if (item.getItemMeta() == null) {
            return;
        }


        //З пустого шприца в зарядженого
        if (entity.getType() == EntityType.WARDEN &&
                item.getItemMeta().equals(BalzamItems.getEmptyNeedle().getItemMeta())) {
            fillNeedle(entity, player);
            return;
        }

        //Бальзамація Вардена
        if (item.getItemMeta().equals(BalzamItems.getWardenNeedle().getItemMeta()) &&
                entity.getType() == EntityType.WARDEN) {
            balzamMob(entity, player);
            return;
        }


        //Бальзамація
        if (item.getItemMeta().equals(BalzamItems.getNeedle().getItemMeta()) &&
                !(entity.getType() == EntityType.WARDEN)) {
            balzamMob(entity, player);
            return;
        }


        //розбальзамація
        if (item.getType() == Material.LAPIS_LAZULI && !entity.hasAI()) {
            unBalzamMob(entity, player);
            return;
        }


    }


    //З пустого шрица зробити заряджений
    private void fillNeedle(LivingEntity entity, Player player) {

        //Якщо варден забальзамований
        if (!entity.hasAI()) {
            player.sendMessage(ChatColor.RED + "You can't do this with balzamed Warden!");
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 0.9f, 1); //Звук реджекта
            return;
        }


        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        item.setAmount(item.getAmount() - 1);
        inventory.addItem(BalzamItems.getNeedle());


        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.9f, ((float) (Math.random() * (1 - 0.8) + 0.8))); //звук ассепта

    }


    //Спробувать розбальзамувать моба
    private void unBalzamMob(LivingEntity entity, Player player) {
        entity.setAI(true);
        player.sendMessage(ChatColor.GREEN + "Mob was unbalzamed!");
        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.9f, ((float) (Math.random() * (1 - 0.7) + 0.7))); //звук ассепта
        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
        entity.setCustomName(null);

        entity.setInvulnerable(false);

        entity.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
    }


    //Спроба забальзамувати моба
    private void balzamMob(LivingEntity entity, Player player) {
        if (!entity.hasAI()) {
            player.sendMessage(ChatColor.RED + "This mob was already balzamed!");
            player.playSound(player, Sound.ENTITY_VILLAGER_NO, 0.9f, 1); //Звук реджекта
            return;
        }

        entity.setAI(!entity.hasAI());
        setName(entity);

        entity.setInvulnerable(true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 100000));

        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

        player.sendMessage(ChatColor.GREEN + "Mob was balzamed!");
        player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.9f, ((float) (Math.random() * (1 - 0.7) + 0.7))); //звук ассепта
    }


    //Дати ім'я бальзамованим
    private void setName(LivingEntity entity) {
        boolean hasAi = (entity.hasAI());
        if (entity.getCustomName() == null) {
            entity.setCustomName(!hasAi ? ChatColor.GREEN + "Balzamed" : null);
        } else {
            entity.setCustomName(!hasAi ? ChatColor.GREEN + entity.getCustomName() : null);
        }
    }


    //Перевірка на антимоба
    private boolean badMob(EntityType inputType) {

        for (EntityType entity : exceptionEntity) {
            if (entity == inputType) {
                return true;
            }
        }
        return false;
    }


}
