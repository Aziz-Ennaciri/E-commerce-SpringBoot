package com.ecommerce.aze_ecom.mappers;

import com.ecommerce.aze_ecom.beans.Address;
import com.ecommerce.aze_ecom.DTOs.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toDto(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setBuildingName(address.getBuildingName());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setPincode(address.getPincode());
        return addressDTO;
    }
    public Address toEntity(AddressDTO addressDTO){
        Address address = new Address();
        address.setAddressId(addressDTO.getAddressId());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingName(addressDTO.getBuildingName());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setPincode(addressDTO.getPincode());
        return address;
    }

}
