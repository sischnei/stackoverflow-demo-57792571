package nl.martinvw.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Autowired
    public DemoService(DemoConfiguration.MainUIFrame demoInject) {
        System.out.println(demoInject);
    }
}
