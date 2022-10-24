package com.northwind.business.concretes;

import com.northwind.business.abstracts.UserService;
import com.northwind.core.dataAccess.UserDao;
import com.northwind.core.entities.User;
import com.northwind.core.result.Result;
import com.northwind.core.result.SuccessResult;
import com.northwind.core.utilities.DataResult;
import com.northwind.core.utilities.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı Eklendi");
    }

    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.findByEmail(email)
                ,"Email'e göre kullnıcıyı getir.");
    }
}
