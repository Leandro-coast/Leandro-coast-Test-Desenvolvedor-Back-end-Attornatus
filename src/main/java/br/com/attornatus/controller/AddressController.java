package br.com.attornatus.controller;

import br.com.attornatus.model.Address;
import br.com.attornatus.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @PostMapping("/person/address")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {

        try {
            return new ResponseEntity<>(addressRepository.save(address), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<Address> putAddress(@RequestBody Address address) {

        try {
            return new ResponseEntity<>(addressRepository.save(address), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("all/address")
    public ResponseEntity<List<Address>> getAllAddress() {

        try {
            List<Address> addressList = addressRepository.findAll();

            return addressList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(addressList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/person/{id}/address/")
    public ResponseEntity<List<Address>> getByPersonId(@PathVariable("id") int id) {

        try {
            List<Address> addressList = addressRepository.findByPersonId(id);

            return addressList.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(addressList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/person/address/{id}/leading")
    public ResponseEntity<List<Address>> setLeadingAddress(@RequestBody Address address) {

        try {

            List<Address> listToSave = new ArrayList<>();
            List<Address> addressList = addressRepository.findByPersonId(address.getIdPerson());

            for (Address addr : addressList) {
                if (addr.getId() == address.getId() ){
                    addr.setLeading(true);
                }else {
                    addr.setLeading(false);
                }
                listToSave.add(addr);
            }
            return new ResponseEntity<List<Address>>(addressRepository.saveAll(listToSave), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
