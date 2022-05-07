package com.datn.cinema.repository;

import com.datn.cinema.entity.User;
import com.datn.cinema.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from `user`\n" +
            "inner join `account` on `user`.username = `account`.username\n" +
            "inner join ward on `user`.ward_id = ward.ward_id\n" +
            "inner join account_status on account_status.account_status_id = `account`.account_status_id\n" +
            "where concat( email , `name` ,`account`.username, ward.ward_name , account_status.account_status_name ) like concat('%',?1,'%')", nativeQuery = true)
    List<User> searchAll(String key);

    @Query(value = "select * from `user` \n" +
            "inner join `account` on `user`.username = `account`.username\n" +
            "inner join account_role on `account`.username = account_role.username\n" +
            "where account_role.role_id = 3\n" +
            "group by `user`.user_id \n" +
            "limit ?1 , 5", nativeQuery = true)
    List<User> getAllUser(int index);

    @Query(value = "select * from `user` \n" +
            "inner join `account` on `user`.username = `account`.username\n" +
            "inner join account_role on `account`.username = account_role.username\n" +
            "where account_role.role_id = 3\n", nativeQuery = true)
    List<User> findAllUser();

    @Query(value = "select * from `user` where user_id = ?1", nativeQuery = true)
    User findUserById(Integer userId);

    @Modifying
    @Query(value = "update User u" +
            " set u.name = ?2, " +
            "u.email =?3, " +
            "u.phone =?4, " +
            "u.ward =?5, " +
            "u.avatarUrl =?6, " +
            "u.gender = ?7, " +
            "u.birthday = ?8" +
            " where u.userId = ?1")
    void updateUser(Integer userId, String name, String email, String phone,
                    Ward ward, String avatarUrl, int gender, String birthday);

    User findByEmail(String email);

    @Query(value = "SELECT * FROM `user` " +
            "WHERE username = ?1", nativeQuery = true)
    User findByUsername(String username);

    @Modifying
    @Query(value = "update `user`" +
            " set `name` = ?2, " +
            "birthday =?3, " +
            "gender =?4, " +
            "email =?5, " +
            "id_card =?6, " +
            "phone =?7" +
            " where username = ?1", nativeQuery = true)
    void updateUser(String username, String name, String birthday, Integer gender, String email, String idCard, String phone);

    @Modifying
    @Query(value = "update `user`" +
            " set `name` = ?2, " +
            "birthday =?3, " +
            "gender =?4, " +
            "email =?5, " +
            "id_card =?6, " +
            "phone =?7" +
            " where user_id = ?1", nativeQuery = true)
    void updateUser1(String name, String birthday, Integer gender, String email, String idCard, String phone);

    @Query(value = "select  * from `user` where `user`.email = ?1", nativeQuery = true)
    User getUserByEmail(String email);

    @Query(value = "select * from `user` where `user`.id_card = ?1", nativeQuery = true)
    User getUserByIdCard(String username);

    @Modifying
    @Query(value = "INSERT INTO `user` ( avatar_url, birthday, email, gender, id_card, name, phone, username, ward_id) " +
            "values " + "(:avatarUrl," + ":birthday," + ":email," + " :gender," + " :idCard," + ":name," + ":phone," + ":username," + ":wardId ) ",
            nativeQuery = true)
    @Transactional
    void saveUserCus(@Param("avatarUrl") String avatarUrl,
                     @Param("name") String name,
                     @Param("username") String username,
                     @Param("email") String email,
                     @Param("birthday") String birthday,
                     @Param("idCard") String idCard,
                     @Param("gender") int gender,
                     @Param("phone") String phone,
                     @Param("wardId") Integer wardId);

    @Query(value = "SELECT * FROM `user` " +
            "INNER JOIN `account` ON `account`.username = `user`.username " +
            "WHERE `account`.username = ?1", nativeQuery = true)
    User getUserByUsername(String username);

    @Query(value = "SELECT * FROM cinema_db.user WHERE user.id_card = ?1", nativeQuery = true)
    User findUserByCardId(String cardId);

    @Query(value = "select * from `user`\n" +
            "            inner join `account` on `user`.username = `account`.username\n" +
            "            inner join ward on `user`.ward_id = ward.ward_id\n" +
            "            inner join account_status on account_status.account_status_id = `account`.account_status_id\n" +
            "            where concat( email , `name` ,`account`.username, ward.ward_name , account_status.account_status_name ) like concat('%',?1,'%') limit ?2,5", nativeQuery = true)
    List<User> getListSearchPagination(String q, int index);

    @Modifying
    @Query(value = "insert into `user`(name, email, phone, username, avatar_url, ward_id, birthday, id_card, gender) " +
            "value (?1, ?2, ?3, ?4 , ?5, ?6, ?7, ?8, ?9)"
            , nativeQuery = true)
    void saveUserSocial(String name, String email, String phone, String username, String avatarUrl, Integer wardId,
                        String birthday, String idCard, Integer gender);

    @Modifying
    @Transactional
    @Query(value = "insert into `user`(avatar_url,birthday,email,gender,id_card,`name`,phone,username,ward_id) " +
            "value (null,null, ?1 ,null, ?2 , ?3 , ?4 , null,null) "
            , nativeQuery = true)
    void createUserWithNoAccount(String email, String idCard, String name, String phone);

    @Query(value = "select * from `user` " +
            "where `user`.email = ?1 and `user`.id_card = ?2 and `user`.`name` = ?3 and `user`.phone = ?4 "
            , nativeQuery = true)
    User getUserByUserNoAccountDTO(String email, String idCard, String name, String phone);
}
