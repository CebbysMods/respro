package lv.cebbys.mcmods.respro;

import lv.cebbys.mcmods.respro.api.ResproRegistry;
import lv.cebbys.mcmods.respro.component.core.ResproPackDump;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;

import static lv.cebbys.mcmods.respro.constant.ResproConstants.RESPRO;

public class Respro implements ModInitializer, ClientModInitializer {
    public static final String MODID = "respro";

    @Override
    public void onInitialize() {
        ResproRegistry.registerData(dataBuilder -> {
            dataBuilder.setDumpMode(true);
            dataBuilder.setPackId(new ResourceLocation(RESPRO, "example_data"));
            dataBuilder.setPackProfile(profile -> {
                profile.setPackIcon(icon -> icon.setFromResources("assets/respro/icon.png"));
                profile.setPackName(text -> text.setText("Respro Data Pack"));
                profile.setPackMeta(meta -> {
                    meta.setDescription("Example Data Pack");
                    meta.setFormat(PackType.SERVER_DATA.getVersion(SharedConstants.getCurrentVersion()));
                });
                profile.setPackInsertionPosition(Pack.Position.TOP);
                profile.setPinned(false);
            });
            dataBuilder.setCustreRecipe(new ResourceLocation(RESPRO, "oak_log_stripping"), custre -> {
                custre.setIngredient(new ResourceLocation("oak_log"));
                custre.setResult(new ResourceLocation("stripped_oak_log"));
            });
        });
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ResproRegistry.registerAssets(assetsBuilder -> {
            assetsBuilder.setDumpMode(true);
            assetsBuilder.setPackId(new ResourceLocation(RESPRO, "example_assets"));
            assetsBuilder.setPackProfile(profile -> {
                profile.setPackIcon(icon -> icon.setFromResources("assets/respro/icon.png"));
                profile.setPackName(text -> text.setText("Respro Asset Pack"));
                profile.setPackMeta(meta -> {
                    meta.setDescription("Example Asset Pack");
                });
                profile.setPackInsertionPosition(Pack.Position.TOP);
                profile.setPinned(false);
            });
            assetsBuilder.setVariantBlockState(new ResourceLocation(RESPRO, "double_slab_block"), variants -> {
                variants.setVariant(properties -> {
                    properties.setProperty("light_level", 1);
                }, variant -> {
                    variant.setModel("double_slab_block");
                });
            });
        });
    }
}
