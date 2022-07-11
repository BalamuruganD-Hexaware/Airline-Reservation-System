import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../shared/services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private router: Router,
    private authService: AuthService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, Validators.required),
    });
    this.authService.user$.subscribe((data) => {
      if (data) {
        this.router.navigate(['/home']);
      }
    });
  }

  onLogin() {
    this.authService
      .loginUser(this.loginForm.value)
      .then((user) => {
        this.router.navigate(['/home']);
      })
      .catch((error) => {
        this.loginForm.patchValue({
          password: '',
        });
        this._snackBar.open('Invalid email or password', 'Ok', {
          duration: 5000,
        });
      });
  }
}
