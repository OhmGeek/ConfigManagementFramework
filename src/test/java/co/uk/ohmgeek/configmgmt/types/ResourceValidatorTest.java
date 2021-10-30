package co.uk.ohmgeek.configmgmt.types;

import co.uk.ohmgeek.configmgmt.api.ValidationException;
import co.uk.ohmgeek.configmgmt.types.resources.ResourceA;
import co.uk.ohmgeek.configmgmt.types.resources.ResourceB;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class ResourceValidatorTest {
    private ResourceValidator validator = new ResourceValidator();

    @Test
    public void shouldNotThrowException_whenResourceIsValid() throws ValidationException {
        validator.isValid(new ResourceA("db", "collection", "description"));
    }

    @Test
    public void shouldThrowValidationExceptionWhenContentIsInvalid()  {
        try {
            validator.isValid(new ResourceA("db", "", "description"));
            fail("Should fail as table is empty, but didn't");
        } catch (ValidationException e) {
            assertThat(e.getMessage(), containsString("Validation failed for property [tableName]"));
            assertThat(e.getMessage(), containsString("The table name is empty"));
        }

        try {
            validator.isValid(new ResourceA("db", "table", ""));
            fail("Should fail as description is empty, but didn't");
        } catch (ValidationException e) {
            assertThat(e.getMessage(), containsString("Validation failed for property [description]"));
            assertThat(e.getMessage(), containsString("The description is empty"));
        }
    }

    @Test
    public void shouldThrowValidationExceptionWhenTypeIsntAConfigurationResource()  {
        try {
            validator.isValid(new Object());
            fail("Should fail as Object isn't a configuration resource.");
        } catch (ValidationException e) {
            assertThat(e.getMessage(), containsString("Resource Object isn't annotated as a ConfigurationResource!"));
        }
    }

    @Test
    public void shouldThrowValidationExceptionWhenValidatorMethodIsPrivate() {
        try {
            validator.isValid(new ResourceB());
            fail("Should fail validator method is private");
        } catch (ValidationException e) {
            assertThat(e.getMessage(), containsString("Error accessing validation methods. Is there an incorrect Validator annotation?"));
        }
    }
}