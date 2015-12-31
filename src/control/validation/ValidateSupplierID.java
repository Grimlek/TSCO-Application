package control.validation;

public final class ValidateSupplierID
    implements Validator
{

    @Override
    public <T> boolean validate(T data)
    {
        if (data.toString().length() == 6 && data.toString().matches("\\d{6}"))
            return true;
        else
            return false;
    }
}
