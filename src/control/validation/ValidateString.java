package control.validation;

public class ValidateString
        implements Validator {

    @Override
    public <T> boolean validate(T data) {
        if (data.toString().equals(""))
            return false;
        else if (data.toString().matches("[a-zA-Z\\s\\d-]+"))
            return true;
        else
            return false;
    }
}
