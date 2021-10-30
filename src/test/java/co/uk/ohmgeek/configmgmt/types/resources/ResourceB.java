package co.uk.ohmgeek.configmgmt.types.resources;


import co.uk.ohmgeek.configmgmt.api.ConfigurationResource;
import co.uk.ohmgeek.configmgmt.api.Validator;

@ConfigurationResource(name = "resourceb")
public class ResourceB {

    @Validator(property = "privateProperty")
    private void privateProperty() {
        // do nothing.
    }
}
