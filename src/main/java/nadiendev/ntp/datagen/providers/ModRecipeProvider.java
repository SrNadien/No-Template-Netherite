package nadiendev.ntp.datagen.providers;

import com.google.gson.JsonObject;
import com.thevortex.allthemodium.registry.ModRegistry;
import com.thevortex.allthemodium.registry.TagRegistry;
import nadiendev.ntp.NoNetheriteTemplate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

          // Wrapper para evitar generar advancements automáticos
        Consumer<FinishedRecipe> recipeConsumer = new Consumer<FinishedRecipe>() {
            @Override
            public void accept(FinishedRecipe recipe) {
                // Crear una versión sin advancement
                FinishedRecipe wrappedRecipe = new FinishedRecipe() {
                    @Override
                    public void serializeRecipeData(JsonObject json) {
                        recipe.serializeRecipeData(json);
                    }

                    @Override
                    public ResourceLocation getId() {
                        return recipe.getId();
                    }

                    @Override
                    public RecipeSerializer<?> getType() {
                        return recipe.getType();
                    }

                    @Override
                    public JsonObject serializeAdvancement() {
                        return null; // NO generar advancement
                    }

                    @Override
                    public ResourceLocation getAdvancementId() {
                        return null; // NO generar advancement
                    }
                };
                consumer.accept(wrappedRecipe);
            }
        };

        // ==========================================
        // NETHERITE — sin condición
        // ==========================================

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1)
            .pattern("aba").pattern("aca").pattern("aaa")
            .define('a', Items.OBSIDIAN).define('b', Items.DIAMOND).define('c', Items.ENDER_EYE)
            .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
            .save(recipeConsumer, rl("netherite_upgrade_smithing_template"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_HELMET, 1)
            .pattern("aaa").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_helmet"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_CHESTPLATE, 1)
            .pattern("a a").pattern("aaa").pattern("aaa")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_LEGGINGS, 1)
            .pattern("aaa").pattern("a a").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_BOOTS, 1)
            .pattern("a a").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_boots"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_PICKAXE, 1)
            .pattern("aaa").pattern(" b ").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_pickaxe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
            .pattern("aa").pattern("ab").pattern(" b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
            .pattern(" aa").pattern(" ba").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_axe_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_SHOVEL, 1)
            .pattern("a").pattern("b").pattern("b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_shovel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
            .pattern("aa").pattern(" b").pattern(" b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_hoe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
            .pattern(" aa").pattern(" b ").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_hoe_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_SWORD, 1)
            .pattern("a").pattern("a").pattern("b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(recipeConsumer, rl("netherite_sword"));

        // ==========================================
        // ALLTHEMODIUM — condición forge:mod_loaded
        // ==========================================

        saveAtm(consumer, "allthemodium_pickaxe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.ALLTHEMODIUM_PICKAXE.get(), 1)
                .pattern("ara").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD)
                .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE)));

        saveAtm(consumer, "allthemodium_axe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.ALLTHEMODIUM_AXE.get(), 1)
                .pattern("aa").pattern("ar").pattern(" r")
                .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD)
                .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE)));

        saveAtm(consumer, "allthemodium_shovel",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.ALLTHEMODIUM_SHOVEL.get(), 1)
                .pattern(" a ").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD)
                .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE)));

        saveAtm(consumer, "allthemodium_hoe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.ALLTHEMODIUM_HOE.get(), 1)
                .pattern("aa ").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD)
                .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE)));

        saveAtm(consumer, "allthemodium_sword",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_SWORD.get(), 1)
                .pattern(" a ").pattern(" a ").pattern(" r ")
                .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD)
                .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE)));

        saveAtm(consumer, "allthemodium_helmet",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_HELMET.get(), 1)
                .pattern("aaa").pattern("ana")
                .define('a', TagRegistry.ALLTHEMODIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_allthemodium_ingot", has(TagRegistry.ALLTHEMODIUM_INGOT)));

        saveAtm(consumer, "allthemodium_chestplate",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_CHESTPLATE.get(), 1)
                .pattern("a a").pattern("ana").pattern("aaa")
                .define('a', TagRegistry.ALLTHEMODIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_allthemodium_ingot", has(TagRegistry.ALLTHEMODIUM_INGOT)));

        saveAtm(consumer, "allthemodium_leggings",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_LEGGINGS.get(), 1)
                .pattern("aaa").pattern("ana").pattern("a a")
                .define('a', TagRegistry.ALLTHEMODIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_allthemodium_ingot", has(TagRegistry.ALLTHEMODIUM_INGOT)));

        saveAtm(consumer, "allthemodium_boots",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_BOOTS.get(), 1)
                .pattern("a a").pattern("ana")
                .define('a', TagRegistry.ALLTHEMODIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_allthemodium_ingot", has(TagRegistry.ALLTHEMODIUM_INGOT)));

        // ==========================================
        // VIBRANIUM — condición forge:mod_loaded
        // ==========================================

        saveAtm(consumer, "vibranium_pickaxe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.VIBRANIUM_PICKAXE.get(), 1)
                .pattern("ara").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD)
                .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE)));

        saveAtm(consumer, "vibranium_axe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.VIBRANIUM_AXE.get(), 1)
                .pattern("aa").pattern("ar").pattern(" r")
                .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD)
                .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE)));

        saveAtm(consumer, "vibranium_shovel",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.VIBRANIUM_SHOVEL.get(), 1)
                .pattern(" a ").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD)
                .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE)));

        saveAtm(consumer, "vibranium_hoe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.VIBRANIUM_HOE.get(), 1)
                .pattern("aa ").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD)
                .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE)));

        saveAtm(consumer, "vibranium_sword",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_SWORD.get(), 1)
                .pattern(" a ").pattern(" a ").pattern(" r ")
                .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD)
                .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE)));

        saveAtm(consumer, "vibranium_helmet",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_HELMET.get(), 1)
                .pattern("aaa").pattern("ana")
                .define('a', TagRegistry.VIBRANIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_vibranium_ingot", has(TagRegistry.VIBRANIUM_INGOT)));

        saveAtm(consumer, "vibranium_chestplate",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_CHESTPLATE.get(), 1)
                .pattern("a a").pattern("ana").pattern("aaa")
                .define('a', TagRegistry.VIBRANIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_vibranium_ingot", has(TagRegistry.VIBRANIUM_INGOT)));

        saveAtm(consumer, "vibranium_leggings",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_LEGGINGS.get(), 1)
                .pattern("aaa").pattern("ana").pattern("a a")
                .define('a', TagRegistry.VIBRANIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_vibranium_ingot", has(TagRegistry.VIBRANIUM_INGOT)));

        saveAtm(consumer, "vibranium_boots",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_BOOTS.get(), 1)
                .pattern("a a").pattern("ana")
                .define('a', TagRegistry.VIBRANIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_vibranium_ingot", has(TagRegistry.VIBRANIUM_INGOT)));

        // ==========================================
        // UNOBTAINIUM — condición forge:mod_loaded
        // ==========================================

        saveAtm(consumer, "unobtainium_pickaxe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.UNOBTAINIUM_PICKAXE.get(), 1)
                .pattern("ara").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD)
                .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE)));

        saveAtm(consumer, "unobtainium_axe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.UNOBTAINIUM_AXE.get(), 1)
                .pattern("aa").pattern("ar").pattern(" r")
                .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD)
                .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE)));

        saveAtm(consumer, "unobtainium_shovel",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.UNOBTAINIUM_SHOVEL.get(), 1)
                .pattern(" a ").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD)
                .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE)));

        saveAtm(consumer, "unobtainium_hoe",
            ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.UNOBTAINIUM_HOE.get(), 1)
                .pattern("aa ").pattern(" r ").pattern(" r ")
                .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD)
                .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE)));

        saveAtm(consumer, "unobtainium_sword",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_SWORD.get(), 1)
                .pattern(" a ").pattern(" a ").pattern(" r ")
                .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD)
                .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE)));

        saveAtm(consumer, "unobtainium_helmet",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_HELMET.get(), 1)
                .pattern("aaa").pattern("ana")
                .define('a', TagRegistry.UNOBTAINIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_unobtainium_ingot", has(TagRegistry.UNOBTAINIUM_INGOT)));

        saveAtm(consumer, "unobtainium_chestplate",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_CHESTPLATE.get(), 1)
                .pattern("a a").pattern("ana").pattern("aaa")
                .define('a', TagRegistry.UNOBTAINIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_unobtainium_ingot", has(TagRegistry.UNOBTAINIUM_INGOT)));

        saveAtm(consumer, "unobtainium_leggings",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_LEGGINGS.get(), 1)
                .pattern("aaa").pattern("ana").pattern("a a")
                .define('a', TagRegistry.UNOBTAINIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_unobtainium_ingot", has(TagRegistry.UNOBTAINIUM_INGOT)));

        saveAtm(consumer, "unobtainium_boots",
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_BOOTS.get(), 1)
                .pattern("a a").pattern("ana")
                .define('a', TagRegistry.UNOBTAINIUM_INGOT).define('n', Items.NETHERITE_INGOT)
                .unlockedBy("has_unobtainium_ingot", has(TagRegistry.UNOBTAINIUM_INGOT)));
    }

    // =============================================
    // HELPERS
    // =============================================

    private static ResourceLocation rl(String path) {
        return new ResourceLocation(NoNetheriteTemplate.MODID, path);
    }

    
    private void saveAtm(Consumer<FinishedRecipe> writer, String name, RecipeBuilder builder) {
        ConditionalRecipe.builder()
            .addCondition(new ModLoadedCondition("allthemodium"))
            .addRecipe(w -> builder.save(w, rl(name)))
            .build(writer, rl(name));
    }
}