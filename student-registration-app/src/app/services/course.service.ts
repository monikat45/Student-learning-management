import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CourseService {

    private apiUrl = '/api/admin';

    constructor(private http: HttpClient) { }

    createCourse(courseData: any): Observable<any> {
        return this.http.post(`${this.apiUrl}/createCourse`, courseData);
    }
}
