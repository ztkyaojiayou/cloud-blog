package com.jianzh5.blog.seniorvalid;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * 公众号：JAVA日知录
 * @author jam
 * @date 2022/1/12 10:44 上午
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    boolean existsByUserName(String userName);

    boolean existsByUserNameOrEmailOrTelphone(String userName, String email, String telPhone);

    Collection<User> findByUserNameOrEmailOrTelphone(String userName, String email, String telphone);
}
