package nadiendev.ntp.datagen.providers;

import net.allthemods.allthemodium.core.registry.ATMItems;
import net.allthemods.allthemodium.core.registry.ATMTags;
import nadiendev.ntp.NoNetheriteTemplate;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    private final HolderGetter<Item> items;

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.items = registries.lookupOrThrow(Registries.ITEM);
    }

    @Override
    protected void buildRecipes() {

        // base → recetas normales sin advancements
        // atm/vib/uno → solo cargan si allthemodium está instalado
        RecipeOutput base = noAdvancement(output);
        RecipeOutput atm  = noAdvancement(output.withConditions(new ModLoadedCondition("allthemodium")));
        RecipeOutput vib  = noAdvancement(output.withConditions(new ModLoadedCondition("allthemodium")));
        RecipeOutput uno  = noAdvancement(output.withConditions(new ModLoadedCondition("allthemodium")));

        // ==========================================
        // NETHERITE
        // ==========================================

        ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1)
            .pattern("aba").pattern("aca").pattern("aaa")
            .define('a', Items.OBSIDIAN).define('b', Items.DIAMOND).define('c', Items.ENDER_EYE)
            .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
            .save(base, rk("netherite_upgrade_smithing_template"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_HELMET, 1)
            .pattern("aaa").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_helmet"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_CHESTPLATE, 1)
            .pattern("a a").pattern("aaa").pattern("aaa")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_chestplate"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_LEGGINGS, 1)
            .pattern("aaa").pattern("a a").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_leggings"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_BOOTS, 1)
            .pattern("a a").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_boots"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_PICKAXE, 1)
            .pattern("aaa").pattern(" b ").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_pickaxe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
            .pattern("aa").pattern("ab").pattern(" b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_axe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
            .pattern(" aa").pattern(" ba").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_axe_alt"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_SHOVEL, 1)
            .pattern("a").pattern("b").pattern("b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_shovel"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
            .pattern("aa").pattern(" b").pattern(" b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_hoe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
            .pattern(" aa").pattern(" b ").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_hoe_alt"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_SWORD, 1)
            .pattern("a").pattern("a").pattern("b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rk("netherite_sword"));

        // ==========================================
        // ALLTHEMODIUM 
        // ==========================================

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.ALLTHEMODIUM_PICKAXE.get(), 1)
            .pattern("ara").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_ALLTHEMODIUM).define('r', ATMTags.Items.RODS_ALLTHEMODIUM)
            .unlockedBy("has_allthemodium_plate", has(ATMTags.Items.PLATES_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_pickaxe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.ALLTHEMODIUM_AXE.get(), 1)
            .pattern("aa").pattern("ar").pattern(" r")
            .define('a', ATMTags.Items.PLATES_ALLTHEMODIUM).define('r', ATMTags.Items.RODS_ALLTHEMODIUM)
            .unlockedBy("has_allthemodium_plate", has(ATMTags.Items.PLATES_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_axe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.ALLTHEMODIUM_SHOVEL.get(), 1)
            .pattern(" a ").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_ALLTHEMODIUM).define('r', ATMTags.Items.RODS_ALLTHEMODIUM)
            .unlockedBy("has_allthemodium_plate", has(ATMTags.Items.PLATES_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_shovel"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.ALLTHEMODIUM_HOE.get(), 1)
            .pattern("aa ").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_ALLTHEMODIUM).define('r', ATMTags.Items.RODS_ALLTHEMODIUM)
            .unlockedBy("has_allthemodium_plate", has(ATMTags.Items.PLATES_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_hoe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.ALLTHEMODIUM_SWORD.get(), 1)
            .pattern(" a ").pattern(" a ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_ALLTHEMODIUM).define('r', ATMTags.Items.RODS_ALLTHEMODIUM)
            .unlockedBy("has_allthemodium_plate", has(ATMTags.Items.PLATES_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_sword"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.ALLTHEMODIUM_HELMET.get(), 1)
            .pattern("aaa").pattern("ana")
            .define('a', ATMTags.Items.INGOTS_ALLTHEMODIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_allthemodium_ingot", has(ATMTags.Items.INGOTS_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_helmet"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.ALLTHEMODIUM_CHESTPLATE.get(), 1)
            .pattern("a a").pattern("ana").pattern("aaa")
            .define('a', ATMTags.Items.INGOTS_ALLTHEMODIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_allthemodium_ingot", has(ATMTags.Items.INGOTS_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_chestplate"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.ALLTHEMODIUM_LEGGINGS.get(), 1)
            .pattern("aaa").pattern("ana").pattern("a a")
            .define('a', ATMTags.Items.INGOTS_ALLTHEMODIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_allthemodium_ingot", has(ATMTags.Items.INGOTS_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_leggings"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.ALLTHEMODIUM_BOOTS.get(), 1)
            .pattern("a a").pattern("ana")
            .define('a', ATMTags.Items.INGOTS_ALLTHEMODIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_allthemodium_ingot", has(ATMTags.Items.INGOTS_ALLTHEMODIUM))
            .save(atm, rk("allthemodium_boots"));

        // ==========================================
        // VIBRANIUM
        // ==========================================

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.VIBRANIUM_PICKAXE.get(), 1)
            .pattern("ara").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_VIBRANIUM).define('r', ATMTags.Items.RODS_VIBRANIUM)
            .unlockedBy("has_vibranium_plate", has(ATMTags.Items.PLATES_VIBRANIUM))
            .save(vib, rk("vibranium_pickaxe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.VIBRANIUM_AXE.get(), 1)
            .pattern("aa").pattern("ar").pattern(" r")
            .define('a', ATMTags.Items.PLATES_VIBRANIUM).define('r', ATMTags.Items.RODS_VIBRANIUM)
            .unlockedBy("has_vibranium_plate", has(ATMTags.Items.PLATES_VIBRANIUM))
            .save(vib, rk("vibranium_axe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.VIBRANIUM_SHOVEL.get(), 1)
            .pattern(" a ").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_VIBRANIUM).define('r', ATMTags.Items.RODS_VIBRANIUM)
            .unlockedBy("has_vibranium_plate", has(ATMTags.Items.PLATES_VIBRANIUM))
            .save(vib, rk("vibranium_shovel"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.VIBRANIUM_HOE.get(), 1)
            .pattern("aa ").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_VIBRANIUM).define('r', ATMTags.Items.RODS_VIBRANIUM)
            .unlockedBy("has_vibranium_plate", has(ATMTags.Items.PLATES_VIBRANIUM))
            .save(vib, rk("vibranium_hoe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.VIBRANIUM_SWORD.get(), 1)
            .pattern(" a ").pattern(" a ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_VIBRANIUM).define('r', ATMTags.Items.RODS_VIBRANIUM)
            .unlockedBy("has_vibranium_plate", has(ATMTags.Items.PLATES_VIBRANIUM))
            .save(vib, rk("vibranium_sword"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.VIBRANIUM_HELMET.get(), 1)
            .pattern("aaa").pattern("ana")
            .define('a', ATMTags.Items.INGOTS_VIBRANIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_vibranium_ingot", has(ATMTags.Items.INGOTS_VIBRANIUM))
            .save(vib, rk("vibranium_helmet"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.VIBRANIUM_CHESTPLATE.get(), 1)
            .pattern("a a").pattern("ana").pattern("aaa")
            .define('a', ATMTags.Items.INGOTS_VIBRANIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_vibranium_ingot", has(ATMTags.Items.INGOTS_VIBRANIUM))
            .save(vib, rk("vibranium_chestplate"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.VIBRANIUM_LEGGINGS.get(), 1)
            .pattern("aaa").pattern("ana").pattern("a a")
            .define('a', ATMTags.Items.INGOTS_VIBRANIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_vibranium_ingot", has(ATMTags.Items.INGOTS_VIBRANIUM))
            .save(vib, rk("vibranium_leggings"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.VIBRANIUM_BOOTS.get(), 1)
            .pattern("a a").pattern("ana")
            .define('a', ATMTags.Items.INGOTS_VIBRANIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_vibranium_ingot", has(ATMTags.Items.INGOTS_VIBRANIUM))
            .save(vib, rk("vibranium_boots"));

        // ==========================================
        // UNOBTAINIUM 
        // ==========================================

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.UNOBTAINIUM_PICKAXE.get(), 1)
            .pattern("ara").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_UNOBTAINIUM).define('r', ATMTags.Items.RODS_UNOBTAINIUM)
            .unlockedBy("has_unobtainium_plate", has(ATMTags.Items.PLATES_UNOBTAINIUM))
            .save(uno, rk("unobtainium_pickaxe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.UNOBTAINIUM_AXE.get(), 1)
            .pattern("aa").pattern("ar").pattern(" r")
            .define('a', ATMTags.Items.PLATES_UNOBTAINIUM).define('r', ATMTags.Items.RODS_UNOBTAINIUM)
            .unlockedBy("has_unobtainium_plate", has(ATMTags.Items.PLATES_UNOBTAINIUM))
            .save(uno, rk("unobtainium_axe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.UNOBTAINIUM_SHOVEL.get(), 1)
            .pattern(" a ").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_UNOBTAINIUM).define('r', ATMTags.Items.RODS_UNOBTAINIUM)
            .unlockedBy("has_unobtainium_plate", has(ATMTags.Items.PLATES_UNOBTAINIUM))
            .save(uno, rk("unobtainium_shovel"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, ATMItems.UNOBTAINIUM_HOE.get(), 1)
            .pattern("aa ").pattern(" r ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_UNOBTAINIUM).define('r', ATMTags.Items.RODS_UNOBTAINIUM)
            .unlockedBy("has_unobtainium_plate", has(ATMTags.Items.PLATES_UNOBTAINIUM))
            .save(uno, rk("unobtainium_hoe"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.UNOBTAINIUM_SWORD.get(), 1)
            .pattern(" a ").pattern(" a ").pattern(" r ")
            .define('a', ATMTags.Items.PLATES_UNOBTAINIUM).define('r', ATMTags.Items.RODS_UNOBTAINIUM)
            .unlockedBy("has_unobtainium_plate", has(ATMTags.Items.PLATES_UNOBTAINIUM))
            .save(uno, rk("unobtainium_sword"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.UNOBTAINIUM_HELMET.get(), 1)
            .pattern("aaa").pattern("ana")
            .define('a', ATMTags.Items.INGOTS_UNOBTAINIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_unobtainium_ingot", has(ATMTags.Items.INGOTS_UNOBTAINIUM))
            .save(uno, rk("unobtainium_helmet"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.UNOBTAINIUM_CHESTPLATE.get(), 1)
            .pattern("a a").pattern("ana").pattern("aaa")
            .define('a', ATMTags.Items.INGOTS_UNOBTAINIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_unobtainium_ingot", has(ATMTags.Items.INGOTS_UNOBTAINIUM))
            .save(uno, rk("unobtainium_chestplate"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.UNOBTAINIUM_LEGGINGS.get(), 1)
            .pattern("aaa").pattern("ana").pattern("a a")
            .define('a', ATMTags.Items.INGOTS_UNOBTAINIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_unobtainium_ingot", has(ATMTags.Items.INGOTS_UNOBTAINIUM))
            .save(uno, rk("unobtainium_leggings"));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, ATMItems.UNOBTAINIUM_BOOTS.get(), 1)
            .pattern("a a").pattern("ana")
            .define('a', ATMTags.Items.INGOTS_UNOBTAINIUM).define('n', Items.NETHERITE_INGOT)
            .unlockedBy("has_unobtainium_ingot", has(ATMTags.Items.INGOTS_UNOBTAINIUM))
            .save(uno, rk("unobtainium_boots"));
    }

    // =============================================
    // HELPERS
    // =============================================

    /** Crea un ResourceKey<Recipe<?>> con el MODID del mod como namespace. */
    private static ResourceKey<Recipe<?>> rk(String path) {
        return ResourceKey.create(Registries.RECIPE,
            Identifier.fromNamespaceAndPath(NoNetheriteTemplate.MODID, path));
    }

    /**
     * Wrapper que descarta advancements para no generar archivos en
     * advancement/recipes/. Adaptado a la firma de 26.1 que usa
     * ResourceKey<Recipe<?>> en lugar de ResourceLocation.
     */
    private static RecipeOutput noAdvancement(RecipeOutput output) {
        return new RecipeOutput() {
            @Override
            public void accept(ResourceKey<Recipe<?>> id, Recipe<?> recipe,
                               AdvancementHolder advancement) {
                output.accept(id, recipe, null);
            }

            @Override
            public Advancement.Builder advancement() {
                return output.advancement();
            }

            @Override
            public void accept(ResourceKey<Recipe<?>> id, Recipe<?> recipe,
                               AdvancementHolder advancement, ICondition... conditions) {
                output.accept(id, recipe, null, conditions);
            }

            @Override
            public void includeRootAdvancement() {}
        };
    }

    // =============================================
    // RUNNER (entry point del datagen)
    // =============================================

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "No Netherite Template Recipes";
        }
    }
}