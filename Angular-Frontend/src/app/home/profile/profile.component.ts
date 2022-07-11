import { Component, OnInit } from '@angular/core';
import { Passenger } from 'src/app/shared/model/passenger.model';
import { AuthService } from 'src/app/shared/services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user: Passenger;
  data: any[];
  constructor(private auth: AuthService) {}

  ngOnInit(): void {
    this.auth.user$.subscribe((data) => {
      this.user = data;
    });
  }
}
