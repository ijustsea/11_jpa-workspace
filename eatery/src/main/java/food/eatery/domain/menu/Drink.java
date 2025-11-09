package food.eatery.domain.menu;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("drink")
public class Drink extends Menu{
    private boolean alcohol;
    private boolean zero;
}
