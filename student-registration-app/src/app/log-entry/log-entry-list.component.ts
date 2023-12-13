import { Component, Input } from '@angular/core';
import { StudentService } from '../services/student.service';

@Component({
    selector: 'app-log-entry-list',
    templateUrl: './log-entry-list.component.html',
    styleUrls: ['./log-entry-list.component.css']
})
export class LogEntryListComponent {

    @Input() studentId: number;

    logEntries: any[]; // Change the type based on your actual data structure

    constructor(private studentService: StudentService) {
        // Fetch log entries for the student on component initialization
        this.fetchLogEntries();
    }

    fetchLogEntries() {
        this.studentService.getLogEntries(this.studentId).subscribe(
            data => {
                this.logEntries = data;
            },
            error => {
                console.error('Error fetching log entries:', error);
            }
        );
    }

    onUpdate(logEntryId: number, updatedData: any) {
        this.studentService.updateLog(this.studentId, logEntryId, updatedData).subscribe(
            response => {
                console.log('Log entry updated successfully');
                this.fetchLogEntries(); // Refresh log entries after update
            },
            error => {
                console.error('Log entry update failed:', error);
                // Handle error, e.g., display an error message
            }
        );
    }

    onDelete(logEntryId: number) {
        this.studentService.deleteLog(this.studentId, logEntryId).subscribe(
            response => {
                console.log('Log entry deleted successfully');
                this.fetchLogEntries(); // Refresh log entries after deletion
            },
            error => {
                console.error('Log entry deletion failed:', error);
                // Handle error, e.g., display an error message
            }
        );
    }
}
