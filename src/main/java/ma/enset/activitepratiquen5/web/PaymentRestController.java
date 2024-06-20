package ma.enset.activitepratiquen5.web;

import lombok.AllArgsConstructor;
import ma.enset.activitepratiquen5.dtos.NewPaymentDTO;
import ma.enset.activitepratiquen5.entities.Payment;
import ma.enset.activitepratiquen5.entities.PaymentStatus;
import ma.enset.activitepratiquen5.entities.PaymentType;
import ma.enset.activitepratiquen5.services.PaymentService;
import ma.enset.activitepratiquen5.entities.Student;
import ma.enset.activitepratiquen5.repository.PaymentRepository;
import ma.enset.activitepratiquen5.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.MediaTray;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class PaymentRestController {
    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private PaymentService paymentService;

    public PaymentRestController(PaymentRepository paymentRepository, StudentRepository studentRepository,PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.paymentService=paymentService;
    }
    @GetMapping(path = "/payments")
    public List<Payment> allPayments()
    {
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> paymentsByStudents(@PathVariable String code)
    {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path = "/payments/byStatus")
    public List<Payment> paymentsByStatus(@RequestParam PaymentStatus status)
    {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/payments/byType")
    public List<Payment> paymentsByType(@RequestParam PaymentType type)
    {
        return paymentRepository.findByType(type);
    }


    @GetMapping(path = "/payments/{id}")
    public  Payment getPaymentById(@PathVariable Long id)
    {
        return paymentRepository.findById(id).get();
    }


    @GetMapping(path = "/students")
    public  List<Student> allStudents()
    {
        return studentRepository.findAll();
    }


    @GetMapping(path = "/students/{code}")
    public Student getStudentByCode (@PathVariable String code)
    {
        return studentRepository.findByCode(code);
    }

    /*@GetMapping(path = "/studentsByProgramId/{programId}")

    public List<Student> getStudentsByProgramId(String programId)
    {
        return studentRepository.findByProgramId(programId);
    } or -------------------*/
    @GetMapping(path = "/studentsByProgramId")

    public List<Student> getStudentsByProgramId(@RequestParam String programId)
    {
        return studentRepository.findByProgramId(programId);
    }

    @PutMapping ("/payments/{id}")
    public  Payment updatePaymentStatus(@RequestParam PaymentStatus status ,@PathVariable  Long id )
    {
       /* Payment payment_students =paymentRepository.findById(id).get();
        payment_students.setStatus(status);
        return paymentRepository.save(payment_students);*/
       return this.paymentService.updatePaymentStatus(status,id);


    }
    //MediaType.MULTIPART_FORM_DATA_VALUE for there is structured ,data there are files
    @PostMapping(path="/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Payment savePayment(@RequestParam("file") MultipartFile file, NewPaymentDTO newpaymentDTO) throws IOException {
        /*//where the folder is sent
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
        Files.copy(file_.getInputStream(),filePath);

        Student student =studentRepository.findByCode(student_code);

        Payment payment =Payment.builder().date(date)
                .type(type).student(student)
                .amount(amount)
                //toUri for path file
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();*/
        return  this.paymentService.savePayment(file,newpaymentDTO);


    }
//MediaType.APPLICATION_PDF_VALUE indicates that it is a file pdf
@GetMapping(path = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
    //To consult file
//byte data file
    public byte[] getPaymentFile(@PathVariable  Long paymentId) throws IOException {
 /*     Payment payment=paymentRepository.findById(paymentId).get();
      .0return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
*/
    return  this.paymentService.getPaymentFile(paymentId);
    }


}
