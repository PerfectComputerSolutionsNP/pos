import {Category} from './category.model';

export class Page {

 content         : Array<Category>;
 first           : boolean;
 last            : boolean;
 number          : number;
 numberOfElement : number;
 size            : number;
 totalElements   : number;
 totalPages      : number;

}
