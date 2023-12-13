import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StudentService } from '../services/student.service';

@Component({
    selector: 'app-student',
    templateUrl: './student.component.html',
    styleUrls: ['./student.component.css']
})
export class StudentComponent {

    @Input() studentId: number;

    logEntryForm: FormGroup;

    constructor(private fb: FormBuilder, private studentService: StudentService) {
        this.logEntryForm = this.fb.group({
            date: [new Date(), Validators.required],
            category: ['', Validators.required],
            description: ['', Validators.required],
            timeSpent: ['', Validators.required],
        });
    }

    onLogHours() {
        if (this.logEntryForm.valid) {
            const logEntryData = this.logEntryForm.value;
            this.studentService.logHours(this.studentId, logEntryData).subscribe(
                response => {
                    console.log('Log entry successful');
                    // Handle success, e.g., display a success message
                },
                error => {
                    console.error('Log entry failed:', error);
                    // Handle error, e.g., display an error message
                }
            );
        }
    }
}
