package rnd4impact.favourite_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rnd4impact.favourite_service.entity.FavouriteEntity;
import rnd4impact.favourite_service.entity.FavouriteId;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<FavouriteEntity, FavouriteId> {
    // Find likes by user ID
    List<FavouriteEntity> findByIdUserId(int userId);

    // Find likes by product ID
    List<FavouriteEntity> findByIdProductId(int productId);

    // Delete all likes by user ID
    void deleteByIdUserId(int userId);

    // Delete all likes by product ID
    void deleteByIdProductId(int productId);
}
