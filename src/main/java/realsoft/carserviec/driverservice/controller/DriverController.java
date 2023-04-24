package realsoft.carserviec.driverservice.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import realsoft.carserviec.driverservice.entity.Driver;
import realsoft.carserviec.driverservice.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@Api(value = "Driver service Endpoints", tags = {"Drivers"})
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers(){
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long driverId){
        return ResponseEntity.ok(driverService.getDriver(driverId));
    }
    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver){
        return ResponseEntity.ok(driverService.saveDriver(driver));
    }
    @PutMapping("/{driverId}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long driverId, @RequestBody Driver driver){
        return ResponseEntity.ok(driverService.updateDriver(driverId,driver));
    }
    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long driverId){
        driverService.deleteDriver(driverId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/available-drivers")
    public ResponseEntity<List<Driver>> getAvailableDrivers(){
        return ResponseEntity.ok(driverService.getAvailableDrivers());
    }


}
