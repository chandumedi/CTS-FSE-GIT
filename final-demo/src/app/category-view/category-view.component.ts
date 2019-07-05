import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RouterService } from '../services/router.service';
import { CategoryComponent } from '../category/category.component';
import { CategoryService } from '../services/category.service';
import { Category } from '../category';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-category-view',
  templateUrl: './category-view.component.html',
  styleUrls: ['./category-view.component.css']
})
export class CategoryViewComponent implements OnInit {
  
  constructor(private dialog: MatDialog,private routerService: RouterService,private categoryService:CategoryService) { 
   
   
    this.dialog.open(CategoryComponent,{
      data:{

      }
    }).afterClosed().subscribe(result => {
      this.routerService.routeBack();
    });
  }


  ngOnInit() {

  }
  
}
