import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class MyDatabase {
    long endofrecord;
    String csvFile = ".//us-500.csv";
    String dbFile = ".//data.db";
    String indFile = ".//id.ndx";
    String indFile1 = ".//lastname.ndx";
    String indFile2= ".//state.ndx";
    //String temp1= "C:/Users/shashank/Desktop/db proj2/temp.ndx";
    String fname="id.ndx";
    //File tempf=new File(temp1);
    File indf=new File(indFile);
    Map hm = new TreeMap();
    Map hm2 = new TreeMap();
    HashMap hm3 = new HashMap(); 
    String[] fieldnames={"id","first_name","last_name","company_name","address","city","county","state","zip","phone1","phone2","email","web"};
  public void writetoindexfile(String s,long i) throws IOException
  {
      @SuppressWarnings("UnusedAssignment")
      String temp=null;
      temp=""+(int)i;
      hm3.put(s,temp);    
  }
    @SuppressWarnings("ConvertToTryWithResources")
  public void indexvalues() throws IOException{
            @SuppressWarnings("UnusedAssignment")
            String temp=null;
            Set set=hm3.entrySet();
            Iterator i= set.iterator();
            FileWriter fw = new FileWriter(indFile,false);
            BufferedWriter bw = new BufferedWriter(fw);
            while(i.hasNext()){
                Map.Entry me=(Map.Entry)i.next();
                temp=me.getKey()+","+me.getValue();
                bw.write(temp);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
  
  public void writetolnamefile(String s,long l) throws IOException
  {
      //HashMap h1=new HashMap();
      //FileWriter fw = new FileWriter(indFile1,true);
      //BufferedWriter bw = new BufferedWriter(fw);
      @SuppressWarnings("UnusedAssignment")
      String ss=null;
      String line="";
      String[] sub;
      String temp;
      String store=null;
      String cvsSplitBy=",";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] sub2=null;
         if(hm.containsKey(s)) {
             
            ss= (String)hm.get(s);
            ss = ss+"|"+ (int)l;
            hm.put(s,ss);
          }
         else{
            ss=""+(int)l;
             hm.put(s,ss);
         }
         
         //bw.close();
         //fw.close();
         //hashvalues();
        }
        
    @SuppressWarnings("ConvertToTryWithResources")
        public void lnamevalues() throws IOException{
            @SuppressWarnings("UnusedAssignment")
            String temp=null;
            Set set=hm.entrySet();
            Iterator i= set.iterator();
            FileWriter fw = new FileWriter(indFile1,false);
            BufferedWriter bw = new BufferedWriter(fw);
            while(i.hasNext()){
                Map.Entry me=(Map.Entry)i.next();
                temp=me.getKey()+","+me.getValue();
                bw.write(temp);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
  
    public void writetostatefile(String s,long l) throws IOException
    {
        //System.out.println("NR state value"+s);
      @SuppressWarnings("UnusedAssignment")
      String ss=null;
      String line="";
      String[] sub;
      String temp;
      String store=null;
      String cvsSplitBy=",";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] sub2=null;
      //FileWriter fw = new FileWriter(indFile2,true);
      //BufferedWriter bw = new BufferedWriter(fw);
         if(hm2.containsKey(s)) {
            ss= (String)hm2.get(s);
            ss = ss+"|"+ (int)l;
            hm2.put(s,ss);

          }
         else{
          ss=""+(int)l;
          hm2.put(s,ss);
 
         }
         //bw.close();
           // fw.close();
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public void statevalues() throws IOException{
            @SuppressWarnings("UnusedAssignment")
            String temp=null;
            Set set=hm2.entrySet();
            Iterator i= set.iterator();
            FileWriter fw = new FileWriter(indFile2,false);
            BufferedWriter bw = new BufferedWriter(fw);
            while(i.hasNext()){
                Map.Entry me=(Map.Entry)i.next();
                temp=me.getKey()+","+me.getValue();
                bw.write(temp);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
    
    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
  public void readfromfile(long l) throws IOException
  {
      String line="";
      String cvsSplitBy = "\\|";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] s2=null;
      try{
            RandomAccessFile file= new RandomAccessFile(dbFile,"rw");
            file.seek(l);
            for(int i=0;i<13;i++)
            {
                    System.out.print(""+file.readUTF()+"\t");
            }
            System.out.println("");
            //line=file.readLine();
            //System.out.println(""+line);
            //System.out.println(""+file.readUTF());
            file.close();
      }
      catch(FileNotFoundException e){
          e.printStackTrace();
      }     
  }
  public void select(String s) throws FileNotFoundException
  {
      int length;
      String temp;
      int count=0;
      length=s.length();
      //System.out.println("length:"+length);
      if(length==2)
      {
            Set set=hm2.entrySet();
            Iterator i= set.iterator();
            while(i.hasNext()){
                Map.Entry me=(Map.Entry)i.next();
                temp=""+me.getKey();
                if(temp.equals(s))
                {
                    selectfromstate(s);
                    count++;
                    break;
                }
            }
       }
      else
      {
            Set set=hm.entrySet();
            Iterator i= set.iterator();
            while(i.hasNext()){
                Map.Entry me=(Map.Entry)i.next();
                temp=""+me.getKey();
                if(temp.equals(s))
                {
                    selectfromlname(s);
                    count++;
                }
            }
      }
      if(count!=1)
      {
          System.out.println("A record with either lastname or state as:"+s+"  does not exists");
      }
  }
    @SuppressWarnings({"UnusedAssignment", "CallToPrintStackTrace"})
  public void selectfromlname(String s) throws FileNotFoundException
  {
      @SuppressWarnings("UnusedAssignment")
      String l="";
      String[] sub;
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] sub2=null;
      String temp="";
        sub = null;
        int i=0;
      String cvsSplitBy = ",";
      String filesplitby= "|";
      FileReader fr = new FileReader(indFile1);
      BufferedReader br = new BufferedReader(fr);
  
      try{
          while((l=br.readLine())!=null)
          {
             sub= l.split(cvsSplitBy);
             if(sub[0].equals(s))
             {
                 //System.out.println(sub[1]);
                     StringTokenizer t=new StringTokenizer(sub[1],"|");
                     while(t.hasMoreTokens())
                     { 
                         String token=t.nextToken();
                         readfromfile(Long.parseLong(token));                    
                     }
             } 
          }
          br.close();
          fr.close();
      }
      catch (IOException e) {
		e.printStackTrace();
	}  
  }
    @SuppressWarnings({"UnusedAssignment", "CallToPrintStackTrace"})
  public void selectfromstate(String s) throws FileNotFoundException
  {
      @SuppressWarnings("UnusedAssignment")
      String l="";
      String[] sub;
      String temp="";
        sub = null;
      String cvsSplitBy = ",";
      FileReader fr = new FileReader(indFile2);
      BufferedReader br = new BufferedReader(fr);
      try{
          while((l=br.readLine())!=null)
          {
             sub= l.split(cvsSplitBy);
             if(sub[0].equals(s))
             {
                 //System.out.println(sub[1]);
                     StringTokenizer t=new StringTokenizer(sub[1],"|");
                     while(t.hasMoreTokens())
                     {
                         
                         String token=t.nextToken();
                         readfromfile(Long.parseLong(token));                         
                     }
             } 
          }
          br.close();
          fr.close();
      }
      catch (IOException e) {
		e.printStackTrace();
	} 
  }
    @SuppressWarnings({"UnusedAssignment", "CallToPrintStackTrace"})
  public void select(int s) throws FileNotFoundException
  {
      @SuppressWarnings("UnusedAssignment")
      String l="";
      String[] sub;
      String temp="";
        sub = null;
      String cvsSplitBy = ",";
      int count=0;
      FileReader fr = new FileReader(indFile);
      BufferedReader br = new BufferedReader(fr);
      try{
          while((l=br.readLine())!=null)
          {
             sub= l.split(cvsSplitBy);
             //System.out.println("offset of id"+sub[0]);
             if(Integer.parseInt(sub[0])==s)
             {
                 readfromfile(Long.parseLong(sub[1]));
                 count++;
             } 
          }
          if(count!=1)
          {
              System.out.println("A record with the id:"+s+" does not exists");
          }
          br.close();
          fr.close();
      }
      catch (IOException e) {
		e.printStackTrace();
	}  
  }
    @SuppressWarnings("ConvertToTryWithResources")
  public void insert(String snew) throws FileNotFoundException, IOException
  {
      String temp;
      //char[] array = null;
      String parse="";
      String[] temp2;
      //int length=0;
      String cseperator=",";
      long currentlocation;
      RandomAccessFile file= new RandomAccessFile(dbFile,"rw");
      temp=snew;
      temp2=temp.split(cseperator);
      if(hm3.containsKey(temp2[0]))
      {
          System.out.println("A record with the id:"+temp2[0]+"already exists");
      }
      else
      {
          file.seek(endofrecord);
          currentlocation=file.getFilePointer();
          //System.out.println("current location"+currentlocation);
          for(int i=0;i<13;i++)
          {
              file.writeUTF(temp2[i]);
          }
          System.out.println(""+temp2[0]+""+temp2[2]+""+temp2[7]);
          writetoindexfile(temp2[0],currentlocation);
          writetolnamefile(temp2[2],currentlocation);
          writetostatefile(temp2[7],currentlocation);
          endofrecord=file.getFilePointer();
          indexvalues();
          lnamevalues();
          statevalues();
      }
      file.close();
  }
  public void count() throws FileNotFoundException
  {
      System.out.println("no of records:"+hm3.size());     
  }
    @SuppressWarnings({"UnusedAssignment", "CallToPrintStackTrace"})
  public void delete(int s) throws FileNotFoundException
  {
      @SuppressWarnings("UnusedAssignment")
      String l="";
      String[] sub;
      int count=0;
      @SuppressWarnings("UnusedAssignment")
      String temp="";
        sub = null;
      String cvsSplitBy = ",";
      FileReader fr = new FileReader(indFile);
      BufferedReader br = new BufferedReader(fr);
      try{
          while((l=br.readLine())!=null)
          {
             sub= l.split(cvsSplitBy);
             //System.out.println("offset of id"+sub[0]);
             if(Integer.parseInt(sub[0])==s)
             {
                 deletefromlastname(Long.parseLong(sub[1]));
                 deletefromstate(Long.parseLong(sub[1]));
                 deletefromfile(Long.parseLong(sub[1]));
                 //hm3.remove(s);
                 count++;
                 break;
             }
             
          }
          if(count!=1)
          {
              System.out.println("Record with the id: "+s+" does not exist");
          }
          temp=""+s;
          hm3.remove(temp);
          
          indexvalues();
          br.close();
          fr.close();
          
      }
      catch (IOException e) {
		e.printStackTrace();
	}  
  }
    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
  public void deletefromfile(long l) throws IOException
  {
      @SuppressWarnings("UnusedAssignment")
      String line="";
      line=""+0;
      String cvsSplitBy = "\\|";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] s2=null;
      try{
            RandomAccessFile file= new RandomAccessFile(dbFile,"rw");
            file.seek(l);
            for(int i=0;i<13;i++)
            {
                    file.writeUTF(line);//System.out.print(""+file.readUTF()+"\t");
            }
            System.out.println("");
            //line=file.readLine();
            //System.out.println(""+line);
            //System.out.println(""+file.readUTF());
            file.close();
      }
      catch(FileNotFoundException e){
          e.printStackTrace();
      }     
  }
    @SuppressWarnings({"ConvertToTryWithResources", "UnnecessaryContinue", "CallToPrintStackTrace"})
  public void deletefromlastname(long l) throws IOException
  {
      String line="";
      line=""+0;
      @SuppressWarnings("UnusedAssignment")
      String compare="";
      compare=""+l;
      String cvsSplitBy = "\\|";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] s2=null;
      String temp=null;
      @SuppressWarnings("UnusedAssignment")
      String temp2=null;
      try{
            RandomAccessFile file= new RandomAccessFile(dbFile,"rw");
            file.seek(l);
            for(int i=0;i<=2;i++)
            {
            temp=file.readUTF();
            //System.out.println("from deletefromfile"+temp);
            }//System.out.println("from deletefromfile"+temp);
            temp2=""+hm.get(temp);
            hm.remove(temp);
            StringTokenizer t=new StringTokenizer(temp2,"|");
                     while(t.hasMoreTokens())
                     {
                         
                         String token=t.nextToken();
                         if(token.equals(compare))
                         {
                             continue;
                         }
                         else
                         {
                             writetolnamefile(temp, Long.parseLong(token));
                         }
                         //readfromfile(Long.parseLong(token));
                         //System.out.println("Processed Token:"+token);   
                     }
            file.close();
            lnamevalues();
      }
      catch(FileNotFoundException e){
          e.printStackTrace();
      }     
  }
    @SuppressWarnings({"ConvertToTryWithResources", "UnnecessaryContinue", "CallToPrintStackTrace"})
  public void deletefromstate(long l) throws IOException
  {
      String line="";
      line=""+0;
      @SuppressWarnings("UnusedAssignment")
      String compare="";
      compare=""+l;
      String cvsSplitBy = "\\|";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] s2=null;
      String temp=null;
      @SuppressWarnings("UnusedAssignment")
      String temp2=null;
      try{
            RandomAccessFile file= new RandomAccessFile(dbFile,"rw");
            file.seek(l);
            for(int i=0;i<=7;i++)
            {
            temp=file.readUTF();
            //System.out.println("from deletefromfile"+temp);
            }//System.out.println("from deletefromfile"+temp);
            temp2=""+hm2.get(temp);
            hm2.remove(temp);
            StringTokenizer t=new StringTokenizer(temp2,"|");
                     while(t.hasMoreTokens())
                     {
                         
                         String token=t.nextToken();
                         if(token.equals(compare))
                         {
                             continue;
                         }
                         else
                         {
                             writetostatefile(temp, Long.parseLong(token));
                         }
                         
                         //readfromfile(Long.parseLong(token));
                         //System.out.println("Processed Token:"+token);
                         
                     }
            file.close();
            statevalues();
      }
      catch(FileNotFoundException e){
          e.printStackTrace();
      }     
  }
    @SuppressWarnings({"UnusedAssignment", "UnnecessaryContinue", "CallToPrintStackTrace"})
  public void modify(int i,String fname,String newvalue) throws FileNotFoundException
  {
      @SuppressWarnings("UnusedAssignment")
      String l="";
      String[] sub;
      String temp="";
        sub = null;
      String cvsSplitBy = ",";
      int count=0;
      FileReader fr = new FileReader(indFile);
      BufferedReader br = new BufferedReader(fr);
      try{
          while((l=br.readLine())!=null)
          {
             sub= l.split(cvsSplitBy);
             if(sub[0].equals(newvalue))
                     {
                         System.out.println("A record with the value:"+newvalue+"already exists");
                         break;
                     }
             //System.out.println("offset of id"+sub[0]);
             if(Integer.parseInt(sub[0])==i)
             {
                 //deletefromlastname(Long.parseLong(sub[1]));
                 //deletefromstate(Long.parseLong(sub[1]));
                 modifydbfile(Long.parseLong(sub[1]),fname,newvalue);
                 //hm3.remove(s);
                 count++;
                 break;
             }
             else
             {  
                 continue;
             }
             
          }
          if(count!=1)
          {
              System.out.println("A Record with id:"+i+"does not exists");
          }
          br.close();
          fr.close();
          
      }
      catch (IOException e) {
		e.printStackTrace();
	}  
      
  }
    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
  public void modifydbfile(long l,String fname,String newvalue) throws IOException
  {
      String line="";
      line=""+0;
      String cvsSplitBy = "\\|";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] s2=null;
      String temp="";
      String temp2="";
      String temp3="";
      String temp4="";
      int fieldnamelocation=0;
      try{
            for(int i=0;i<13;i++)
            {
                if(fieldnames[i].equals(fname))
                {
                   fieldnamelocation=i; 
                }
            }
            RandomAccessFile file= new RandomAccessFile(dbFile,"rw");
            file.seek(l);
                        for(int i=0;i<=0;i++)
                        {
                            temp4=""+file.readUTF();
                        }
            file.seek(l);
            for(int i=0;i<=fieldnamelocation;i++)
            {
                temp=""+file.readUTF();
            }
             file.seek(l);
                        for(int i=0;i<=7;i++)
                        {
                            temp3=""+file.readUTF();
                        }
            file.seek(l);
            for(int i=0;i<fieldnamelocation;i++)
            {
                temp2=""+file.readUTF();
            }
            file.writeUTF(newvalue);
            System.out.println("");
            if(fieldnamelocation==0)
            {
                modifyid(l,temp4,newvalue);
            }
            if(fieldnamelocation==2)
            {
                modifylastname(l,temp,newvalue);
            }
            else if(fieldnamelocation==7)
                    {
                       
                        modifystatename(l,temp3,newvalue);
                    }
            //line=file.readLine();
            //System.out.println(""+line);
            //System.out.println(""+file.readUTF());
            file.close();
      }
      catch(FileNotFoundException e){
          e.printStackTrace();
      }     
  }
    @SuppressWarnings("CallToPrintStackTrace")
  public void modifyid(long l,String keyvalue,String newvalue) throws IOException
  {
      String line="";
      line=""+0;
      String compare="";
      compare=""+l;
      String cvsSplitBy = "\\|";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] s2=null;
      String temp=null;
      @SuppressWarnings("UnusedAssignment")
      String temp2=null;
      try{

            temp2=""+hm3.get(keyvalue);
            hm3.remove(keyvalue);
            writetoindexfile(newvalue, Long.parseLong(temp2));
            indexvalues();
      }
                   
      catch(FileNotFoundException e){
          e.printStackTrace();
      }     
  }
  
    @SuppressWarnings("CallToPrintStackTrace")
  public void modifylastname(long l,String keyvalue,String newvalue) throws IOException
  {
      String line="";
      line=""+0;
      @SuppressWarnings("UnusedAssignment")
      String compare="";
      compare=""+l;
      String cvsSplitBy = "\\|";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] s2=null;
      String temp=null;
      @SuppressWarnings("UnusedAssignment")
      String temp2=null;
      try{

            temp2=""+hm.get(keyvalue);
            hm.remove(keyvalue);
            
            //writetolnamefile(newvalue,Long.parseLong(temp2));
            StringTokenizer t=new StringTokenizer(temp2,"|");
                     while(t.hasMoreTokens())
                     {
                         
                         String token=t.nextToken();
                         if(token.equals(compare))
                         {
                             writetolnamefile(newvalue,Long.parseLong(token));
                         }
                         else
                         {
                         writetolnamefile(keyvalue,Long.parseLong(token));
                         }
                         }
                      lnamevalues();
      }
                   
      catch(FileNotFoundException e){
          e.printStackTrace();
      }     
  }
  
    @SuppressWarnings("CallToPrintStackTrace")
  public void modifystatename(long l,String keyvalue,String newvalue) throws IOException
  {
      String line="";
      line=""+0;
      @SuppressWarnings("UnusedAssignment")
      String compare="";
      compare=""+l;
      String cvsSplitBy = "\\|";
      @SuppressWarnings("MismatchedReadAndWriteOfArray")
      String[] s2=null;
      String temp=null;
      @SuppressWarnings("UnusedAssignment")
      String temp2=null;
      try{

            System.out.println("keyvalue value"+keyvalue);
            temp2=""+hm2.get(keyvalue);
            hm2.remove(keyvalue);
            System.out.println("values of the state"+temp2);
            //writetolnamefile(newvalue,Long.parseLong(temp2));
            StringTokenizer t=new StringTokenizer(temp2,"|");
            
                     while(t.hasMoreTokens())
                     {
                         
                         String token=t.nextToken();
                         if(token.equals(compare))
                         {
                            writetostatefile(newvalue,Long.parseLong(token));
                        }
                         else
                         {
                             writetostatefile(keyvalue,Long.parseLong(token));
                         }
                      statevalues();
      }
      }             
      catch(FileNotFoundException e){
          e.printStackTrace();
      }     
  }

    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
  public void run() {
        @SuppressWarnings("UnusedAssignment")
	String line = "";
	String cvsSplitBy = "\\|";
        int size;
        long startofrecord;
        //long endofrecord;
        @SuppressWarnings("UnusedAssignment")
        String[] s = null;
	String s1 = null;
        try {
                RandomAccessFile file = new RandomAccessFile(csvFile, "r");
		RandomAccessFile file1 =new RandomAccessFile(dbFile, "rw");
                file.readLine();
                while ((line = file.readLine()) != null) {
		        // use comma as separator
                        size=line.length();
			startofrecord= file1.getFilePointer();
                        s = line.split(cvsSplitBy);
                        //System.out.println("id"+ s[0] + "fname"+ s[1]);
                        for(int i=0;i<13;i++)
                        {
                            file1.writeUTF(s[i]);
                        }
                        writetoindexfile(s[0],startofrecord);
                        writetolnamefile(s[2],startofrecord);
                        //hashvalues();
                        writetostatefile(s[7],startofrecord);
                        //System.out.println(startofrecord);                    
                }
                endofrecord = file1.getFilePointer();
                //readfromfile(163);
                indexvalues();
                lnamevalues();
                statevalues();

                select(2);
                // Retreives the record values whose id is given as a argument
                // Retreives the records values whose lastname or state is given as an argument,
                //if record is not present prints a message saying that no record exists with the given value            
                System.out.println();
                
                select("AS");     
                
                
                //inserting a record into the files , 
                //if id exists then prints a message saying that a record with id already exists
                insert("500,James,Butt,Benton John B Jr,6649 N Blue Gum St,New Orleans,Orleans,LA,70116,504-621-8927,504-845-1427,jbutt@gmail.com,http://www.bentonjohnbjr.com");   
                
                /*
                given a unique identifier, field_name, and new value of an existing record,
                updates the old value of field_name with new value.
                Modifying a non-existant record identifier fails and a message is printed.
                */
                modify(500,"id","503");
         
                /*deletes a record which has the given id and changes are made in index files
                    if the record is not present a msg is displayed 
                */
                System.out.println();
  
                delete(505);
                
                System.out.println();
                
                select("AK");
                
                //counts the total number of records present
                System.out.println();
                count();
                file.close();
                file1.close();    
	} 
        catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
  } 
    public static void main(String[] args) {
 
	MyDatabase obj = new MyDatabase();
	obj.run();
 
  }
}
