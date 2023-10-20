import { AfterViewInit, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';
import { GrocessaryService } from '../../service/grocessary.service';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit{
  url: string = '';
  userName: string = '';
  constructor(
    private gService :GrocessaryService,
    private router:Router,
    private changeDetector: ChangeDetectorRef
  ) {
    if (this.gService.getAdminName() !== null) {
      let fname = this.gService.getAdminName().substring(0, this.gService.getAdminName().indexOf(' ')); 
      let lname = this.gService.getAdminName().substring(this.gService.getAdminName().indexOf(' ') + 1); 
      this.userName = fname.substring(0,1) + lname.substring(0,1)
    }
  }

  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      this.url = event?.url;
    });
  }

  
  routerToLink(link: string): void {
    if (link === '/admin/logout') {
      this.gService.clientLogout();
      return;
    }
    this.router.navigate([link]);
  }

}
