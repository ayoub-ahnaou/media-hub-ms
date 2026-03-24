import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { CatalogComponent } from './features/media/pages/catalog/catalog.component';
import { MediaDetailComponent } from './features/media/pages/media-detail/media-detail.component';
import { LoginComponent } from './features/auth/pages/login/login.component';
import { DashboardComponent } from './features/dashboard/pages/dashboard/dashboard.component';
import { SubscriptionComponent } from './features/subscription/pages/subscription/subscription.component';
import { HistoryComponent } from './features/history/pages/history/history.component';

export const routes: Routes = [
	{ path: '', pathMatch: 'full', redirectTo: 'login' },
	{ path: 'login', component: LoginComponent },
	{ path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
	{ path: 'catalog', component: CatalogComponent, canActivate: [authGuard] },
	{ path: 'media/:id', component: MediaDetailComponent, canActivate: [authGuard] },
	{ path: 'subscription', component: SubscriptionComponent, canActivate: [authGuard] },
	{ path: 'history', component: HistoryComponent, canActivate: [authGuard] },
	{ path: '**', redirectTo: 'login' }
];
