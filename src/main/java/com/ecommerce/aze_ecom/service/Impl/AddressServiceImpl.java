package com.ecommerce.aze_ecom.service.Impl;

import com.ecommerce.aze_ecom.beans.Address;
import com.ecommerce.aze_ecom.beans.User;
import com.ecommerce.aze_ecom.exceptions.APIException;
import com.ecommerce.aze_ecom.exceptions.ResourceNotFoundException;
import com.ecommerce.aze_ecom.mappers.AddressMapper;
import com.ecommerce.aze_ecom.DTOs.AddressDTO;
import com.ecommerce.aze_ecom.repositories.AddressRepository;
import com.ecommerce.aze_ecom.repositories.UserRepository;
import com.ecommerce.aze_ecom.service.Interf.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AddressDTO creatAddress(AddressDTO addressDTO, User user) {
        Address address = addressMapper.toEntity(addressDTO);
        address.setUser(user);
        List<Address> addresses = user.getAddresses();
        addresses.add(address);
        user.setAddresses(addresses);
        Address savedAddress = addressRepository.save(address);
    return addressMapper.toDto(savedAddress);
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        if (addresses.isEmpty()){
            throw new APIException("No Addresses exists");
        }
        return addresses.stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(()->new ResourceNotFoundException("Address","addressId",addressId));
        return addressMapper.toDto(address);
    }

    @Override
    public List<AddressDTO> getUserAddress(User user) {
        List<Address> addresses = user.getAddresses();
        if (addresses.isEmpty()){
            throw new APIException("User has no Address");
        }
        return addresses.stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(Long addressId, AddressDTO addressDTO) {
        Address savedAddress = addressRepository.findById(addressId).orElseThrow(()->new ResourceNotFoundException("Address","addressId",addressId));

        savedAddress.setStreet(addressDTO.getStreet());
        savedAddress.setBuildingName(addressDTO.getBuildingName());
        savedAddress.setCity(addressDTO.getCity());
        savedAddress.setState(addressDTO.getState());
        savedAddress.setCountry(addressDTO.getCountry());
        savedAddress.setPincode(addressDTO.getPincode());

        Address updatedAddress = addressRepository.save(savedAddress);
        User user = savedAddress.getUser();
        user.getAddresses().removeIf(address->address.getAddressId().equals(addressId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);
        return addressMapper.toDto(updatedAddress);
    }

    @Override
    public String deleteAddress(Long addressId) {
        Address savedAddress = addressRepository.findById(addressId).orElseThrow(()->new ResourceNotFoundException("Address","addressId",addressId));
        User user = savedAddress.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.delete(user);
        addressRepository.delete(savedAddress);
        return "Address deleted successfully with addressId"+addressId;
    }

}
