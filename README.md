# ConfigManagementFramework
A Java framework for rolling out config changes. 

## Why?
Tools like Terraform/Puppet allow for fairly decent configuration management.
However, all of these are mostly focused on infrastructure/OS configuration. 

This framework is designed to provide configuration management for anything with a Java API.

## How does this work?
### ConfigurationResource - representation of configuration state.
A configuration resource (annotated with `@ConfigurationResource`), represents
the configuration state. It consists of various properties, as well as associated methods
for providing validation on the config.

### Validation of resources

Validation can be achieved by public methods annotated with `@Validator(property = "propertyName")`. These methods are contained
within the configuration resource POJOs (along with the resource state representation).

This method should validate the state of the configuration, and if there's a validation error, an exception should be
thrown (runtime or checked are both accepted). 

The actual resource validation is done by the `ResourceValidator` class which will resolve all validation methods
for a particular resource, call them (to validate), and report any errors that need fixing.