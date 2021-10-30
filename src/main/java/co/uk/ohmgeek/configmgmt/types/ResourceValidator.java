package co.uk.ohmgeek.configmgmt.types;

import co.uk.ohmgeek.configmgmt.api.ConfigurationResource;
import co.uk.ohmgeek.configmgmt.api.ValidationException;
import co.uk.ohmgeek.configmgmt.api.Validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * I validate any object annotated with {@link ConfigurationResource}. All public methods marked with {@link Validator}
 * will be called in order to validate the state of a resource is valid.
 */
public class ResourceValidator {

    public void isValid(Object resource) throws ValidationException {
        if (!isConfigurationResource(resource)) {
            throw new ValidationException(String.format("Resource %s isn't annotated as a ConfigurationResource!",
                    resource.getClass().getSimpleName()));
        }

        String resourceName = resource.getClass().getAnnotation(ConfigurationResource.class).name();

        for (Method method : resource.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Validator.class)) {
                String propertyBeingValidated = method.getAnnotation(Validator.class).property();

                try {
                    method.invoke(resource);
                } catch (InvocationTargetException e) {
                    throw new ValidationException(String.format("Validation failed for property [%s] - %s", propertyBeingValidated, e.getCause().getMessage()), e);
                } catch (IllegalAccessException e) {
                    throw new ValidationException("Error accessing validation methods. Is there an incorrect Validator annotation?", e);
                }
            }
        }

    }

    private boolean isConfigurationResource(Object resource) {
        return resource.getClass().isAnnotationPresent(ConfigurationResource.class);
    }
}
