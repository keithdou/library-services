# LibraryServices

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.0.7.

## Dependencies

This project was built using PrimeNg version 6.  (still beta at 8/6/2018):

	npm install node_modules
	npm install primeng@6.0.0-beta.1
	npm install font-awesome
	npm install chart.js

If you install a pre-6 version, you will also need to npm install rxjs-compat

## Rest Services

A Java REST service compatible with any J2EE container is included in this project under 'library-browser'.  The service expects to find data in a MongoDB database which needs to be installed separately.  Aproxy service is used to avaoid CORS errors in development. You need to configure proxy-conf.json to point to your apps server (default is localhost:8080).

## load data

The data to be imported can be downloaded from TBA.

To import into MongoDB:

	mongoimport -d library -c books --type csv --file MyDownloadedCSVFile.csv --headerline

To make the column names a bit more code friendly, either edit the CSV file prior to import or run the following commands in a Mongo shell:

	db.books.update({},{$rename : {"Title" : "title"}},false,true);
	db.books.update({},{$rename : {"Author" : "author"}},false,true);
	db.books.update({},{$rename : {"Call number" : "callNumber"}},false,true);
	db.books.update({},{$rename : {"Item id" : "itemId"}},false,true);
	db.books.update({},{$rename : {"Item type" : "itemType"}},false,true);
	db.books.update({},{$rename : {"Status" : "status"}},false,true);
	db.books.update({},{$rename : {"Language" : "language"}},false,true);
	db.books.update({},{$rename : {"Age" : "age"}},false,true);
	db.books.update({},{$rename : {"Date" : "date"}},false,true);
	db.books.update({},{$rename : {"Checkout Library" : "checkoutLibrary"}},false,true);	

## Development server

Run `npm start` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
