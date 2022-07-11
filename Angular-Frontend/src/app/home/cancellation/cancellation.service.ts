import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JsonResponse } from 'src/app/shared/model/response.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class CancellationService {
  constructor(private http: HttpClient) {}

  getEligibleCancellations(pID: number) {
    let data = {
      pID: pID,
    };
    return this.http.post<JsonResponse>(
      environment.API_URL + 'cancellation/show',
      data
    );
  }

  getPastReservations(pID: number) {
    let data = {
      pID: pID,
    };
    return this.http.post<JsonResponse>(
      'http://localhost:8080/ars/cancellation/showPast',
      data
    );
  }

  cancellation(rID: number) {
    let data = {
      reservationId: rID,
    };
    return this.http.post<JsonResponse>(
      environment.API_URL + 'cancellation/cancel',
      data
    );
  }
}
