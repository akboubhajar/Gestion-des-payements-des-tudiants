package ma.enset.activitepratiquen5;

import ma.enset.activitepratiquen5.entities.Payment;
import ma.enset.activitepratiquen5.entities.PaymentStatus;
import ma.enset.activitepratiquen5.entities.PaymentType;
import ma.enset.activitepratiquen5.entities.Student;
import ma.enset.activitepratiquen5.repository.PaymentRepository;
import ma.enset.activitepratiquen5.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class ActivitePratiqueN5Application {



    public static void main(String[] args) {
        SpringApplication.run(ActivitePratiqueN5Application.class, args);
    }




    @Bean
    CommandLineRunner commandLineRunner1(StudentRepository studentRepository, PaymentRepository paymentRepository)
    {
        return args->
        {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("khaoula").code("112233").programId("SDIA")
                    .build());

            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("khaoula").code("112244").programId("SDIA")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("hamza").code("112255").programId("GLSID")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("hamza").code("112266").programId("BDCC")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                    .firstname("khaoula").code("112277").programId("BDCC")
                    .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                .firstname("khaoula").code("112288").programId("BDCC")
                .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                .firstname("ayman").code("112299").programId("BDCC")
                .build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
                .firstname("mohammed").code("112245").programId("BDCC")
                .build());

            PaymentType[] paymentTypes =PaymentType.values();
            Random random=new Random();
            //create 10 payments for each student
            studentRepository.findAll().forEach(student -> {
                for (int i=0;i<10;i++)
                {

                    int index = random.nextInt(paymentTypes.length);// between 0 et paymentTypes.length
                    Payment payment =Payment.builder()
                                   .amount(1000+(int)(Math.random()*20000))
                                    .type(paymentTypes[index])
                                   .status(PaymentStatus.CREATED)
                                    .date(LocalDate.now())
                                   .student(student)
                                   .build();
                    paymentRepository.save(payment);


                }
            });
        };
    }
}
