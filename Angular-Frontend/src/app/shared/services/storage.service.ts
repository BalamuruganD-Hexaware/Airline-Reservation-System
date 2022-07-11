import { Flight } from '../model/flight.model';
import { Passenger } from '../model/passenger.model';

export function store(obj: Passenger | Flight, name: string): void {
  localStorage.setItem(name, JSON.stringify(obj));
}

export function retrieve(name: string): Passenger | Flight {
  let obj: Passenger | Flight;
  obj = JSON.parse(localStorage.getItem(name));
  return obj;
}

export function remove(name: string) {
  localStorage.removeItem(name);
}
