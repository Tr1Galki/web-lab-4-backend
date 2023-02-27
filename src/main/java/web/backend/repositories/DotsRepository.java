package web.backend.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;
import web.backend.util.DotEntity;

import java.util.List;

@Repository
@SessionScope
public interface DotsRepository extends PagingAndSortingRepository<DotEntity, Long> {
    @Query("SELECT * FROM dots_table")
    List<DotEntity> getDots();

    @Query(value = "INSERT INTO dots_table (x, y, r, date, time, owner, creator, area) VALUES (:x, :y, :r, :date, :time, :owner, :creator, :area)")
    @Modifying
    void addDot(Double x, Double y, Double r, Long date, Long time, String owner, String creator, Boolean area);

    @Query("SELECT * FROM dots_table WHERE owner = :owner")
    List<DotEntity> getDotsByOwner(String owner);


}
