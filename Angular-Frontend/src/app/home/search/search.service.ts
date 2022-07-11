import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JsonResponse } from 'src/app/shared/model/response.model';
import { SearchFilter } from 'src/app/shared/model/searchFilter.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class SearchService {
  constructor(private http: HttpClient) {}

  getAllFlights(): Observable<JsonResponse> | null {
    return this.http.get<JsonResponse>(
      environment.API_URL + 'search/getAllFlights'
    );
  }

  searchFlights(data: SearchFilter): Observable<JsonResponse> {
    return this.http.post<JsonResponse>(
      environment.API_URL + 'search/showAvailableFlights',
      data
    );
  }
}
