export class PaypalDTO {
  private _price:number;


  constructor(price: number) {
    this._price = price;
  }


  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }
}
