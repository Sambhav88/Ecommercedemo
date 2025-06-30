package com.Sambhav.Ecommerce.Service;

import com.Sambhav.Ecommerce.Repository.UserRepo;
import com.Sambhav.Ecommerce.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    UserRepo repo;

    public Users register(Users user)
    {
        return repo.save(user);
    }
}
