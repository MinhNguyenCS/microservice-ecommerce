package rnd4impact.favourite_service.controller;


import lombok.Data;

import java.io.Serializable;

@Data
public class FavouriteController implements Serializable {

    private int userId;

    private int productId;
}
