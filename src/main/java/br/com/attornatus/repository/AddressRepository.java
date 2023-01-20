package br.com.attornatus.repository;

import br.com.attornatus.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address,Integer> {

    @Query(value = "SELECT * FROM TB_ADDRESS WHERE ID_PERSON =  ?1 ", nativeQuery = true)
    List<Address> findByPersonId(Integer idPerson);


}
