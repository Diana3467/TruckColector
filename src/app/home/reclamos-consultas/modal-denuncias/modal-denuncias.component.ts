import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

@Component({
  selector: 'app-modal-denuncias',
  templateUrl: './modal-denuncias.component.html',
})
export class ModalDenunciasComponent implements OnInit {

  editForm: any;
  @Input() model: any;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.model = data.data;
  }

  ngOnInit(): void {
    this.editForm = new FormGroup({
      tipoDenuncia: new FormControl(this.model.cModoDenC),
      descripcionDenuncia: new FormControl(this.model.cDescripcionDenC),
    })
  }

}
