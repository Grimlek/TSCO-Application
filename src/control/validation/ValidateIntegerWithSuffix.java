package control.validation;

public class ValidateIntegerWithSuffix
        implements Validator {
    @Override
    public <T> boolean validate(T data) {
        if (data.toString().matches("^\\d+\\s?[a-zA-Z\\.-]*"))
            return true;
        else
            return false;
    }
}
