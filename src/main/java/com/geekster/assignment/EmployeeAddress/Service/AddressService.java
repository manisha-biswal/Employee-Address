package com.geekster.assignment.EmployeeAddress.Service;

import com.geekster.assignment.EmployeeAddress.Model.Address;
import com.geekster.assignment.EmployeeAddress.Repository.IAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final IAddressRepo addressRepository;

    @Autowired
    public AddressService(IAddressRepo addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            existingAddress.setStreet(updatedAddress.getStreet());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setState(updatedAddress.getState());
            existingAddress.setZipCode(updatedAddress.getZipCode());
            return addressRepository.save(existingAddress);
        } else {
            return null; // Address with the given ID not found
        }
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
