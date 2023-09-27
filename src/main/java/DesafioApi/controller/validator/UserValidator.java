package DesafioApi.controller.validator;

import DesafioApi.domain.model.User;
import DesafioApi.exception.InvalidFieldsException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserValidator {

    public static void validate(User user) {
        var sb = new StringBuilder();
        validateField("firstname", user.getFirstName(), sb);
        validateField("lastname", user.getLastName(), sb);
        validateField("email", user.getEmail(), sb);
        validateField("birthday", String.valueOf(user.getBirthday()), sb);
        validateField("login", user.getLogin(), sb);
        validateField("password", user.getPassword(), sb);
        validateField("phone", user.getPhone(), sb);

        if (!sb.isEmpty()) {
            throw new InvalidFieldsException("Invalid fields: " + sb);
        }
    }

    private static void validateField(String field, String value, StringBuilder sb) {
        if (value == null || value.isBlank()) {
            sb.append(field).append(" is required, ");
        }
    }
}
