package web.backend.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;
import web.backend.util.UserEntity;

import java.util.List;

@Repository
@SessionScope
public interface UsersRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    @Query("SELECT * FROM user_table WHERE _user = :user")
    List<UserEntity> getUser(String user);

    @Query(value = "INSERT INTO user_table (_user) VALUES (:user)")
    @Modifying
    void addUser(String user);


}
