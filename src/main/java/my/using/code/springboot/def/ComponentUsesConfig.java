package my.using.code.springboot.def;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentUsesConfig {
    private Config config;
    @Autowired
    public ComponentUsesConfig(Config config) {
         this.config=config;
    }

    // Other methods for bot functionality can be defined here

}
