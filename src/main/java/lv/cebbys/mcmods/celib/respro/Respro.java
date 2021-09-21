package lv.cebbys.mcmods.celib.respro;

import lv.cebbys.mcmods.celib.respro.api.ResproRegistry;
import lv.cebbys.mcmods.celib.respro.imp.sides.client.providers.ResproAssetProvider;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.providers.ResproDataProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Respro implements ModInitializer, ClientModInitializer {
    public static final String MODID = "respro";

    @Override
    public void onInitialize() {
        ResproRegistry.registerDataProvider(
                new Identifier(Respro.MODID, "data_provider"),
                new ResproDataProvider());
    }

    @Override
    public void onInitializeClient() {
        ResproRegistry.registerAssetProvider(
                new Identifier(Respro.MODID, "asset_provider"),
                new ResproAssetProvider());
    }
}
