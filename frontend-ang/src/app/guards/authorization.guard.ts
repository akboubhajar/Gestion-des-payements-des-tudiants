/*import { CanActivateFn } from '@angular/router';

export const authorizationGuard: CanActivateFn = (route, state) => {
  return true;
};*/
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
export class authorizationGuard {
  constructor(private authService :AuthService, private router:Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.authService.isAuthenticated)
    {
      let requiredRoles=route.data['roles'];//the roles that are required
      let userRoles= this.authService.roles;
      for (let role of userRoles)
      {
        if(requiredRoles.includes(role))
        {
          return true;
        }
      }
      return false;
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
