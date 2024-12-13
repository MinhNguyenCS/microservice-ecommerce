package rnd4impact.favourite_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import rnd4impact.favourite_service.dto.FavouriteDTO;
import rnd4impact.favourite_service.entity.FavouriteEntity;
import rnd4impact.favourite_service.repository.FavouriteRepository;
import rnd4impact.favourite_service.service.imp.FavouriteServiceImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteService implements FavouriteServiceImp {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Override
    public List<FavouriteDTO> getAllFavourites() {
        List<FavouriteDTO> dtoList = new ArrayList<>();
        List<FavouriteEntity> entityList = favouriteRepository.findAll();

        for (FavouriteEntity item : entityList) {
            FavouriteDTO favouriteDTO = new FavouriteDTO();
            favouriteDTO.setUserId(item.getId().getUserId());
            favouriteDTO.setProductId(item.getId().getProductId());
            dtoList.add(favouriteDTO);
        }

        return dtoList;
    }

    @Override
    public List<FavouriteDTO> getFavouriteByUserId(int id) {
        List<FavouriteDTO> dtoList = new ArrayList<>();
        List<FavouriteEntity> list = favouriteRepository.findByIdUserId(id);
        for (FavouriteEntity item: list) {
            FavouriteDTO favouriteDTO = new FavouriteDTO();
            favouriteDTO.setUserId(item.getId().getUserId());
            favouriteDTO.setProductId(item.getId().getProductId());
            dtoList.add(favouriteDTO);
        }
        return dtoList;
    }

    @Override
    public List<FavouriteDTO> getFavouriteByProductId(int id) {
        List<FavouriteDTO> dtoList = new ArrayList<>();
        List<FavouriteEntity> list = favouriteRepository.findByIdProductId(id);
        for (FavouriteEntity item: list) {
            FavouriteDTO favouriteDTO = new FavouriteDTO();
            favouriteDTO.setUserId(item.getId().getUserId());
            favouriteDTO.setProductId(item.getId().getProductId());
            dtoList.add(favouriteDTO);
        }
        return dtoList;
    }

    @Override
    public void deleteFavouriteByUserId(int id) {
        try {
            favouriteRepository.deleteByIdUserId(id);
        }catch(Exception e) {
            throw new RuntimeException("Error deleting favourite with userId" + id, e);
        }
    }

    @Override
    public void deleteFavouriteByProductId(int id) {
        try {
            favouriteRepository.deleteByIdProductId(id);
        }catch(Exception e) {
            throw new RuntimeException("Error deleting favourite with userId" + id, e);
        }
    }
}
