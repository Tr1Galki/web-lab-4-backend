package web.backend.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import web.backend.util.DotEntry;

public interface DotsRepository extends PagingAndSortingRepository<DotEntry, Long> {

}
