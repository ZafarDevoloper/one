    package com.example.one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneApplication.class, args);
    }
/**
 * Company malumotlarini service, controller yordamida ResposeEntity qaytaradigan to'liq
 * REST full API yozing. Bunda Address(street, homeNumber) Company(corpName, directorName,
 * Address) DepartmentRepository(name, Company) WorkerRepository(name, phoneNumber, Address,
 * DepartmentRepository) malumotlari bo'lsin. Proyektni git ga yuklab, javob sifatida linkni yuboring.
 */
}
