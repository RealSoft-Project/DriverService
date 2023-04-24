package realsoft.carserviec.driverservice.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import realsoft.carserviec.driverservice.entity.Driver;
import realsoft.carserviec.driverservice.repo.DriverRepository;

import java.util.Optional;

@Component
@Slf4j
public class Listeners {
    private final DriverRepository repository;

    public Listeners(DriverRepository repository) {
        this.repository = repository;
    }
    @RabbitListener(queues = "driver.assign")
    public void assignDriver(@Payload Long driverId){
        log.info("Received from Rabbit mq driver.assign queue");
        Optional<Driver> driver = repository.findById(driverId);
        driver.ifPresent(value -> value.setIsAssigned(true));
        repository.save(driver.get());
    }

    @RabbitListener(queues = "driver.unassign")
    public void unAssignCar(@Payload Long driverId){
        log.info("Received from Rabbit mq driver.unassign queue");
        Optional<Driver> driver = repository.findById(driverId);
        driver.ifPresent(value -> value.setIsAssigned(false));
        repository.save(driver.get());
    }
}
