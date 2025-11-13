package food.eatery.repository;

import food.eatery.domain.ReservStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservSearch {
    private String customerName;//고객명
    private String customerPhone;//.고객번호
    private ReservStatus reservStatus;
}
