package uz.karkas.building;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.karkas.building.config.swagger.ApiProperties;

@SpringBootTest
class BuildingApplicationTests {

    @Autowired
    public ApiProperties properties;
    @Test
    void contextLoads() {
        System.out.println(properties.getApi());
    }

}
