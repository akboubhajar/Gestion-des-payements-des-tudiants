import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ProfileComponent} from "./profile/profile.component";
import {LoginComponent} from "./login/login.component";
import {LoadStudentsComponent} from "./load-students/load-students.component";
import {LoadPaymentsComponent} from "./load-payments/load-payments.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {StudentsComponent} from "./students/students.component";
import {PaymentsComponent} from "./payments/payments.component";
import {AdminTemplateComponent} from "./admin-template/admin-template.component";
import {AuthGuard} from "./guards/authentication.guard";
import {authorizationGuard} from "./guards/authorization.guard";
import {StudentsDetailsComponent} from "./students-details/students-details.component";
import {NewPaymentComponent} from "./new-payment/new-payment.component";
import {PaymentDetailsComponent} from "./payment-details/payment-details.component";

const routes: Routes = [

  {path:"" ,component:LoginComponent},
  {path:"login" ,component:LoginComponent},
  {path:"admin" ,component:AdminTemplateComponent ,
    canActivate:[AuthGuard]
    ,children:[
      {path:"profile" ,component:ProfileComponent},
      {path:"home" ,component:HomeComponent},
      {path:"loadStudents" ,component:LoadStudentsComponent,
      canActivate:[authorizationGuard],data:{roles:['ADMIN']}},
      {path:"loadPayments" ,component:LoadPaymentsComponent
      ,canActivate:[authorizationGuard],data:{roles:['ADMIN']}},
      {path:"dashboard" ,component:DashboardComponent},
      {path:"students" ,component:StudentsComponent},
      {path:"payments" ,component:PaymentsComponent},
      {path:"student-details/:code" ,component:StudentsDetailsComponent},
      {path:"new-payment/:code" ,component:NewPaymentComponent},
      {path:"payment-details/:id" ,component:PaymentDetailsComponent},
    ]},




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
