import { FormGroup, ValidatorFn } from '@angular/forms';

export function ConfirmedValidator(
  password: string,
  cpassword: string
): ValidatorFn {
  return (formGroup: FormGroup) => {
    const control = formGroup.controls[password];
    const matchingControl = formGroup.controls[cpassword];

    if (matchingControl.errors && !matchingControl.errors.confirmedValidator) {
      return;
    }
    if (control.value !== matchingControl.value) {
      return { confirmedValidator: true };
    } else {
      return null;
    }
  };
}
