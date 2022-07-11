import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JsonResponse } from 'src/app/shared/model/response.model';
import { environment } from 'src/environments/environment';

function _window() {
  return window;
}

@Injectable()
export class ReservationService {
  constructor(private http: HttpClient) {}

  getCost(data): Observable<JsonResponse> {
    return this.http.post<JsonResponse>(
      environment.API_URL + 'reservation/getTotalCost',
      data
    );
  }

  placeOrder(cost: number) {
    return this.http.post<JsonResponse>(
      environment.API_URL + 'reservation/order',
      {
        amount: cost,
      }
    );
  }

  reserveFlight(payload) {
    return this.http.post<JsonResponse>(
      environment.API_URL + 'reservation/reserveSeats',
      payload
    );
  }

  get nativeWindow() {
    return _window();
  }
}
