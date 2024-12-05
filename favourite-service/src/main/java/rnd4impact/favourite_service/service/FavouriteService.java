package rnd4impact.favourite_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import rnd4impact.favourite_service.dto.FavouriteDTO;
import rnd4impact.favourite_service.entity.FavouriteEntity;
import rnd4impact.favourite_service.repository.FavouriteRepository;
import rnd4impact.favourite_service.service.imp.FavouriteServiceImp;

import java.util.ArrayList;
import java.util.List;

public class FavouriteService implements FavouriteServiceImp {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Override
    public List<FavouriteDTO> getAllFavourites() {
       List<FavouriteDTO> dtoList = new ArrayList<>();
        List<FavouriteEntity> list = favouriteRepository.findAll();
        for (FavouriteEntity item: list) {
            FavouriteDTO favouriteDTO = new FavouriteDTO();
            favouriteDTO.setUserId(item.getUserId());
            favouriteDTO.setProductId(item.getProductId());
            dtoList.add(favouriteDTO);
        }
        return dtoList;
    }

    @Override
    public List<FavouriteDTO> getFavouriteByUserId(int id) {
        List<FavouriteDTO> dtoList = new ArrayList<>();
        List<FavouriteEntity> list = favouriteRepository.findLikesByUserId(id);
        for (FavouriteEntity item: list) {
            FavouriteDTO favouriteDTO = new FavouriteDTO();
            favouriteDTO.setUserId(item.getUserId());
            favouriteDTO.setProductId(item.getProductId());
            dtoList.add(favouriteDTO);
        }
        return dtoList;
    }

    @Override
    public List<FavouriteDTO> getFavouriteByProductId(int id) {
        List<FavouriteDTO> dtoList = new ArrayList<>();
        List<FavouriteEntity> list = favouriteRepository.findLikesByProductId(id);
        for (FavouriteEntity item: list) {
            FavouriteDTO favouriteDTO = new FavouriteDTO();
            favouriteDTO.setUserId(item.getUserId());
            favouriteDTO.setProductId(item.getProductId());
            dtoList.add(favouriteDTO);
        }
        return dtoList;
    }

    @Override
    public void deleteFavouriteByUserId(int id) {
        try {
            favouriteRepository.deleteAllByUserId(id);
        }catch(Exception e) {
            throw new RuntimeException("Error deleting favourite with userId" + id, e);
        }
    }

    @Override
    public void deleteFavouriteByProductId(int id) {
        try {
            favouriteRepository.deleteAllByProductId(id);
        }catch(Exception e) {
            throw new RuntimeException("Error deleting favourite with userId" + id, e);
        }
    }
}
