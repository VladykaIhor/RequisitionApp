package com.ivladyka.requisitionappapiusers.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRequestDTO {

    private String email;

    private String phoneNumber;
}
