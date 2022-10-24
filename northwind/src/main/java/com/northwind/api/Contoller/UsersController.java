package com.northwind.api.Contoller;

import com.northwind.business.abstracts.UserService;
import com.northwind.core.entities.User;
import com.northwind.core.utilities.DataResult;
import com.northwind.core.utilities.ErrorDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.userService.add(user));
    }

    @GetMapping("/findByEmail")
    public DataResult<User> findByEmail(@RequestParam String email){
        return this.userService.findByEmail(email);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String,String> validationError = new HashMap<>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors() ){
            validationError.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationError
                ,"Doğrulama Hataları");
        return errorDataResult;
    }

}
