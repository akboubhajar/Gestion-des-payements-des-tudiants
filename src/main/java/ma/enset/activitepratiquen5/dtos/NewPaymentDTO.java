package ma.enset.activitepratiquen5.dtos;

import lombok.*;
import ma.enset.activitepratiquen5.entities.PaymentType;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NewPaymentDTO {

    private double  amount;
    private PaymentType type;
    private LocalDate date;

    private String studentCode;

}
