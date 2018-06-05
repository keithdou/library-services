import { Injectable } from '@angular/core';
import { Observable, Subject, pipe } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';

import { environment } from '../environments/environment';

import { Book } from './book'

const SERVICES_URL = environment.servicesUrl;

@Injectable({
  providedIn: 'root'
})
export class LibraryServicesService {

  constructor(private _http: HttpClient) { }

  listBooks(pageNumber:number, pageSize:number, 
            sortField:string, sortOrder:string) : Observable<Book[]> {

		let url = SERVICES_URL + "/books?pageNumber=" + pageNumber +
		                         "&pageSize=" + pageSize +
		                         "&orderBy=" + sortField  +
		                         "&sortDirection=" + sortOrder;
		console.log("url=" + url);

		return this._http.get(url).pipe(
		map(
			data => {
				// console.log("data:");
				// console.log(data);
				let bookIst = <Array<Book>> data;
				return bookIst;
			}));
	}
}
