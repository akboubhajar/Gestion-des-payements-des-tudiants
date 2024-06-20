import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import {Injectable} from "@angular/core";
import {AuthService} from "../services/auth.service";


@Injectable(
  {providedIn:'root'}
)
export class AuthGuard {
  constructor(private authService :AuthService, private router:Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.authService.isAuthenticated)
    {
         return true
    }
    else
    {

      this.router.navigateByUrl('/login')
      return false

    }


    }


}
//si on utilise cette method on peuit pas injecter le service ou router
/*export const authenticationGuard: CanActivateFn = (route, state) => {
  return true;
};*/
