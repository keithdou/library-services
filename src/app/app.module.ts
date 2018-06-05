import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserComponent } from './browser/browser.component';

import { HttpClientModule } from '@angular/common/http';

import { TableModule } from 'primeng/table';

@NgModule({
  declarations: [
    AppComponent,
    BrowserComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    TableModule
  ],
  providers: [],
  bootstrap: [BrowserComponent]
})
export class AppModule { }
