import { Time } from '@angular/common';

export interface SearchFilter {
  departureCity: string;
  destinationCity: string;
  departureTime?: Time;
  departureDate?: string;
}
