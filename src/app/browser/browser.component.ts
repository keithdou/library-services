import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { LibraryServicesService, RefType } from '../library-services.service';
import { LazyLoadEvent } from 'primeng/primeng';
import { Book } from '../book'
import { BookList } from '../book'

@Component({
	selector: 'app-root',
	templateUrl: './browser.component.html',
	encapsulation: ViewEncapsulation.None,
	styleUrls: ['./browser.component.css']
})
export class BrowserComponent implements OnInit {

	constructor(private service:LibraryServicesService) { }

	currentPage : Book[];
	totalRecords: number;
	pageNumber  : number;
	pageSize    : number;
	sortColumn  : string;
	sortOrder   : string;
	languageList: RefType[];
	selectedLanguage: RefType;
	libraryList: RefType[];
	selectedLibrary: RefType;
	paramList   : any[];
	selectedBook: Book;
	displayDetails : boolean = false;
	dialogueTitle : string;

	ngOnInit() {
		this.pageNumber = 1;
		this.pageSize = 16;
		this.service.listBooks(this.pageNumber, this.pageSize, null,null, this.paramList).subscribe (
			data => {
				this.currentPage = data['books'];
				this.totalRecords = data['totalRecordCount'];
				console.log("total=" + this.totalRecords);
				console.log("current page:");
				console.log(this.currentPage);
				console.log(data);
			});

		this.service.listLanguages().subscribe (
			data => {
				this.languageList = data;
			});

		this.service.listLibraries().subscribe (
			data => {
				this.libraryList = data;
				this.libraryList.splice(0,0,{name : "Any" , code : ""});
			});
	}

	lazyLoadBooks (event : LazyLoadEvent) {
		console.log("lazyLoadBooks:");
		console.log(event);
		this.pageNumber = (event.first / event.rows) + 1;
		console.log("pageNumber = " + this.pageNumber);
		this.sortColumn = event.sortField;
		this.sortOrder = event.sortOrder == 1 ? "A" : "D";
		if (!this.sortColumn) {
			this.sortColumn = "itemId";
		}
		console.log("sortColumn = " + this.sortColumn);
		this.service.listBooks(this.pageNumber, this.pageSize,
		                       this.sortColumn, this.sortOrder, this.paramList).subscribe (
			data => {
				this.currentPage = data['books'];
				this.totalRecords = data['totalRecordCount'];
			});
	}

	onRowSelect(event) {
		console.log(event);
		this.selectedBook = event.data;
		this.dialogueTitle = "Item " + this.selectedBook.itemId;
		this.showDialog();
	}

	onLanguageChange(event) {
		this.selectedLanguage = event.value;
	}

	onLibraryChange(event) {
		this.selectedLibrary = event.value;
	}

	refresh(event) {
		console.log('refresh event:' + event);
		this.pageNumber = 1;
		this.paramList = [];
		if (this.selectedLanguage && this.selectedLanguage.code) {
			this.paramList.push({key : "language", value: this.selectedLanguage.code});
		}
		if (this.selectedLibrary && this.selectedLibrary.code) {
			this.paramList.push({key : "checkoutLibrary", value: this.selectedLibrary.code});
		}
	
		this.service.listBooks(this.pageNumber, this.pageSize,
		                       this.sortColumn, this.sortOrder,this.paramList).subscribe (
			data => {
				this.currentPage = data['books'];
				this.totalRecords = data['totalRecordCount'];
		});
	}

	formatDate(date) {
		//201805091234
		let dt = date.toString();
		return dt.substr(6,2) + "/" + dt.substr(4,2) + "/" + dt.substr(0,4)
			+ " " + dt.substr(8,2) + ":" + dt.substr(10,2);
	}

	showDialog() {
        this.displayDetails = true;
    }

}
