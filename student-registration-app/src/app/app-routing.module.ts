import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentRegistrationComponent } from './student-registration/student-registration.component'; // Add this line

const routes: Routes = [
  // ... other routes
  { path: 'registerStudent', component: StudentRegistrationComponent },
  // ... other routes
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
