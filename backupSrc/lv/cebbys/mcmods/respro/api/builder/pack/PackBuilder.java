package lv.cebbys.mcmods.respro.api.builder.pack;

public interface PackBuilder<T extends PackBuilder<?>> {
    T setPackIcon(String pathInResources);

    T setPackName(String packName);

    T setPackDescription(String description);
}
