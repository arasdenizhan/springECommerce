package com.example.yeczane.dto.populator;

import com.example.yeczane.dto.UsersDto;
import com.example.yeczane.model.CustomerInfo;
import com.example.yeczane.model.Users;

public class UserPopulator {
    private UserPopulator(){
        throw new UnsupportedOperationException();
    }

    public static UsersDto populateDto(Users users){
        return new UsersDto(
                users.getId(),
                users.getUsername(),
                users.getPassword(),
                users.getEmail()
        );
    }

    public static UsersDto populateCustomerInfo(UsersDto usersDto, CustomerInfo customerInfo){
        if(customerInfo!=null){
            usersDto.setCustomerName(customerInfo.getCustomerName());
            usersDto.setCustomerAddress(customerInfo.getCustomerAddress());
            usersDto.setCustomerPhone(customerInfo.getCustomerPhone());
        }
        return usersDto;
    }
}
