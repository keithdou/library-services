import { Injectable } from '@angular/core';
import { Observable, Subject, pipe } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';

import { environment } from '../environments/environment';

import { Book } from './book'
import { BookList} from './book'

const SERVICES_URL = environment.servicesUrl;

export interface RefType {
  name: string;
  code: string;
};


@Injectable({
  providedIn: 'root'
})
export class LibraryServicesService {

  constructor(private _http: HttpClient) { }

  listBooks(pageNumber:number, pageSize:number, 
            sortField:string, sortOrder:string,
            params : any[]) : Observable<BookList> {

		let url = SERVICES_URL + "/books?pageNumber=" + pageNumber +
		                         "&pageSize=" + pageSize +
		                         "&orderBy=" + sortField  +
		                         "&sortDirection=" + sortOrder;

		if (params) {
			for (let param of params) {
				console.log(param);
				url = url + "&" + param['key'] + "=" + param['value'];
			}
		}                         
		console.log("url=" + url);

		return this._http.get(url).pipe(
		map(
			data => {
				// console.log("data:");
				// console.log(data);
				//console.log(<Array<Book>> data['bookList']);
				return {totalRecordCount : data['totalRecordCount'], books: <Array<Book>> data['bookList']};
			}));
	}

	listLanguages() : Observable<RefType[]>   {

		let url = SERVICES_URL + "/languages";

		return this._http.get(url).pipe(
		map(
			data => {
				let languageData = <Array<any>> data;
				let languageList = [];
				for (let name of languageData) {
					languageList.push({name : name, code: name});
				}
				languageList[0]['name'] = "Any";
				return languageList;
			}));
	}
	listLibraries() : Observable<RefType[]>   {

		let url = SERVICES_URL + "/libraries";

		return this._http.get(url).pipe(
		map(
			data => {
				let libraryData = <Array<any>> data;
				let libraryList = [];
				for (let name of libraryData) {
					libraryList.push({name : name, code: name});
				}
				return libraryList;
			}));

	}
}
