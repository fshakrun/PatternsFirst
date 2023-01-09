package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {
    @UtilityClass
    public static class Registration {

        public UserInformation generateByCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new UserInformation (faker.address().cityName(),
                    faker.name().fullName(),
                    faker.numerify("+7##########"));
        }
    }

    public String generateTheDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}