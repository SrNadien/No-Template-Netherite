package nadiendev.ntp.datagen.providers;

import nadiendev.ntp.NoNetheriteTemplate;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    private final HolderGetter<Item> items;

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
        this.items = registries.lookupOrThrow(Registries.ITEM);
    }

    @Override
    protected void buildRecipes() {
        // ==========================================
        // NO TEMPLATE NETHERITE RECIPES
        // ==========================================

        // Netherite Upgrade Smithing Template
        ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1)
            .pattern("aba")
            .pattern("aca")
            .pattern("aaa")
            .define('a', Items.OBSIDIAN)
            .define('b', Items.DIAMOND)
            .define('c', Items.ENDER_EYE)
            .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_upgrade_smithing_template")));

        // --- ARMADURAS ---

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_HELMET, 1)
            .pattern("aaa")
            .pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_helmet")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_CHESTPLATE, 1)
            .pattern("a a")
            .pattern("aaa")
            .pattern("aaa")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_chestplate")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_LEGGINGS, 1)
            .pattern("aaa")
            .pattern("a a")
            .pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_leggings")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_BOOTS, 1)
            .pattern("a a")
            .pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_boots")));

        // --- HERRAMIENTAS ---

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_PICKAXE, 1)
            .pattern("aaa")
            .pattern(" b ")
            .pattern(" b ")
            .define('a', Items.NETHERITE_INGOT)
            .define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_pickaxe")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
            .pattern("aa")
            .pattern("ab")
            .pattern(" b")
            .define('a', Items.NETHERITE_INGOT)
            .define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_axe")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
            .pattern(" aa")
            .pattern(" ba")
            .pattern(" b ")
            .define('a', Items.NETHERITE_INGOT)
            .define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_axe_alt")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_SHOVEL, 1)
            .pattern("a")
            .pattern("b")
            .pattern("b")
            .define('a', Items.NETHERITE_INGOT)
            .define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_shovel")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
            .pattern("aa")
            .pattern(" b")
            .pattern(" b")
            .define('a', Items.NETHERITE_INGOT)
            .define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_hoe")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
            .pattern(" aa")
            .pattern(" b ")
            .pattern(" b ")
            .define('a', Items.NETHERITE_INGOT)
            .define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_hoe_alt")));

        ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.NETHERITE_SWORD, 1)
            .pattern("a")
            .pattern("a")
            .pattern("b")
            .define('a', Items.NETHERITE_INGOT)
            .define('b', Items.STICK)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_sword")));
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            // Wrapper que descarta advancements para no generar archivos en advancement/recipes/
            RecipeOutput noAdvancementOutput = new RecipeOutput() {
                @Override
                public void accept(ResourceKey<Recipe<?>> id, Recipe<?> recipe, AdvancementHolder advancement) {
                    output.accept(id, recipe, null);
                }

                @Override
                public Advancement.Builder advancement() {
                    return output.advancement();
                }

                @Override
                public void accept(ResourceKey<Recipe<?>> id, Recipe<?> recipe, AdvancementHolder advancement, ICondition... conditions) {
                    output.accept(id, recipe, null, conditions);
                }

                @Override
                public void includeRootAdvancement() {}
            };
            return new ModRecipeProvider(registries, noAdvancementOutput);
        }

        @Override
        public String getName() {
            return "No Netherite Template Recipes";
        }
    }
}