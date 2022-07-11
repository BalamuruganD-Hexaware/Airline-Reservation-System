import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Passenger } from 'src/app/shared/model/passenger.model';
import { Reservation } from 'src/app/shared/model/reservation.model';
import { AuthService } from 'src/app/shared/services/auth.service';
import { CancellationService } from './cancellation.service';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-cancellation',
  templateUrl: './cancellation.component.html',
  styleUrls: ['./cancellation.component.css'],
  providers: [CancellationService],
})
export class CancellationComponent implements OnInit {
  user: Passenger;
  currentReservations: Reservation[] = [];
  pastReservations: Reservation[] = [];
  constructor(
    private service: CancellationService,
    private authService: AuthService,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.authService.user$.subscribe((user) => {
      this.user = user;
      this.service.getEligibleCancellations(this.user.pID).subscribe((data) => {
        this.currentReservations = data.responseObject as Reservation[];
      });
    });
  }
  cancelReservation({ reservationId, confirmationNumber }) {
    const confirmDialog = this.dialog.open(ConfirmDialogComponent, {
      width: '250px',
    });
    confirmDialog.afterClosed().subscribe((result) => {
      console.log(result);
      console.log(typeof result);
      if (result) {
        this.service.cancellation(reservationId).subscribe((data) => {
          if (data.status) {
            this._snackBar.open(
              `Your reservation ${confirmationNumber} has been cancelled successfully`,
              'Okay',
              {
                duration: 5000,
              }
            );
          }
        });
      }
    });
  }
}
