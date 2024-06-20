import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {observableToBeFn} from "rxjs/internal/testing/TestScheduler";
import {Observable} from "rxjs";
import {Payment, Student} from "../model/students.model";

@Injectable({
  providedIn: 'root'
})
export class StudentsService {

  constructor(private http:HttpClient) { }

  public getAllPayements():Observable<Array<Payment>>
  {
    //return this.http.get(environment.backendHost+"/payments");
    return this.http.get<Array<Payment>>(`${environment.backendHost}/payments`);
  }
  public getAllStudents():Observable<Array<Student>>
  {
   return this.http.get<Array<Student>>(`${environment.backendHost}/students`);
  }

  public getStudentPayments(code :string):Observable<Array<Payment>>
  {
    return this.http.get<Array<Payment>>(`${environment.backendHost}/students/${code}/payments`);
  }

  public savePayments(formData :any):Observable<Array<Payment>>
  {
    return this.http.post<Array<Payment>>(`${environment.backendHost}/payments`,formData);
  }

  getPaymentDetails(paymentId: number) {
    //ce n'est des donnes json c'est un fichier
    return this.http.get(`${environment.backendHost}/paymentFile/${paymentId}`,{responseType:'blob'});

  }
}
