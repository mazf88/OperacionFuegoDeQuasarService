package co.com.meli.fuegoquasar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.meli.fuegoquasar.entities.Satellite;

public interface SatelliteRepository extends JpaRepository<Satellite, Integer> {

}