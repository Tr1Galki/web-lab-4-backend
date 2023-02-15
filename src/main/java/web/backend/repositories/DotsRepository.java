package web.backend.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import web.backend.util.DotEntity;

@Repository
public interface DotsRepository extends PagingAndSortingRepository<DotEntity, Long> {

}
