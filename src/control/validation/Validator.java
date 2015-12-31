package control.validation;

public interface Validator
{
    public <T> boolean validate(T data);
}
