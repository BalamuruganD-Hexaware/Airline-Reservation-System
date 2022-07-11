import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SearchService } from './search.service';
import { Flight } from 'src/app/shared/model/flight.model';
import { MatDialog } from '@angular/material/dialog';
import { ReservationComponent } from '../reservation/reservation.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from 'src/app/shared/services/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  providers: [SearchService],
})
export class SearchComponent implements OnInit {
  state: 'more' | 'less' = 'more';
  cities: string[] = [
    'Mumbai',
    'Chennai',
    'Delhi',
    'Hyderabad',
    'Kolkata',
    'Bangalore',
  ];
  flights: Flight[];
  searchForm: FormGroup;

  constructor(
    private searchService: SearchService,
    private authService: AuthService,
    private _snackBar: MatSnackBar,
    private router: Router,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.searchForm = new FormGroup({
      departureCity: new FormControl(null, [Validators.required]),
      destinationCity: new FormControl(null, [Validators.required]),
      departureDate: new FormControl(null),
      departureTime: new FormControl(null),
    });

    this.showAllFlights();
  }

  showAllFlights() {
    this.searchService.getAllFlights().subscribe((data) => {
      this.flights = data.responseObject as Flight[];
    });
  }

  formatDepartureDate(_date: string) {
    let date = new Date(_date);
    date.setDate(date.getDate() + 1);
    let strDate = date.toISOString().split('T')[0];
    return strDate;
  }
  onSearch() {
    let data = this.searchForm.value;
    if (data.departureDate !== null) {
      data.departureDate = this.formatDepartureDate(data.departureDate);
    }
    console.log(data);
    this.searchService.searchFlights(data).subscribe((data) => {
      this.flights = data.responseObject as Flight[];
      if (this.flights.length === 0) {
        this._snackBar.open('No flights available', 'Okay', {
          duration: 5000,
        });
      }
    });
  }

  openReservationDialog(flight: Flight) {
    this.authService.user$.subscribe((data) => {
      if (data === null) {
        this.router.navigate(['/login']);
      } else {
        this.dialog.open(ReservationComponent, {
          width: '80vw',
          data: {
            flightDetails: flight,
          },
        });
      }
    });
  }
  switchState() {
    if (this.state === 'more') this.state = 'less';
    else this.state = 'more';
  }
}
