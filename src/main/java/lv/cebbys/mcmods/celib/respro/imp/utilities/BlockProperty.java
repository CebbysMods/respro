package lv.cebbys.mcmods.celib.respro.imp.utilities;

import lv.cebbys.mcmods.celib.respro.imp.loggers.ResproLogger;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class BlockProperty extends LinkedHashMap<String, List<String>> {
    public BlockProperty() {
        super();
    }

    public BlockProperty(String key, String value, String... other) {
        super();
        with(key, value, other);
    }

    public BlockProperty with(String key, String value, String... other) {
        List<String> list = new LinkedList<>();
        list.add(value);
        if (other != null) {
            list.addAll(Arrays.asList(other));
        }
        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (list.size() > 0) {
            super.put(key, list);
        } else {
            ResproLogger.warn("Tried to append invalid value to block property container");
        }
        return this;
    }

    public Map<String, String> toMultipart() {
        Map<String, String> multipart = new LinkedHashMap<>();
        for (String parentKey : super.keySet()) {
            List<String> parentValues = super.get(parentKey);
            StringBuilder value = new StringBuilder();
            boolean first = true;
            for (String parentValue : parentValues) {
                if (first) first = false;
                else {
                    value.append("|");
                }
                value.append(parentValue);
            }
            multipart.put(parentKey, value.toString());
        }
        return multipart;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String key : this.keySet()) {
            List<String> value = this.get(key);
            if (first) {
                first = false;
            } else {
                builder.append(",");
            }
            builder.append(key).append("=").append(value.get(0));
        }
        return builder.toString();
    }
}
