import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmedValidator } from './validator.service';
import { Router } from '@angular/router';
import { Passenger } from '../shared/model/passenger.model';
import { AuthService } from '../shared/services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  hide = true;
  registerForm: FormGroup;
  registerMessage: string;
  registerStatus: boolean;

  constructor(
    private router: Router,
    private authService: AuthService,
    private _snackBar: MatSnackBar
  ) {}
  ngOnInit(): void {
    this.authService.user$.subscribe((data) => {
      if (data) {
        this.router.navigate(['/home']);
      }
    });
    this.registerForm = new FormGroup(
      {
        firstName: new FormControl(null, [
          Validators.required,
          Validators.minLength(4),
        ]),
        lastName: new FormControl(null, [
          Validators.required,
          Validators.minLength(4),
        ]),
        email: new FormControl(null, [Validators.required, Validators.email]),
        phoneNumber: new FormControl(null, [
          Validators.required,
          Validators.minLength(10),
        ]),
        password: new FormControl(null, [
          Validators.required,
          Validators.minLength(8),
        ]),
        cpassword: new FormControl(null, [
          Validators.required,
          Validators.minLength(8),
        ]),
        address: new FormControl(null, [
          Validators.required,
          Validators.maxLength(50),
        ]),
      },
      ConfirmedValidator('password', 'cpassword')
    );
  }

  get form() {
    return this.registerForm.controls;
  }

  onRegister() {
    let data = this.registerForm.value;
    let newUser: Passenger = {
      pID: 0,
      firstName: data['firstName'],
      lastName: data['lastName'],
      email: data['email'],
      phoneNumber: data['phoneNumber'],
      address: data['address'],
      password: data['password'],
    };
    this.authService.registerToDb(newUser).subscribe((data) => {
      this.registerStatus = data.status;
      this.registerMessage = data.message;
      newUser = data.responseObject as Passenger;
      if (this.registerStatus) {
        this.authService.createUser(newUser);
        this.router.navigate(['/home']);
      } else {
        this._snackBar.open(this.registerMessage, 'Ok', {
          duration: 2000,
        });
        this.registerForm.patchValue({
          email: '',
          phoneNumber: '',
          password: '',
          cpassword: '',
        });
      }
    });
  }
}
