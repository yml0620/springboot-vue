package com.yml.demo.service;

import com.yml.demo.model.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface UserService {

    void AddUser(User user);

    void delUserById(@Param("no") int no);

    void updateUserByNo(User user);

    List<User> getUser();
}
