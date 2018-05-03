/**
*
* @author Ömer Zırh/omer.zirh@ogr.sakarya.edu.tr
* @since 03/11/2018
* <p>
*   program.java dosyasının okunup bilgilerinin yazdırıldıgı java program dosyası.
* </p>
*/
package odev1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author touchngo
 */
public class Odev1 {
     



    public static void main(String[] args) throws Exception {
        int indexEleman=0;
        String replaced="";
        String className="";
        String altElemanlar = "";
        String metod="";            //gerekli degiskenler atandı
        String ali="";
        int parametreIndex=0;
        int parantIndex=0;
        int altMetodIndex=0;
        int functionPrintIndex=0;
        String [] parant;
        String [] parant1;
try{  // hata yakalamak icin try ve catch blokları olusturuldu
    Reader reader = new FileReader("program.java"); //iki while döngüsü iki kere program.java dosyası açıldı
    Reader ready = new FileReader("program.java");
    BufferedReader bufferedReader =new BufferedReader(reader);
    BufferedReader buffRead=new BufferedReader(ready);
   
    String line = bufferedReader.readLine();   //iki while dongusu icinde ayrı ayrıd  dosya okumak icin BufferedReader dan olusturulan nesnelerden  stringler olusturuldu
    String index=buffRead.readLine();
    
    Pattern p; //regEx icin Pattern sınıfından p nesnesi
    Matcher m;      //eslestirme yapmak icin Matcher sınıfından m nesnesi
    p = Pattern.compile("\\((.*?)\\)");   //parant1ez icini yazdırmak icin gereken regex    p = Pattern.compile("\\b(void|string|boolean|)\\b");  

    while((line=bufferedReader.readLine())!=null){                  //alt elemanları ve metodları sayabilmek için ayrı bir while döngüsü 
        if(line.contains("class")&&line.contains("public")){
            className = line.replaceAll("public", "");                          //satırda eger class ve public varsa satır alınıyor ve sınıf okunuyor
            System.out.println("Sınıf Adı:"+className.substring("class".length()+1,className.indexOf("{")));    
        }
        if(line.contains(";")&&(line.contains("private")||line.contains("public"))){  //satırda ; ve public veya private var ise giriliyor ve alt eleman indexi artıyor
            indexEleman++;
        }
        
         
         
        if((line.contains("(")&&line.contains(")")&&line.contains("{"))&&((line.contains("public")||line.contains("private")))){
            altMetodIndex++;                                                                //satır da parantez,süslü parantez ve public veya private var ise metod index artıyor
        }
    }
    bufferedReader.close();                 //dosyayla isimiz bittikten sonra while döngüsü kapatılıp buffered reader sonlandırılıyor
            System.out.println("Alt Elemanlar:"+indexEleman);        // alt eleman indexi yazdırılıyor
    while((index = buffRead.readLine()) != null) {          //diger islemler icin while icine giriliyor
         
            m = p.matcher(index);                               //parametre degerleri okunması icin her dongude parantez kontrol ediliyor
        if(index.contains(";")&&(index.contains("private")||index.contains("public"))){   //alt eleman kosulları saglanıyorsa  giriliyor

          
           String equalVariable="";                             //
            if(index.contains("=")){                    //eger alt eleman kosulunu saglayan satırda = varsa o karakterden baslayarak ; e kadar olan kısım degiskene atanıyıor
            equalVariable = index.substring(index.indexOf("="), index.indexOf(";")).trim();
           }
            replaced=index.replaceAll("(public|private|final|static)\\s","").replaceAll(equalVariable, "").trim(); //degiskene atanan kısımla,regExde belirtilen ifadelerin yeri "" ile degistiriliyor
                                                                                                                   //trim fonksiyonu ile bosluklardan kurtuluyor
             altElemanlar =  replaced.substring(replaced.indexOf(" "),replaced.indexOf(";"))+"-"+replaced.substring(0, replaced.indexOf(" ")); //artık iki elemanlı bir degiskenimiz var
                                                                                                                        //once ikinci sonra kısa cizgi ve sonra ilk ifade  degiskene aatanıyor
            System.out.println(altElemanlar.trim());                    //istenilen ifade tipinde yazdırılıyor
            functionPrintIndex++;                   //fonksiyon sayısı alt elemanlardan sonra yazdırılmasını saglamak icin kosul yapıldı bu yüzden her al eleman yazıldıgında belirledigmiz degisken bir artıyor
         }  
        
         
        if(functionPrintIndex==indexEleman){                //yukarıda sayılan degisken eger alt eleman sayısına esitse donguye giriliyor ve uye fonksiyon sayısı yazdırılıyor
            System.out.println("Üye Fonksiyolar:"+altMetodIndex);
            functionPrintIndex++;               // bir daha yazdırılmaması için degisken sayısı bir atırılıyor ve koşul bir daha sağlanmıyor
        }
        if((index.contains("(")&&index.contains(")")&&index.contains("{"))&&((index.contains("public")||index.contains("private")))){
                                                                // eger fonksiyon koşulları sağlanıyorsa giriliyor
            metod=index.substring(0,index.indexOf("(")+1).trim();   //satırın ilk index'inden '(' e kadar olan kısmı metod degiskenine atanıyor
         
        if(metod.contains("void")||metod.contains("String")||metod.contains("boolean")||metod.contains("int")||metod.contains("double")||metod.contains("boolean"))
         {                                  // eger satırda donus tipi varsa bu kosula giriliyor
         
            System.out.println(metod.trim().substring(metod.trim().lastIndexOf(" ")+1,metod.trim().indexOf("("))); //son bosluktan '(' e kadar olan bölüm yazıdırlıuor(fonksiyon ismi)
            System.out.println("Dönüş Türü:"+metod.substring(metod.indexOf(" "), metod.lastIndexOf(" "))); //son bosluktan, onceki bosluga olan bolum yazırılıyor(dönüs tipi)
             
             if(m.find())      //eger iki parantez varsa
             {
                
                 
                      parant1=m.group(1).split(",");  //parantez ici ',' le ayrılarak bir diziye atanıyor
                      for(String parametre:parant1){        //diziyle donguye giriliyor
                     
                        if(!"".equals(parametre)){           //eger dizi elemanlarından biri bos degilse 
                       
                            parantIndex++;                    //parantez indexi artırılıyor (bos olmayan parantez)
                       }
                        else
                            System.out.println("Aldığı Parametre:"+parametreIndex); // eger bossa 0 olan parametre index yazdırılıyor
                }
                            for(String parametre:parant1){          //tekrar aynı döngü kuruldu
                     
                       if(!"".equals(parametre)){       // eger bos degilse
                        
                            parametreIndex++;               // parametre index artılıyor
                            if(parametreIndex==parantIndex){ // parametre indexi tekrar yazdırmasın diye parametre index yukarda sayılam parantez index e ulastıgında 
                            System.out.println("Aldığı Parametre:"+parametreIndex); //parametre sayısı yazdırılıyor
                            parametreIndex=0;           //diger fonksiyondaki kontrol icin parametre index sıfırlanıyor
                           }
                       }
                }
                   for(String parametre:parant1){   // tekrar aynı dongu kuruluyor
                     
                        if("".equals(parametre)){                     //eger parametre bos ise cizgi yazılıyor
                           System.out.println("----------------");
                           continue;  // devam edilioyr
                       }
                       else{                          
                            parametreIndex++;   // eger bos degise parametre index bir artılıyor
                            System.out.println((parametre.trim().substring(parametre.trim().lastIndexOf(" "),parametre.trim().length())+"-"+parametre.trim().substring(0, parametre.trim().indexOf(" "))).trim());     
                                                                        ////parant1ez icini yazdırıyor
                            if(parametreIndex==parantIndex){        // eger parametre index parantez index'e esitse
                                System.out.println("----------------");   //kısa cizgiler yazdırılıyor
                                parantIndex=0;                              // diger kosulda kullanmak icin parantez ve parametre indexler sıfırlanıyor
                                parametreIndex=0;
                           }
                       }
                }
                   
            }
             
         }
         else{   //eger donus tipi olmayan fonksiyon ise
             System.out.println(metod.trim().substring(metod.trim().indexOf(" ")+1,metod.trim().indexOf("(")));  //bosluktan paranteze kadar olan kısım yazdırılıyor(fonksiyon ismi)
             System.out.println("Dönüş Türü:Yok");     //dogrudan donus türü yok yazdırılıyor.
        if(m.find())    
             {                                  //burdan sonra else kapatılana kdar yukarıda uygulanan kosullar ve islemler uygulanıyor
                parant=m.group(1).split(",");
                for(String parametre:parant){
                     
                    if(!"".equals(parametre)){                                             
                        parantIndex++;                           
                    }
                    else
                        System.out.println("Aldığı Parametre:"+parametreIndex);
                }
                
                for(String parametre:parant){
                     
                    if("".equals(parametre)){
                        continue;
                    }
                    else{     
                        parametreIndex++;
                        if(parametreIndex==parantIndex){
                            System.out.println("Aldığı Parametre:"+parametreIndex);
                            parametreIndex=0;
                           }
                       }
                }
                   for(String parametre:parant){
                     
                       if("".equals(parametre)){
                           System.out.println("--------------");
                           continue;
                       }
                       else{                          
                        parametreIndex++;
                           System.out.println((parametre.trim().substring(parametre.trim().lastIndexOf(" "),parametre.trim().length())+"-"+parametre.trim().substring(0, parametre.trim().indexOf(" "))).trim());     //parant1ez icini yazdırıyor
                         if(parametreIndex==parantIndex){
                            System.out.println("--------------");  
                            parantIndex=0;
                            parametreIndex=0;
                           }
                        }
                    }
                }
            }
        }
    }
    buffRead.close();           //dosyayla isimiz bittikten sonra while döngüsü kapatılıp buffered reader sonlandırılıyor
        }
catch(FileNotFoundException e){
        e.printStackTrace();     //try de hesaplananlar hata olursa görüntülenir
        }
    }
}






