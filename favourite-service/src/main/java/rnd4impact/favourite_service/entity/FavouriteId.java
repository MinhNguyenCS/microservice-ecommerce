package rnd4impact.favourite_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class FavouriteId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "product_id")
    private int productId;

}