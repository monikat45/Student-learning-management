// student-registration.component.ts
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { StudentService } from '../services/student.service';
import { Inject } from '@angular/core';

@Component({
  selector: 'app-student-registration',
  templateUrl: './student-registration.component.html',
  styleUrls: ['./student-registration.component.css']
})
export class StudentRegistrationComponent {

  registrationForm: FormGroup;

  constructor(private fb: FormBuilder, @Inject(StudentService) private studentService: StudentService) {
    this.registrationForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      dob: ['', Validators.required],
      address: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.minLength(10)]],
    });
  }

  onSubmit() {
    if (this.registrationForm.valid) {
      const studentData = this.registrationForm.value;
      this.studentService.registerStudent(studentData).subscribe(
        response => {
          console.log('Registration successful');
          // Handle success, e.g., redirect to a confirmation page
        },
        error => {
          console.error('Registration failed:', error);
          // Handle error, e.g., display an error message
        }
      );
    }
  }
}
