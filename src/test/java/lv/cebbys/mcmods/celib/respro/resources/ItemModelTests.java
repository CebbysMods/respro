package lv.cebbys.mcmods.celib.respro.resources;


import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.parser.ResourceParser;
import lv.cebbys.mcmods.celib.respro.imp.sides.common.resources.models.items.ItemModelResource;
import org.junit.jupiter.api.Test;

public class ItemModelTests {

    @Test
    void test0_itemModelResourceTests() {
        ItemModelResource resource = new ItemModelResource();
        resource.display(display -> {
            display.firstPerson().rotation(0, 0, 0).scale(0.5, 0.5, 0.5).translation(0, 0, 0);
        });
        ResourceParser parser = new ResourceParser();
        JsonObject object = parser.parse(resource);
        System.out.println(object);
    }
}
