package co.uk.ohmgeek.configmgmt.types.resources;


import co.uk.ohmgeek.configmgmt.api.ConfigurationResource;
import co.uk.ohmgeek.configmgmt.api.Validator;

@ConfigurationResource(name = "resourcea")
public class ResourceA {
    private String databaseName;
    private String tableName;
    private String description;

    public ResourceA(String db, String table, String desc) {
        this.databaseName = db;
        this.tableName = table;
        this.description = desc;
    }

    public ResourceA() {

    }

    @Validator(property = "databaseName")
    public void validateDatabaseName() {
        // do nothing.
    }

    @Validator(property = "tableName")
    public void validateTableName() throws Exception {
        if (tableName.isEmpty()) {
            // Throw a checked exception.
            throw new Exception("The table name is empty");
        }
    }

    @Validator(property = "description")
    public void validateDescription() {
        if (description.isEmpty()) {
            throw new RuntimeException("The description is empty");
        }
    }


}
