package lv.cebbys.mcmods.respro.imp.resource.recipe;

import lv.cebbys.mcmods.respro.imp.resource.RecipeResource;
import lv.cebbys.mcmods.respro.imp.parser.JsonPart;

public class ShapedCraftingRecipeResource extends RecipeResource {

    @JsonPart(path = "type")
    protected String type;
    @JsonPart(path = "group")
    protected String group;
    @JsonPart(path = "pattern")
    protected String[] pattern;

    public ShapedCraftingRecipeResource pattern(String row1, String row2, String row3) {
        if (pattern != null) {
            throw new RuntimeException("Recipe pattern has been already initialized");
        }
        if (row1 != null && row2 != null && row3 != null) {
            pattern = new String[3];
            pattern[0] = row1;
            pattern[1] = row2;
            pattern[2] = row3;
            return this;
        } else if (row1 != null && row2 != null) {
            return pattern(row1, row2);
        } else if (row1 != null) {
            return pattern(row1);
        }
        throw new RuntimeException("Invalid recipe pattern parameters");
    }

    public ShapedCraftingRecipeResource pattern(String row1, String row2) {
        return this;
    }

    public ShapedCraftingRecipeResource pattern(String row1) {
        return this;
    }

    private boolean validatePatterns(String... patterns) {
        if (patterns.length == 0) return false;
        int nullCount = 0;
        int emptyLength = 0;
        for (String pattern : patterns) {
            if (pattern == null) {
                nullCount++;
            } else {
                if (pattern.length() > 3) return false;
                else if (pattern.length() == 0) emptyLength++;
            }
        }
        return true;
    }
}
