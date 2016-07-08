package control.validation;

public class ValidateDouble
        implements Validator {
    @Override
    public <T> boolean validate(T data) {
        if (data == null)
            return false;
        else if (data.toString().matches("^[0-9]*\\.[0-9]{2}$"))
            return true;
        else
            return false;
    }
}
