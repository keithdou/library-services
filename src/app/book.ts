export class Book {
	title     		: string;
	author    		: string;
	callNumber		: string;
	itemId    		: number;
	itemType  		: string;
	status    		: string;
	language  		: string;
	age       		: string;
	date	  		: number;
	checkoutLibrary	: string;
}

export class BookList {
	totalRecordCount : number;
	books            : Book[];
}