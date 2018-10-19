import { NgModule }                  from '@angular/core';
import { Routes, RouterModule }      from "@angular/router";
import { LoginOldComponent }         from "./page/login-old/login-old.component";
import { UserRegistrationComponent } from "./page/user-registration/user-registration.component";
import { NotFoundComponent }         from "./page/not-found/not-found.component";
import { HomeComponent }             from "./page/home/home.component";
import { LogoutComponent }           from "./page/logout/logout.component";
import { SettingsComponent }         from "./page/settings/settings.component";
import { SalesComponent }            from "./page/sales/sales.component";
import { UsersComponent }            from "./page/users/users.component";
import { InventoryComponent }        from "./page/inventory/inventory.component";
import { LoginComponent }            from "./page/login/login.component";
import { AuthGuard }                 from "./guard/auth.guard";

const routes: Routes = [

  {
    path         : 'home',
    component    : HomeComponent,
    data         : { title: "Point of Sale"},
    canActivate  : [AuthGuard]
  },
  {
    path         : 'inventory',
    component    : InventoryComponent,
    data         : { title: "Inventory"},
    canActivate  : [AuthGuard]
  },
  {
    path         : 'users',
    component    : UsersComponent,
    data         : { title: "Users" },
    canActivate  : [AuthGuard]
  },
  {
    path         : "",
    redirectTo   : "home",
    pathMatch    : "full"
  },
  {
    path          : 'login-old',
    component     : LoginOldComponent,
    data          : { title: 'Old Login' }
  },
  {
    path          : 'logout',
    component     : LogoutComponent,
    data          : { title: 'Logout' }
  },
  {
    path         : 'settings',
    component    : SettingsComponent,
    data         : { title: "Settings"},
    canActivate  : [AuthGuard]
  },
  {
    path         : 'sales',
    component    : SalesComponent,
    data         : { title: "Sales"},
    canActivate  : [AuthGuard]
  },
  {
    path      : 'login',
    component : LoginComponent,
    data      : { title: "Login"}
  },
  {
    path        : 'registration',
    component   : UserRegistrationComponent,
    data        : { title: 'User Registration' },
    canActivate : [AuthGuard]
  },
  {
    path      : 'not-found',
    component : NotFoundComponent,
    data      : { title: 'Page Not Found' }
  },

  {
    path: '**',
    redirectTo: 'not-found'
  }
];

@NgModule( {

  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

export const routingComponents = [

  LoginOldComponent,
  UserRegistrationComponent,
  SalesComponent,
  HomeComponent,
  SettingsComponent,
  NotFoundComponent,
  LogoutComponent,
  UsersComponent,
  InventoryComponent,
  LoginComponent
];
