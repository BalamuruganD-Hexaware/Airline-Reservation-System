import { Passenger } from './passenger.model';

export interface User {
  uid: string;
  email: string;
  photoURL?: string;
  displayName?: string;
}
