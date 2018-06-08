import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserComponent } from './browser/browser.component';

import { HttpClientModule } from '@angular/common/http';

import { TableModule } from 'primeng/table';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { ScrollPanelModule } from 'primeng/scrollpanel';

@NgModule({
  declarations: [
    AppComponent,
    BrowserComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    TableModule,
    DialogModule,
    DropdownModule,
    ButtonModule,
    ScrollPanelModule
  ],
  providers: [],
  bootstrap: [BrowserComponent]
})
export class AppModule { }
