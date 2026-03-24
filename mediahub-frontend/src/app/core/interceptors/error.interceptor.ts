import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (request, next) => {
  return next(request).pipe(
    catchError((error: HttpErrorResponse) => {
      let message = 'Une erreur est survenue.';

      if (error.error instanceof ErrorEvent) {
        message = error.error.message;
      } else if (typeof error.error === 'string' && error.error.trim().length > 0) {
        message = error.error;
      } else if (error.error?.message) {
        message = error.error.message;
      } else if (error.status === 0) {
        message = 'Impossible de joindre le backend.';
      } else if (error.status >= 500) {
        message = 'Erreur serveur. Veuillez réessayer plus tard.';
      } else if (error.status >= 400) {
        message = 'Requête invalide. Vérifiez les informations envoyées.';
      }

      return throwError(() => new Error(message));
    })
  );
};
