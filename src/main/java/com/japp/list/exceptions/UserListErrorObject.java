package com.japp.list.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class UserListErrorObject {
    private Date timestamp;
    private String message;
    private String details;
}
