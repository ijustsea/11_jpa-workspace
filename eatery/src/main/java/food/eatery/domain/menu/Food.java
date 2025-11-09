package food.eatery.domain.menu;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("food")
public class Food extends Menu{
    private String spicy;
    private boolean extra;

}
