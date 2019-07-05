import { Component, OnInit } from '@angular/core';
import { Category } from '../category';
import { MatTableDataSource } from '@angular/material';
import { CategoryService } from '../services/category.service';
import { RouterService } from '../services/router.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  categories:Array<Category>;
  submitMessage:string;
   displayedColumns: string[] = ['categoryName', 'categoryDescription','id'];
  dataSource;
  constructor(private categoryService:CategoryService,private routerService:RouterService) {
     
   }

  ngOnInit() {
    this.categoryService.getCategories().subscribe(categoriesData=>{
      this.categories=categoriesData;
      this.dataSource = new MatTableDataSource(this.categories);
    },
  err=>{});
  }

  deleteCategory(categoryId:string){
    this.categoryService.deleteCategory(categoryId);
  }
  updateCategory(categoryName:string,categoryDesc:string,id:string){
    const category: Category={id:id,categoryName:categoryName,categoryDescription:categoryDesc,categoryCreatedBy:"",categoryCreationDate:null};
    this.categoryService.editCategory(category).subscribe(response=>{
      this.submitMessage="Category updated Successfully";
      this.routerService.routeToCategoryList();
    },error=>{
     
    })
  }
}
