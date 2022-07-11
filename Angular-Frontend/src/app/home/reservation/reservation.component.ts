import { Component, Inject, NgZone, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Flight } from 'src/app/shared/model/flight.model';
import { Passenger } from 'src/app/shared/model/passenger.model';
import { RazorpayOptions } from 'src/app/shared/model/razorpay.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { environment } from 'src/environments/environment';
import { ReservationService } from './reservation.service';

export interface DialogData {
  flightDetails: Flight;
}

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
  providers: [ReservationService],
})
export class ReservationComponent implements OnInit {
  flight: Flight;
  user: Passenger;
  seatType: string = '';
  numOfSeats: number = null;
  totalCost: number;
  options: RazorpayOptions;
  constructor(
    private service: ReservationService,
    private authService: AuthService,
    private _snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<ReservationComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  ngOnInit(): void {
    this.flight = this.data.flightDetails;
    this.authService.user$.subscribe((user) => (this.user = user));
  }
  getTotalCost() {
    let payload = {
      fID: this.flight.flightNumber,
      seatType: this.seatType,
      numOfSeats: this.numOfSeats,
    };
    this.service.getCost(payload).subscribe((data) => {
      this.totalCost = data.responseObject as number;
    });
  }
  makeReservation() {
    this.service.placeOrder(this.totalCost).subscribe((data) => {
      this.options = {
        key: environment.RZP_KEY_ID,
        amount: '' + this.totalCost * 100,
        currency: 'INR',
        name: 'Reserve your flight',
        description: `${this.flight.departureCity} to ${this.flight.destinationCity}`,
        order_id: data.responseObject as string,
        handler: this.finishReservation.bind(this),
        prefill: {
          name: `${this.user.firstName} ${this.user.lastName}`,
          email: this.user.email,
          contact: this.user.phoneNumber,
        },
        notes: {
          address: 'Razorpay Corporate Office',
        },
        theme: {
          color: '#3399cc',
        },
      };
      var rp = new this.service.nativeWindow.Razorpay(this.options);
      rp.open();
    });
  }

  finishReservation(response) {
    let payload = {
      flightID: this.flight.flightNumber,
      pID: this.user.pID,
      seatType: this.seatType,
      numOfSeats: this.numOfSeats,
      totalCost: this.totalCost,
      razorpayPaymentId: response['razorpay_payment_id'],
      razorpayOrderId: response['razorpay_order_id'],
      razorpaySignature: response['razorpay_signature'],
    };
    let message = '';
    this.dialogRef.close();
    this.service.reserveFlight(payload).subscribe((data) => {
      if (data.status) {
        message = 'Reservation Successful, Enjoy your trip!';
      } else {
        message = 'Reservation failure. Please try again';
      }
    });
    this.dialogRef.afterClosed().subscribe(() => {
      this._snackBar.open(message, 'Okay', {
        duration: 5000,
      });
    });
  }
}
