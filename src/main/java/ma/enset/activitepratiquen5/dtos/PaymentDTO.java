package ma.enset.activitepratiquen5.dtos;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.activitepratiquen5.entities.PaymentStatus;
import ma.enset.activitepratiquen5.entities.PaymentType;
import ma.enset.activitepratiquen5.entities.Student;

import java.time.LocalDate;

public class PaymentDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString

    public class Payment {

        private Long id;
        private LocalDate date ;
        private  double amount;
        private PaymentType type;
        private PaymentStatus status;


    }

}
