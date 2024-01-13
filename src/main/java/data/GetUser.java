package data;

import models.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetUser {

    public static User getValidUser(){
        return new User("marioly_vv_" + (int)(Math.random() * (1999 - 100 + 1) + 100) + "@gmail.com", "marioly123", "Calle 7 de Irpavi", "La Paz", "Murillo", "0000", "Bolivia", "72003634", "Marioly", "Vargas", "06/09/1999");
    }

    public static User getInvalidUser(){
        return new User("marioly-bolivia@ucb.edu.com", "mariolyBolivia123marioly", "Calle 7 de Irpavi", "La Paz", "Murillo", "0000", "Bolivia", "72003634", "Marioly", "Vargas", "06/09/1999");
    }

    public static User getUserWithInvalidDateOfBirth(){
        User user = getValidUser();
        LocalDate currentDate = LocalDate.now();
        String invalidDateOfBirth = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        user.setDateOfBirth(invalidDateOfBirth);
        return user;
    }

}
