package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInformation {
    private final String city;
    private final String name;
    private final String phone;
}