package control.validation;

public final class ValidateInteger
    implements Validator
{

    @Override
    public <T> boolean validate(T data)
    {
        if (data == null)
            return false;
        else if (data.toString().matches("\\d+"))
            return true;
        else
            return false;
    }
}
