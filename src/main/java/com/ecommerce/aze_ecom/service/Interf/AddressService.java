package com.ecommerce.aze_ecom.service.Interf;

import com.ecommerce.aze_ecom.beans.User;
import com.ecommerce.aze_ecom.playload.AddressDTO;

public interface AddressService {
    AddressDTO creatAddress(AddressDTO addressDTO, User user);
}
