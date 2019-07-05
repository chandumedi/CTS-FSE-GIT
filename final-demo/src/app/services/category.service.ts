import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpClient,
  HttpHeaders,
} from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Category } from '..//category';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class CategoryService {
categories:Array<Category>;
categoriesSubject: BehaviorSubject<Array<Category>>;
  constructor(private httpClient: HttpClient,private authService:AuthenticationService) {
    this.categories=[];
    this.categoriesSubject=new BehaviorSubject(this.categories);
    }
    
    
  addCategory(data:Category): Observable<Category> {
    let header = new HttpHeaders({
      'Authorization':`Bearer ${this.authService.getBearerToken()}`,
      'Access-Control-Allow-Origin':"*",
      'Access-Control-Allow-Headers':"POST,GET,PUT DELETE,Authorization,Content-Type",
      'Access-Control-Allow-Credentials':"true"
    });
    console.log(header.get('Authorization'));
    return this.httpClient.post<Category>('http://localhost:8084/api/v1/category', data ,
     {headers:header}
    ).do(addedeCategory=>{
      this.categories.push(addedeCategory);
      this.categoriesSubject.next(this.categories);
    });
  }

  fetchCategoriesFromServer() {
    let header = new HttpHeaders({
      'Authorization':`Bearer ${this.authService.getBearerToken()}`,
      'Access-Control-Allow-Origin':"*",
      'Access-Control-Allow-Headers':"POST,GET,PUT DELETE,Authorization,Content-Type",
      'Access-Control-Allow-Credentials':"true"
    });
    return this.httpClient.get<Array<Category>>('http://localhost:8084/api/v1/category/'+`${this.authService.getUserId()}`, {
      headers: header
    }).subscribe(categories => {
      this.categories = categories;
      this.categoriesSubject.next(this.categories);
    }, (err) => {});
  }
  getCategories(): BehaviorSubject<Array<Category>> {
    return this.categoriesSubject;
  }
  getCategoryById(categoryId): Category{
    const selectedCategory = this.categories.find(cat => cat.id === categoryId);
    return Object.assign({}, selectedCategory);
  }

  deleteCategory(categoryId:string){
    let header = new HttpHeaders({
      'Authorization':`Bearer ${this.authService.getBearerToken()}`,
      'Access-Control-Allow-Origin':"*",
      'Access-Control-Allow-Headers':"POST,GET,PUT DELETE,Authorization,Content-Type",
      'Access-Control-Allow-Credentials':"true"
    });
    return this.httpClient.delete("http://localhost:8084/api/v1/category/"+categoryId,{headers: header}).
      subscribe(res=>{
        
      },
      error=>{
        const selectedCategory = this.categories.find((currentCategory) => currentCategory.id === categoryId);
          const index: number = this.categories.indexOf(selectedCategory);
          console.log(index);
          if (index !== -1) {
            this.categories.splice(index, 1);
        } 
          this.categoriesSubject.next(this.categories);
      });
  }

  editCategory(category:Category):Observable<any>{
    let header = new HttpHeaders({
      'Authorization':`Bearer ${this.authService.getBearerToken()}`,
      'Access-Control-Allow-Origin':"*",
      'Access-Control-Allow-Headers':"POST,GET,PUT DELETE,Authorization,Content-Type",
      'Access-Control-Allow-Credentials':"true"
    });
    return this.httpClient.put<Category>(`http://localhost:8084/api/v1/category/${category.id}`, category, {
      headers: new HttpHeaders()
        .set('Authorization', `Bearer ${this.authService.getBearerToken()}`)
    }).do(addedCategpry => {
      
      const selectedCategory = this.categories.find((currentCategory) => currentCategory.id === addedCategpry.id);
      Object.assign(selectedCategory, addedCategpry);
      this.categoriesSubject.next(this.categories);
    });
  }
}
