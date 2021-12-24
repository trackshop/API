// XII·IX <> VII·X

package nl.th7mo.trackshop.api.user;

import nl.th7mo.trackshop.api.role.CantRevokeSuperAdminPrivilegeException;
import nl.th7mo.trackshop.api.util.ExceptionResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleException(
        UserNotFoundException e
    ) {
        return ExceptionResponseBuilder.build(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleException(
        UserAlreadyExistsException e
    ) {
        return ExceptionResponseBuilder.build(HttpStatus.ALREADY_REPORTED, e);
    }

    @ExceptionHandler(CantRevokeSuperAdminPrivilegeException.class)
    public ResponseEntity<Map<String, Object>> handleException(
        CantRevokeSuperAdminPrivilegeException e
    ) {
        return ExceptionResponseBuilder.build(HttpStatus.FORBIDDEN, e);
    }
}
