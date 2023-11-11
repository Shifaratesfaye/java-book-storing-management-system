/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.storing.system;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty id ;
    private final StringProperty bname ;
    private final StringProperty btype ;
    private final StringProperty bauthour ;
    private final StringProperty publishedDate;
        private final StringProperty bprice ;
        
 public Book(){
  id=new SimpleStringProperty(this,"id");
   bname=new SimpleStringProperty(this,"bname");
    btype=new SimpleStringProperty(this,"btype");
    bauthour=new SimpleStringProperty(this,"bauthour");
    publishedDate=new SimpleStringProperty(this,"publishedDate");
    bprice=new SimpleStringProperty(this,"bprice");
       
}
public StringProperty IdProperty(){
return id;
}
public String getId(){
return id.get();
}
public void setId(String newId){
id.set(newId);
} 

public StringProperty bnameProperty(){
return bname;
}
public String getName(){
return bname.get();
}
public void setName(String newname){
bname.set(newname);
} 

public StringProperty TypeProperty(){
return btype;
}
public String getype(){
return btype.get();
}
public void setType(String newType){
btype.set(newType);
} 

public StringProperty AuthourProperty(){
return bauthour;
}
public String getAuthour(){
return bauthour.get();
}
public void setAuthour(String newAuthour){
bauthour.set(newAuthour);
} 

public StringProperty PublishedDateProperty(){
return publishedDate;
}
public String getPublishedDate(){
return publishedDate.get();
}
public void setPublishedDate(String newDate){
publishedDate.set(newDate);
} 
public StringProperty PriceProperty(){
return bprice;
}
public String getPrice(){
return bprice.get();
}
public void setPrice(String newprice){
bprice.set(newprice);
} 
@Override
public String toString(){
    return String.format("%2f", "");
//    getPrice();
    
    
}
}