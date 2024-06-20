import {Component, OnInit, ViewChild} from '@angular/core';
import {StudentsService} from "../services/students.service";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {Student} from "../model/students.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrl: './students.component.css'
})
export class StudentsComponent implements OnInit{
  students!:Array<Student>;
  //public dataSource: any;
  public studentsdataSource ! : MatTableDataSource<Student>;
  public displayedColumns=['id','firstname','lastname','code','programId','Payments'];

  @ViewChild(MatPaginator) paginator! : MatPaginator;
  @ViewChild(MatSort) sort! : MatSort;

  constructor(private studentsService:StudentsService,private router:Router ) {
  }
  ngOnInit() {
    this.studentsService.getAllStudents()
      .subscribe(
       data =>{
         this.students=data;
         this.studentsdataSource=new MatTableDataSource<Student>(this.students)
         this.studentsdataSource.paginator=this.paginator;
         this.studentsdataSource.sort=this.sort;

      },
      err =>{
         console.log(err);

    }
    )
  }

  studentspayments(student: Student) {
this.router.navigateByUrl('/admin/student-details/'+student.code);
  }
}
