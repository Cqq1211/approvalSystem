package com.student.cq.mapper;
import com.student.cq.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户类Mapper
 */
@Mapper
public interface IUserMapper {

    /**
     * 根据用户账号、密码查询用户对象
     * @param username
     * @return
     */
    @Select("select * from user where username= #{username}")
    User selectByUsername(String username);


    /**
     * 更新用户积分
     * @param username
     * @param deduct
     * @return
     */
    int updateIntegral(String username, Integer deduct);

}
