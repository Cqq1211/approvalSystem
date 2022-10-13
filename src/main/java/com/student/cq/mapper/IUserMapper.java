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
     * @param account
     * @return
     */
    @Select("select * from user where account= #{account}")
    User selectByAccount(String account);


    /**
     * 更新用户积分
     * @param account
     * @param deduct
     * @return
     */
    int updateIntegral(String account, Integer deduct);

}
