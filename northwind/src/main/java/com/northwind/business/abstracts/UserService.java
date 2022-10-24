package com.northwind.business.abstracts;

import com.northwind.core.entities.User;
import com.northwind.core.result.Result;
import com.northwind.core.utilities.DataResult;

public interface UserService {
    Result add(User user);
    DataResult<User> findByEmail(String email);

}
