package crafts;

import items.BalzamItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class BalzamCrafts {

    public BalzamCrafts() {
        recipeEmptyNeedle();
        recipeWardenNeedle();

    }

    //Пустий шприц
    private void recipeEmptyNeedle() {
        ItemStack item = new ItemStack(BalzamItems.getEmptyNeedle());
        ShapedRecipe recipeLight = new ShapedRecipe(NamespacedKey.minecraft("recipe_empty_needle"), item);
        recipeLight.shape("*F*", "B%B", "*B*");

        recipeLight.setIngredient('%', Material.GLASS_BOTTLE);
        recipeLight.setIngredient('B', Material.IRON_INGOT);
        recipeLight.setIngredient('*', Material.STRING);
        recipeLight.setIngredient('F', Material.FEATHER);

        Bukkit.getServer().addRecipe(recipeLight);
    }


    //Шприц на Вардена
    private void recipeWardenNeedle() {
        ItemStack item = new ItemStack(BalzamItems.getWardenNeedle());
        ShapedRecipe recipeLight = new ShapedRecipe(NamespacedKey.minecraft("recipe_warden_needle"), item);
        recipeLight.shape("*F*", "*%*", "*B*");

        recipeLight.setIngredient('%', BalzamItems.getNeedle());
        recipeLight.setIngredient('B', Material.SCULK_SHRIEKER);
        recipeLight.setIngredient('*', Material.SCULK);
        recipeLight.setIngredient('F', Material.SCULK_SENSOR);

        Bukkit.getServer().addRecipe(recipeLight);
    }


}
