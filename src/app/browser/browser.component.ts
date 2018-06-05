import { Component, OnInit } from '@angular/core';
import { LibraryServicesService } from '../library-services.service';
import { LazyLoadEvent } from 'primeng/primeng';
import { Book } from '../book'

@Component({
	selector: 'app-root',
	templateUrl: './browser.component.html',
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

	ngOnInit() {
		this.pageNumber = 1;
		this.pageSize = 10;
		this.service.listBooks(this.pageNumber, this.pageSize,null,null).subscribe (
			data => {
				this.currentPage = data;
				this.totalRecords = 45000;
			});

	}

	lazyLoadBooks (event : LazyLoadEvent) {
		console.log("lazyLoadBooks:");
		console.log(event);
		this.pageNumber = (event.first / event.rows) + 1;
		console.log("pageNumber = " + this.pageNumber);
		this.sortColumn = event.sortField;
		if (!this.sortColumn) {
			this.sortColumn = "itemId";
		}
		console.log("sortColumn = " + this.sortColumn);
		this.service.listBooks(this.pageNumber, this.pageSize,
		                       this.sortColumn, event.sortOrder == 1 ? "A" : "D").subscribe (
			data => {
				this.currentPage = data;
			});
	}

	onRowSelect(event) {
		console.log(event);
	}

}
