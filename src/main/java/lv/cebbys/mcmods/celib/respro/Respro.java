package lv.cebbys.mcmods.celib.respro;

import lv.cebbys.mcmods.celib.respro.api.ResproRegistry;
import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;
import lv.cebbys.mcmods.celib.respro.imp.providers.pack.ResproAssetProvider;
import lv.cebbys.mcmods.celib.respro.imp.providers.pack.ResproDataProvider;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Respro implements ModInitializer {
    public static final String MODID = "respro";

    static {
        ResproLogger.info("Initializing CebbyS resource provider");
        ResproRegistry.registerAssetProvider(
                new Identifier(Respro.MODID, "asset_provider"),
                new ResproAssetProvider());

        ResproRegistry.registerDataProvider(
                new Identifier(Respro.MODID, "data_provider"),
                new ResproDataProvider());
        ResproLogger.info("CebbyS resource provider mod initialized");
    }

    @Override
    public void onInitialize() {

    }
}
