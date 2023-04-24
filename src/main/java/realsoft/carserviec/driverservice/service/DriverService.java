package realsoft.carserviec.driverservice.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realsoft.carserviec.driverservice.entity.Driver;
import realsoft.carserviec.driverservice.repo.DriverRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DriverService {
    @Autowired
    private DriverRepository repository;
    public List<Driver> getAllDrivers(){
        return repository.findAll();
    }
    public Driver getDriver(Long id){
        Optional<Driver> driver = repository.findById(id);
        if (driver.isPresent()){
            return driver.get();
        } else {
            throw new EntityNotFoundException("Driver is not exist");
        }
    }
    public Driver saveDriver(Driver driver){
        return repository.save(driver);
    }
    public Driver updateDriver(Long driverId, Driver dr){
        Optional<Driver> driver = repository.findById(driverId);
        if(driver.isPresent()){
            Driver driver1 = driver.get();
            driver1.setEmail(dr.getEmail());
            driver1.setIsAssigned(dr.getIsAssigned());
            driver1.setName(dr.getName());
            driver1.setPhoneNumber(dr.getPhoneNumber());
            return repository.save(driver1);
        } else {
            throw new EntityNotFoundException("Driver not found");
        }
    }
    public void deleteDriver(Long driverId){
        repository.deleteById(driverId);
    }
    public List<Driver> getAvailableDrivers(){
        return repository.findAllByIsAssignedFalse();
    }
}
