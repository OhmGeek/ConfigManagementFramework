package co.uk.ohmgeek.configmgmt.types;

import co.uk.ohmgeek.configmgmt.types.resources.ResourceA;
import co.uk.ohmgeek.configmgmt.types.resources.ResourceB;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class TypeProviderTest {
    // Define a typeprovider within a specific namespace (which binds to Java package).
    private final TypeProvider typeProvider = new TypeProvider("co.uk.ohmgeek.configmgmt.types");

    @Test
    public void shouldResolveAnnotatedResources() {

        // Test case sensitivity
        assertThat(typeProvider.getTypeForName("resourceA"), is(ResourceA.class));
        assertThat(typeProvider.getTypeForName("resourcea"), is(ResourceA.class));

        assertThat(typeProvider.getTypeForName("resourceB"), is(ResourceB.class));
        assertThat(typeProvider.getTypeForName("resourceb"), is(ResourceB.class));
    }

    @Test
    public void shouldReturnNullForUndefinedType() {
        assertThat(typeProvider.getTypeForName("undefined"), nullValue());
    }
}