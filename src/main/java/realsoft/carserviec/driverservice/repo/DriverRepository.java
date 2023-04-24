package realsoft.carserviec.driverservice.repo;

import org.springframework.stereotype.Repository;
import realsoft.carserviec.driverservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    List<Driver> findAllByIsAssignedFalse();
}
