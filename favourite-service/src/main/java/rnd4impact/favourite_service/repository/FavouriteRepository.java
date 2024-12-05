package rnd4impact.favourite_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnd4impact.favourite_service.entity.FavouriteEntity;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity, Integer> {
    List<FavouriteEntity> findLikesByUserId(int id);
    List<FavouriteEntity> findLikesByProductId(int id);
    void deleteAllByUserId(int id);
    void deleteAllByProductId(int id);
}
