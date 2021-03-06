package com.yu.mapper;

import com.yu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @className: UserSqlProvider
 * @author: yu.liu
 * @date: 2019/8/7 16:35
 * @description:
 */
public class UserSqlProvider {
    /**
     * 方式1：在工具类的方法里,可以自己手工编写SQL。
     */
    public String listByUsername(String username) {
        return "select * from t_user where username =#{username}";
    }

    /**
     * 方式2：也可以根据官方提供的API来编写动态SQL。
     */
    public String getBadUser(@Param("username") String username, @Param("password") String password) {
        return new SQL() {{
            SELECT("*");
            FROM("t_user");
            if (username != null && password != null) {
                WHERE("username like #{username} and password like #{password}");
            } else {
                WHERE("1=2");
            }
        }}.toString();
    }


    public String update(User user) {
        return "update t_user set username = #{username} where USER_ID = #{userId} ";
    }

    public String delete(String username) {
        return "delete from  t_user  where username = #{username} ";
    }
}
