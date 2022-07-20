package lv.cebbys.mcmods.respro.constant;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.PackSource;

public class ResproConstants {
    public static final String RESPRO = "respro";
    public static final String PACK_MCMETA_PATH = "pack.mcmeta";
    public static final String PACK_ICON_PATH = "pack.png";
    public static final String PACK_SOURCE_STRING_RESPRO = "pack.source.respro";
    public static final PackSource PACK_SOURCE_RESPRO = PackSource.decorating(PACK_SOURCE_STRING_RESPRO);
    public static final ResourceLocation RESPRO_PACK_MCMETA_LOCATION = new ResourceLocation(RESPRO, PACK_MCMETA_PATH);
    public static final ResourceLocation RESPRO_PACK_ICON_LOCATION = new ResourceLocation(RESPRO, PACK_ICON_PATH);
}
