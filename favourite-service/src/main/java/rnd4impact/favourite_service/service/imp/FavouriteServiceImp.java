package rnd4impact.favourite_service.service.imp;

import rnd4impact.favourite_service.dto.FavouriteDTO;

import java.util.List;

public interface FavouriteServiceImp {
    List<FavouriteDTO> getAllFavourites();
    List<FavouriteDTO> getFavouriteByUserId(int id);
    List<FavouriteDTO> getFavouriteByProductId(int id);
    void deleteFavouriteByUserId(int id );
    void deleteFavouriteByProductId(int id);
}
