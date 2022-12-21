package firstmod.firstmod.utils;

public record FieldPath(String classPath, String fieldName)
{
    public FieldPath
    {
        if(classPath == null || fieldName == null)
            throw new IllegalArgumentException("Class path and field name cannot be null");
    }
}
