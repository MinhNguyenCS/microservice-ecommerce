package rnd4impact.favourite_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnd4impact.favourite_service.entity.FavouriteEntity;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Integer> {

}
