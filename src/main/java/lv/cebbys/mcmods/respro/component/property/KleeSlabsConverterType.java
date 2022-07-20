package lv.cebbys.mcmods.respro.component.property;

public enum KleeSlabsConverterType {
    SIMPLE("SimpleSlabConverter"),
    SMART("SmartSlabConverter"),
    SMARTER("SmarterSlabConverter");

    private final String name;

    KleeSlabsConverterType(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return name;
    }
}
