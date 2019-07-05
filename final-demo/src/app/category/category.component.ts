import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RouterService } from '../services/router.service';
import { FormControl } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';
import { CategoryService } from '../services/category.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CategoryViewComponent } from '../category-view/category-view.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  submitMessage:string;
  categoryName = new FormControl('');
  categoryDescription = new FormControl('');
  constructor(private categoryService: CategoryService,
    private routerService: RouterService,private authService:AuthenticationService,
    private dialogRef: MatDialogRef<CategoryViewComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any) {
    }
  ngOnInit() {
  }
  
  addCategory(){
    const category:any={categoryName:this.categoryName.value,
      categoryDescription:this.categoryDescription.value,categoryCreatedBy:this.authService.getUserId()};
      this.categoryService.addCategory(category).subscribe(
      res=>{
        this.submitMessage=res.categoryName+' created';
        this.dialogRef.close();
      },
      err=>{
        this.submitMessage=err.message;
      }
    )
  }
}
