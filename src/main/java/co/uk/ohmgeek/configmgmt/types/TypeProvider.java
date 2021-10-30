package co.uk.ohmgeek.configmgmt.types;

import co.uk.ohmgeek.configmgmt.api.ConfigurationResource;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides a set of resolvable types that are supported within the framework.
 * All resolvable types must be annotated with {@link ConfigurationResource} and provide a resolvable name.
 */
public class TypeProvider {
    private final Map<String, Class<?>> typeMap;

    public TypeProvider() {
        Reflections reflections = new Reflections("co.uk.ohmgeek.configmgmt.api");

        Set<Class<?>> annotatedTypes = reflections.getTypesAnnotatedWith(ConfigurationResource.class);
        typeMap = new ConcurrentHashMap<>();
        // For each configuration resource found on the classpath, map the class to the name (we use this for resolving).

        annotatedTypes.forEach((clazz) -> {
            String resolvableName = clazz.getAnnotation(ConfigurationResource.class)
                    .name().toLowerCase();
            typeMap.put(resolvableName, clazz);
        });
    }

    /**
     * @param name the name of the resource to fetch
     * @return the concrete implementation of this resource, or null if there's no mapping.
     */
    public Class<?> getTypeForName(String name) {
        return typeMap.get(name.toLowerCase());
    }
}
