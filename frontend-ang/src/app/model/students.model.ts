//structure student cote front end
export interface Student{
  id:string,
  code:string,
  firstname:string,
  lastname:string,
  programId:string,
  photo:string
}
//structure payment cote front end
export interface Payment{
  id:number,
  date:string,
  amount:number,
  type:string,
  status:string,
  file:string,
  student:Student


}
//un énumérateur
export enum PaymentType{
  CASH,TRANSFER,DEPOSIT,CHECK
}
export enum PaymentStatus{
  CREATED,VALIDATED,REJECTED
}
