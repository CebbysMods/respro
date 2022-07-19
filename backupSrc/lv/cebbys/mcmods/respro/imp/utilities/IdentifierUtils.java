package lv.cebbys.mcmods.respro.imp.utility;

import net.minecraft.util.Identifier;

public final class IdentifierUtils {
    private IdentifierUtils() {
    }

    public static Identifier prefix(Identifier id, String prefix) {
        return new Identifier(id.getNamespace(), prefix + id.getPath());
    }

    public static Identifier suffix(Identifier id, String suffix) {
        return new Identifier(id.getNamespace(), id.getPath() + suffix);
    }

    public static Identifier wrapped(String prefix, Identifier id, String suffix) {
        return new Identifier(id.getNamespace(), prefix + id.getPath() + suffix);
    }
}
