package ma.enset.activitepratiquen5.services;

import jakarta.transaction.Transactional;
import ma.enset.activitepratiquen5.dtos.NewPaymentDTO;
import ma.enset.activitepratiquen5.entities.Payment;
import ma.enset.activitepratiquen5.entities.PaymentStatus;
import ma.enset.activitepratiquen5.entities.PaymentType;
import ma.enset.activitepratiquen5.entities.Student;
import ma.enset.activitepratiquen5.repository.PaymentRepository;
import ma.enset.activitepratiquen5.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    PaymentRepository paymentRepository;
    StudentRepository studentRepository;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }
    public Payment savePayment(MultipartFile file, NewPaymentDTO newpaymentDTO) throws IOException {
        //where the folder is sent
        Path forlderPath= Paths.get(System.getProperty("user.home"),"enset-data","payments");
        //if not exist
        if(!Files.exists(forlderPath))
        {
            Files.createDirectories(forlderPath);
        }
        //name file uinique
        ////where the file is sent
        String fileName = UUID.randomUUID().toString();
        Path filePath =Paths.get(System.getProperty("user.home"),"enset-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);

        Student student =studentRepository.findByCode(newpaymentDTO.getStudentCode());

        Payment payment =Payment.builder().date(newpaymentDTO.getDate() )
                .type(newpaymentDTO.getType()).student(student)
                .amount(newpaymentDTO.getAmount())
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);

    }

  /*  public Payment savePayment(MultipartFile file, LocalDate date , PaymentType type, String student_code , Double amount) throws IOException {
        //where the folder is sent
        Path forlderPath= Paths.get(System.getProperty("user.home"),"enset-data","payments");
        //if not exist
        if(!Files.exists(forlderPath))
        {
            Files.createDirectories(forlderPath);
        }
        //name file uinique
        ////where the file is sent
        String fileName = UUID.randomUUID().toString();
        Path filePath =Paths.get(System.getProperty("user.home"),"enset-data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);

        Student student =studentRepository.findByCode(student_code);

        Payment payment =Payment.builder().date(date)
                .type(type).student(student)
                .amount(amount)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);

    }

*/
    public byte[] getPaymentFile(Long paymentId) throws IOException {
        Payment payment=paymentRepository.findById(paymentId).get();


        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }


    public  Payment updatePaymentStatus(PaymentStatus status , Long id )
    {
        Payment payment_students =paymentRepository.findById(id).get();
        payment_students.setStatus(status);
        return paymentRepository.save(payment_students);


    }


}
