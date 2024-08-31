package com.ecommerce.aze_ecom.controller;

import com.ecommerce.aze_ecom.Util.AuthUtil;
import com.ecommerce.aze_ecom.beans.User;
import com.ecommerce.aze_ecom.playload.AddressDTO;
import com.ecommerce.aze_ecom.service.Interf.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddresses(){
        List<AddressDTO> addresses = addressService.getAllAddresses();
        return new ResponseEntity<List<AddressDTO>>(addresses, HttpStatus.FOUND);
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId){
        AddressDTO addressDTO = addressService.getAddressById(addressId);
        return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.FOUND);
    }

    @GetMapping("/user/address")
    public ResponseEntity<List<AddressDTO>> getUserAddress(){
        User user = authUtil.loggedInUser();
        List<AddressDTO> addressDTOS = addressService.getUserAddress(user);
        return new ResponseEntity<List<AddressDTO>>(addressDTOS, HttpStatus.FOUND);
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> creatAddress(@Valid @RequestBody AddressDTO addressDTO){
        User user = authUtil.loggedInUser();
        AddressDTO creatAddressDTO = addressService.creatAddress(addressDTO,user);
        return new ResponseEntity<AddressDTO>(creatAddressDTO, HttpStatus.CREATED);
    }

    @PutMapping("/address/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long addressId,@RequestBody AddressDTO addressDTO){
        AddressDTO UpdatedAddress = addressService.updateAddress(addressId,addressDTO);
        return new ResponseEntity<AddressDTO>(UpdatedAddress, HttpStatus.OK);
    }
}
