package rnd4impact.favourite_service.controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rnd4impact.favourite_service.dto.FavouriteDTO;
import rnd4impact.favourite_service.payload.response.BaseResponse;
import rnd4impact.favourite_service.service.imp.FavouriteServiceImp;

import java.io.Serializable;
import java.util.List;

@CrossOrigin
@RequestMapping("/favourite")
@RestController
public class FavouriteController {

    @Autowired
    private FavouriteServiceImp favouriteServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllFavourite() {
        List<FavouriteDTO> list = favouriteServiceImp.getAllFavourites();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/by-user/{id}")
    public ResponseEntity<?> getFavouriteByUser(@PathVariable int id) {
        List<FavouriteDTO> list = favouriteServiceImp.getFavouriteByUserId(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/by-product/{id}")
    public ResponseEntity<?> getFavouriteByProduct(@PathVariable int id) {
        List<FavouriteDTO> list = favouriteServiceImp.getFavouriteByProductId(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(list);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/delete/by-user/{id}")
    public ResponseEntity<?> deleteByUser(@PathVariable int id) {
        favouriteServiceImp.deleteFavouriteByUserId(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PostMapping("/delete/by-product/{id}")
    public ResponseEntity<?> deleteByProduct(@PathVariable int id) {
        favouriteServiceImp.deleteFavouriteByProductId(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
