package nadiendev.ntp.datagen.providers;

import com.thevortex.allthemodium.registry.ModRegistry;
import com.thevortex.allthemodium.registry.TagRegistry;
import nadiendev.ntp.NoNetheriteTemplate;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput writer) {

        // base → recetas normales sin advancements
        // atm/vib/uno → solo cargan si allthemodium está instalado
        RecipeOutput base = noAdvancement(writer);
        RecipeOutput atm  = noAdvancement(writer.withConditions(new ModLoadedCondition("allthemodium")));
        RecipeOutput vib  = noAdvancement(writer.withConditions(new ModLoadedCondition("allthemodium")));
        RecipeOutput uno  = noAdvancement(writer.withConditions(new ModLoadedCondition("allthemodium")));

        // ==========================================
        // NETHERITE 
        // ==========================================

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1)
            .pattern("aba").pattern("aca").pattern("aaa")
            .define('a', Items.OBSIDIAN).define('b', Items.DIAMOND).define('c', Items.EMERALD)
            .unlockedBy("has_obsidian", has(Items.OBSIDIAN))
            .save(base, rl("netherite_upgrade_smithing_template"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_HELMET, 1)
            .pattern("asa").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .define('s', Items.DIAMOND_HELMET)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_helmet"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_CHESTPLATE, 1)
            .pattern("a a").pattern("asa").pattern("aaa")
            .define('a', Items.NETHERITE_INGOT)
            .define('s', Items.DIAMOND_CHESTPLATE)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_LEGGINGS, 1)
            .pattern("asa").pattern("a a").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .define('s', Items.DIAMOND_LEGGINGS)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_BOOTS, 1)
            .pattern("asa").pattern("a a")
            .define('a', Items.NETHERITE_INGOT)
            .define('s', Items.DIAMOND_BOOTS)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_boots"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_PICKAXE, 1)
            .pattern("asa").pattern(" b ").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK).define('s', Items.DIAMOND_PICKAXE)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_pickaxe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
            .pattern("as").pattern("ab").pattern(" b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK).define('s', Items.DIAMOND_AXE)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_AXE, 1)
            .pattern(" as").pattern(" ba").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK).define('s', Items.DIAMOND_AXE)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_axe_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_SHOVEL, 1)
            .pattern("a").pattern("s").pattern("b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK).define('s', Items.DIAMOND_SHOVEL)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_shovel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
            .pattern("as").pattern(" b").pattern(" b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK).define('s', Items.DIAMOND_HOE)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_hoe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.NETHERITE_HOE, 1)
            .pattern(" as").pattern(" b ").pattern(" b ")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK).define('s', Items.DIAMOND_HOE)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_hoe_alt"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.NETHERITE_SWORD, 1)
            .pattern("a").pattern("s").pattern("b")
            .define('a', Items.NETHERITE_INGOT).define('b', Items.STICK).define('s', Items.DIAMOND_SWORD)
            .unlockedBy("has_netherite_ingot", has(Items.NETHERITE_INGOT))
            .save(base, rl("netherite_sword"));

        // ==========================================
        // ALLTHEMODIUM — plate+rod herramientas, ingot+netherite armaduras
        // ==========================================

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.ATM_PICKAXE.get(), 1)
            .pattern("asa").pattern(" r ").pattern(" r ")
            .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD).define('s', Items.NETHERITE_PICKAXE)
            .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE))
            .save(atm, rl("allthemodium_pickaxe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.ATM_AXE.get(), 1)
            .pattern("as").pattern("ar").pattern(" r")
            .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD).define('s', Items.NETHERITE_AXE)
            .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE))
            .save(atm, rl("allthemodium_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.ATM_SHOVEL.get(), 1)
            .pattern(" a ").pattern(" r ").pattern(" r ")
            .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD)
            .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE))
            .save(atm, rl("allthemodium_shovel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.ATM_HOE.get(), 1)
            .pattern("as ").pattern(" r ").pattern(" r ")
            .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD).define('s', Items.NETHERITE_HOE)
            .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE))
            .save(atm, rl("allthemodium_hoe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ATM_SWORD.get(), 1)
            .pattern(" a ").pattern(" s ").pattern(" r ")
            .define('a', TagRegistry.ALLTHEMODIUM_PLATE).define('r', TagRegistry.ALLTHEMODIUM_ROD).define('s', Items.NETHERITE_SWORD)
            .unlockedBy("has_allthemodium_plate", has(TagRegistry.ALLTHEMODIUM_PLATE))
            .save(atm, rl("allthemodium_sword"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_HELMET.get(), 1)
            .pattern("ana").pattern("aaa")
            .define('a', TagRegistry.ALLTHEMODIUM_INGOT).define('n', Items.NETHERITE_HELMET)
            .unlockedBy("has_allthemodium_ingot", has(TagRegistry.ALLTHEMODIUM_INGOT))
            .save(atm, rl("allthemodium_helmet"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_CHESTPLATE.get(), 1)
            .pattern("a a").pattern("ana").pattern("aaa")
            .define('a', TagRegistry.ALLTHEMODIUM_INGOT).define('n', Items.NETHERITE_CHESTPLATE)
            .unlockedBy("has_allthemodium_ingot", has(TagRegistry.ALLTHEMODIUM_INGOT))
            .save(atm, rl("allthemodium_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_LEGGINGS.get(), 1)
            .pattern("aaa").pattern("ana").pattern("a a")
            .define('a', TagRegistry.ALLTHEMODIUM_INGOT).define('n', Items.NETHERITE_LEGGINGS)
            .unlockedBy("has_allthemodium_ingot", has(TagRegistry.ALLTHEMODIUM_INGOT))
            .save(atm, rl("allthemodium_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.ALLTHEMODIUM_BOOTS.get(), 1)
            .pattern("a a").pattern("ana")
            .define('a', TagRegistry.ALLTHEMODIUM_INGOT).define('n', Items.NETHERITE_BOOTS)
            .unlockedBy("has_allthemodium_ingot", has(TagRegistry.ALLTHEMODIUM_INGOT))
            .save(atm, rl("allthemodium_boots"));

        // ==========================================
        // VIBRANIUM — misma condición allthemodium
        // ==========================================

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.VIB_PICKAXE.get(), 1)
            .pattern("asa").pattern(" r ").pattern(" r ")
            .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD).define('s', ModRegistry.ATM_PICKAXE.get())
            .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE))
            .save(vib, rl("vibranium_pickaxe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.VIB_AXE.get(), 1)
            .pattern("as").pattern("ar").pattern(" r")
            .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD).define('s', ModRegistry.ATM_AXE.get())
            .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE))
            .save(vib, rl("vibranium_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.VIB_SHOVEL.get(), 1)
            .pattern(" a ").pattern(" s ").pattern(" r ")
            .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD).define('s', ModRegistry.ATM_SHOVEL.get())
            .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE))
            .save(vib, rl("vibranium_shovel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.VIB_HOE.get(), 1)
            .pattern("as ").pattern(" r ").pattern(" r ")
            .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD).define('s', ModRegistry.ATM_HOE.get())
            .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE))
            .save(vib, rl("vibranium_hoe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIB_SWORD.get(), 1)
            .pattern(" a ").pattern(" s ").pattern(" r ")
            .define('a', TagRegistry.VIBRANIUM_PLATE).define('r', TagRegistry.VIBRANIUM_ROD).define('s', ModRegistry.ATM_SWORD.get())
            .unlockedBy("has_vibranium_plate", has(TagRegistry.VIBRANIUM_PLATE))
            .save(vib, rl("vibranium_sword"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_HELMET.get(), 1)
            .pattern("ana").pattern("aaa")
            .define('a', TagRegistry.VIBRANIUM_INGOT).define('n', ModRegistry.ALLTHEMODIUM_HELMET.get())
            .unlockedBy("has_vibranium_ingot", has(TagRegistry.VIBRANIUM_INGOT))
            .save(vib, rl("vibranium_helmet"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_CHESTPLATE.get(), 1)
            .pattern("a a").pattern("ana").pattern("aaa")
            .define('a', TagRegistry.VIBRANIUM_INGOT).define('n', ModRegistry.ALLTHEMODIUM_CHESTPLATE.get())
            .unlockedBy("has_vibranium_ingot", has(TagRegistry.VIBRANIUM_INGOT))
            .save(vib, rl("vibranium_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_LEGGINGS.get(), 1)
            .pattern("aaa").pattern("ana").pattern("a a")
            .define('a', TagRegistry.VIBRANIUM_INGOT).define('n', ModRegistry.ALLTHEMODIUM_LEGGINGS.get())
            .unlockedBy("has_vibranium_ingot", has(TagRegistry.VIBRANIUM_INGOT))
            .save(vib, rl("vibranium_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.VIBRANIUM_BOOTS.get(), 1)
            .pattern("a a").pattern("ana")
            .define('a', TagRegistry.VIBRANIUM_INGOT).define('n', ModRegistry.ALLTHEMODIUM_BOOTS.get())
            .unlockedBy("has_vibranium_ingot", has(TagRegistry.VIBRANIUM_INGOT))
            .save(vib, rl("vibranium_boots"));

        // ==========================================
        // UNOBTAINIUM — misma condición allthemodium
        // ==========================================

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.UNO_PICKAXE.get(), 1)
            .pattern("asa").pattern(" r ").pattern(" r ")
            .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD).define('s', ModRegistry.VIB_PICKAXE.get())
            .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE))
            .save(uno, rl("unobtainium_pickaxe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.UNO_AXE.get(), 1)
            .pattern("as").pattern("ar").pattern(" r")
            .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD).define('s', ModRegistry.VIB_AXE.get())
            .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE))
            .save(uno, rl("unobtainium_axe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.UNO_SHOVEL.get(), 1)
            .pattern(" a ").pattern(" s ").pattern(" r ")
            .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD).define('s', ModRegistry.VIB_SHOVEL.get())
            .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE))
            .save(uno, rl("unobtainium_shovel"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModRegistry.UNO_HOE.get(), 1)
            .pattern("as ").pattern(" r ").pattern(" r ")
            .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD).define('s', ModRegistry.VIB_HOE.get())
            .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE))
            .save(uno, rl("unobtainium_hoe"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNO_SWORD.get(), 1)
            .pattern(" a ").pattern(" s ").pattern(" r ")
            .define('a', TagRegistry.UNOBTAINIUM_PLATE).define('r', TagRegistry.UNOBTAINIUM_ROD).define('s', ModRegistry.VIB_SWORD.get())
            .unlockedBy("has_unobtainium_plate", has(TagRegistry.UNOBTAINIUM_PLATE))
            .save(uno, rl("unobtainium_sword"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_HELMET.get(), 1)
            .pattern("ana").pattern("aaa")
            .define('a', TagRegistry.UNOBTAINIUM_INGOT).define('n', ModRegistry.VIBRANIUM_HELMET.get())
            .unlockedBy("has_unobtainium_ingot", has(TagRegistry.UNOBTAINIUM_INGOT))
            .save(uno, rl("unobtainium_helmet"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_CHESTPLATE.get(), 1)
            .pattern("a a").pattern("ana").pattern("aaa")
            .define('a', TagRegistry.UNOBTAINIUM_INGOT).define('n', ModRegistry.VIBRANIUM_CHESTPLATE.get())
            .unlockedBy("has_unobtainium_ingot", has(TagRegistry.UNOBTAINIUM_INGOT))
            .save(uno, rl("unobtainium_chestplate"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_LEGGINGS.get(), 1)
            .pattern("aaa").pattern("ana").pattern("a a")
            .define('a', TagRegistry.UNOBTAINIUM_INGOT).define('n', ModRegistry.VIBRANIUM_LEGGINGS.get())
            .unlockedBy("has_unobtainium_ingot", has(TagRegistry.UNOBTAINIUM_INGOT))
            .save(uno, rl("unobtainium_leggings"));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModRegistry.UNOBTAINIUM_BOOTS.get(), 1)
            .pattern("a a").pattern("ana")
            .define('a', TagRegistry.UNOBTAINIUM_INGOT).define('n', ModRegistry.VIBRANIUM_BOOTS.get())
            .unlockedBy("has_unobtainium_ingot", has(TagRegistry.UNOBTAINIUM_INGOT))
            .save(uno, rl("unobtainium_boots"));
    }

    // =============================================
    // HELPERS
    // =============================================

    private static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(NoNetheriteTemplate.MODID, path);
    }


    private static RecipeOutput noAdvancement(RecipeOutput output) {
        return new RecipeOutput() {
            @Override
            public void accept(ResourceLocation id, net.minecraft.world.item.crafting.Recipe<?> recipe,
                             AdvancementHolder advancement) {
                output.accept(id, recipe, null);
            }

            @Override
            public net.minecraft.advancements.Advancement.Builder advancement() {
                return output.advancement();
            }

            @Override
            public void accept(ResourceLocation id, net.minecraft.world.item.crafting.Recipe<?> recipe,
                             AdvancementHolder advancement, ICondition... conditions) {
                output.accept(id, recipe, null, conditions);
            }

        };
    }
}