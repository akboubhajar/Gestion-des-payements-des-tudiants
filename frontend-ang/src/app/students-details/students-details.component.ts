import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {StudentsService} from "../services/students.service";
import {Payment} from "../model/students.model";
import {MatTableDataSource} from "@angular/material/table";


@Component({
  selector: 'app-students-details',
  templateUrl: './students-details.component.html',
  styleUrl: './students-details.component.css'
})
export class StudentsDetailsComponent implements OnInit{
  studentCode! : string;
  studentPayments! :Array<Payment>;
  paymentsDataSource! :MatTableDataSource<Payment>;
  public displayedColumns=['id','date','amount','type','status','firstname','details'];



 constructor(private activatedRoute:ActivatedRoute,private studentService :StudentsService ,private router:Router) {
 }
 ngOnInit() {
   this.studentCode=this.activatedRoute.snapshot.params['code'];
   this.studentService.getStudentPayments(this.studentCode)
     .subscribe(

         value=>{
           this.studentPayments=value;
           this.paymentsDataSource=new MatTableDataSource<Payment>(this.studentPayments);


   },
       err=>{
           console.log(err);
       }

     )

 }

  newPayment() {

  this.router.navigateByUrl(`/admin/new-payment/${this.studentCode}`)
  }

  paymentDetails(element:any) {
   this.router.navigateByUrl(`/admin/payment-details/${element.id}`)

  }
}
