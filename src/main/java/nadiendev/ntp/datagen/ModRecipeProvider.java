package nadiendev.ntp.datagen;

import nadiendev.ntp.NoNetheriteTemplate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput writer) {
        // Wrapper para evitar generar advancements automáticos
        RecipeOutput recipeOutput = new RecipeOutput() {
            @Override
            public void accept(ResourceLocation id, net.minecraft.world.item.crafting.Recipe<?> recipe, 
                             net.minecraft.advancements.AdvancementHolder advancement) {
                // Solo guardamos la receta, ignoramos el advancement
                writer.accept(id, recipe, null);
            }
            
            @Override
            public net.minecraft.advancements.Advancement.Builder advancement() {
                return writer.advancement();
            }

            @Override
            public void accept(ResourceLocation id, net.minecraft.world.item.crafting.Recipe<?> recipe, 
                             net.minecraft.advancements.AdvancementHolder advancement, ICondition... conditions) {
                // Solo guardamos la receta, ignoramos el advancement
                writer.accept(id, recipe, null, conditions);
            }
        };
        
        // ==========================================
        // RECETAS SHAPELESS
        // ==========================================
// 
// 
//         
        // ==========================================
        // NO TEMPLATE NETHERITE RECIPES
        // ==========================================
         

// Netherite Upgrade Smithing Template
ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1)
    .pattern("aba")
    .pattern("aca")
    .pattern("aaa")
    .define('a', Items.OBSIDIAN)
    .define('b', Items.DIAMOND)
    .define('c', Items.ENDER_EYE)
    .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_upgrade_smithing_template"));

// --- ARMADURAS ---

// Netherite Helmet
ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_HELMET, 1)
    .pattern("aaa")
    .pattern("a a")
    .define('a', Items.NETHERITE_INGOT)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_helmet"));

// Netherite Chestplate
ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_CHESTPLATE, 1)
    .pattern("a a")
    .pattern("aaa")
    .pattern("aaa")
    .define('a', Items.NETHERITE_INGOT)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_chestplate"));

// Netherite Leggings
ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_LEGGINGS, 1)
    .pattern("aaa")
    .pattern("a a")
    .pattern("a a")
    .define('a', Items.NETHERITE_INGOT)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_leggings"));

// Netherite Boots
ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_BOOTS, 1)
    .pattern("a a")
    .pattern("a a")
    .define('a', Items.NETHERITE_INGOT)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_boots"));

// --- HERRAMIENTAS ---

// Netherite Pickaxe
ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_PICKAXE, 1)
    .pattern("aaa")
    .pattern(" b ")
    .pattern(" b ")
    .define('a', Items.NETHERITE_INGOT)
    .define('b', Items.STICK)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_pickaxe"));

// Netherite Axe
ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
    .pattern("aa")
    .pattern("ab")
    .pattern(" b")
    .define('a', Items.NETHERITE_INGOT)
    .define('b', Items.STICK)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_axe"));

ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
    .pattern(" aa")
    .pattern(" ba")
    .pattern(" b ")
    .define('a', Items.NETHERITE_INGOT)
    .define('b', Items.STICK)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_axe_alt"));

// Netherite Shovel
ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_SHOVEL, 1)
    .pattern("a")
    .pattern("b")
    .pattern("b")
    .define('a', Items.NETHERITE_INGOT)
    .define('b', Items.STICK)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_shovel"));

// Netherite Hoe
ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
    .pattern("aa")
    .pattern(" b")
    .pattern(" b")
    .define('a', Items.NETHERITE_INGOT)
    .define('b', Items.STICK)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_hoe"));

ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
    .pattern(" aa")
    .pattern(" b ")
    .pattern(" b ")
    .define('a', Items.NETHERITE_INGOT)
    .define('b', Items.STICK)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_hoe_alt"));


// Netherite Sword
ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_SWORD, 1)
    .pattern("a")
    .pattern("a")
    .pattern("b")
    .define('a', Items.NETHERITE_INGOT)
    .define('b', Items.STICK)
    .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
    .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, "netherite_sword"));
         

    }
}