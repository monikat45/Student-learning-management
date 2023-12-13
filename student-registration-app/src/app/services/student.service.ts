// student.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private apiUrl = 'http://localhost:8081/api/students';

  constructor(private http: HttpClient) { }

  registerStudent(studentData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, studentData);
  }
  logHours(studentData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/loghours`, studentData);
  }

  getLogEntries(studentData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/loghours`, studentData);
  }
  deleteLog(studentData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/loghours`, studentData);
  }
}
