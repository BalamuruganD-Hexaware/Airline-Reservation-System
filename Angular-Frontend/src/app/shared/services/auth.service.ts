import { Router } from '@angular/router';
import { Observable, of, switchMap } from 'rxjs';
import { User } from '../model/user.model';
import firebase from 'firebase/compat/app';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import {
  AngularFirestore,
  AngularFirestoreDocument,
} from '@angular/fire/compat/firestore';
import { Injectable } from '@angular/core';
import { Passenger } from '../model/passenger.model';
import { HttpClient } from '@angular/common/http';
import { JsonResponse } from '../model/response.model';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  user$: Observable<Passenger>;

  constructor(
    private afAuth: AngularFireAuth,
    private afs: AngularFirestore,
    private router: Router,
    private http: HttpClient
  ) {
    this.user$ = this.afAuth.authState.pipe(
      switchMap((user) => {
        if (user) {
          return this.afs
            .doc<Passenger>(`passengers/${user.uid}`)
            .valueChanges();
        } else {
          return of(null);
        }
      })
    );
  }

  async loginUser({ email, password }) {
    return this.afAuth.signInWithEmailAndPassword(email, password);
  }

  async createUser(passenger: Passenger) {
    const credential = await this.afAuth.createUserWithEmailAndPassword(
      passenger.email,
      passenger.password
    );
    return this.updateUserData(credential.user, passenger);
  }

  updateUserData(user: firebase.User, passenger: Passenger) {
    const userRef: AngularFirestoreDocument<any> = this.afs.doc(
      `passengers/${user.uid}`
    );

    const data = {
      uid: user.uid,
      photoURL: user.photoURL,
      email: user.email,
      pID: passenger.pID,
      firstName: passenger.firstName,
      lastName: passenger.lastName,
      phoneNumber: passenger.phoneNumber,
      password: passenger.password,
      address: passenger.address,
    };

    return userRef.set(data, { merge: true });
  }

  registerToDb(data: Passenger): Observable<JsonResponse> {
    return this.http.post<JsonResponse>(
      environment.API_URL + 'auth/register',
      data
    );
  }

  async signOut() {
    await this.afAuth.signOut();
    this.router.navigate(['/']);
  }
}
