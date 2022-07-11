import { Time } from '@angular/common';

export interface Flight {
  flightNumber: string;
  airlinesName: string;
  departureCity: string;
  destinationCity: string;
  departureDate: Date;
  departureTime: Time;
  arrivalDate: Date;
  arrivalTime: Time;
  businessCount: number;
  businessPrice: number;
  economyCount: number;
  economyPrice: number;
}
