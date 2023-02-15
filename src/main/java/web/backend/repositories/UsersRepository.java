package web.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import web.backend.util.UserEntry;

public interface UsersRepository extends CrudRepository<UserEntry, String> {

}
