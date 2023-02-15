package web.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import web.backend.util.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, String> {

}
