package rnd4impact.favourite_service.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class FavouriteDTO {

    private int userId;
    private int productId;
}
