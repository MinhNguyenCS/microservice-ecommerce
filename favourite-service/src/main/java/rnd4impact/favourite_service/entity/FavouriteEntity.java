package rnd4impact.favourite_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "likes")
public class FavouriteEntity {

    @EmbeddedId
    private FavouriteId id;

/*    @Id
    @JoinColumn(name = "user_id", nullable = false)
    private int userId;

    @Id
    @JoinColumn(name = "product_id", nullable = false)
    private int productId;*/

    @Column(name = "like_date")
    private LocalDateTime likeDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
