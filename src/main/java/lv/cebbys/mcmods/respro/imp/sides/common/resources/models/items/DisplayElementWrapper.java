package lv.cebbys.mcmods.respro.imp.sides.common.resources.models.items;

import lv.cebbys.mcmods.respro.imp.parser.ResourceWrapper;

public class DisplayElementWrapper extends ResourceWrapper<String, DisplayElementResource> {

    public DisplayElementResource firstPersonLeftHand() {
        return this.resource("firstperson_lefthand");
    }

    public DisplayElementResource firstPersonRightHand() {
        return this.resource("firstperson_righthand");
    }

    public DisplayElementResource firstPerson() {
        return this.resource("firstperson_righthand", "firstperson_lefthand");
    }

    public DisplayElementResource thirdPersonLeftHand() {
        return this.resource("thirdperson_lefthand");
    }

    public DisplayElementResource thirdPersonRightHand() {
        return this.resource("thirdperson_righthand");
    }

    public DisplayElementResource thirdPerson() {
        return this.resource("thirdperson_righthand", "thirdperson_lefthand");
    }

    public DisplayElementResource gui() {
        return this.resource("gui");
    }

    public DisplayElementResource head() {
        return this.resource("head");
    }

    public DisplayElementResource ground() {
        return this.resource("ground");
    }

    public DisplayElementResource fixed() {
        return this.resource("fixed");
    }

    public DisplayElementResource name(String n) {
        return this.resource(n);
    }

    @Override
    protected Class<DisplayElementResource> wrappedClass() {
        return DisplayElementResource.class;
    }
}
