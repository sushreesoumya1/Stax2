import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.time.Instant;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PoCExtractElements {

	public static void main(String[] args) {
	 	/* -------------Main code START------------------ */
		try {
		int cnt=200000;	
		Instant start = Instant.now();
		System.out.println(start);
		File file = new File("D:\\Users\\mishsbn\\Bulk_Print_XMLs\\CAMT053.xml");
		
		//File file = new File("D:\\Users\\mishsbn\\Bulk_Print_XMLs\\CAMT053-Original.xml");
		
		FileWriter filewriter= new FileWriter("D:\\Users\\mishsbn\\Bulk_Print_XMLs\\New_XMLs\\newfile_"+(cnt*2)+".xml");
		String header="<?xml version=\"1.0\" encoding=\"UTF-8\"?>											\r\n" + 
				"<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:camt.053.001.02\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:iso:std:iso:20022:tech:xsd:camt.053.001.01											\r\n" + 
				"camt.053.001.02.xsd\">											\r\n" + 
				"	<BkToCstmrStmt>										\r\n" + 
				"		<GrpHdr>									\r\n" + 
				"			<MsgId>BANKFILEID00001</MsgId>								\r\n" + 
				"			<CreDtTm>2009-10-30T03:30:47+02:00</CreDtTm>								\r\n" + 
				"			<!-- No need for message pagination (at this stage)-->								\r\n" + 
				"		</GrpHdr>	";
		
		String footer="</BkToCstmrStmt>										\r\n" + 
				"</Document>	";
		filewriter.write(header);
		filewriter.write('\n');
        PageHandler pageHandler = new PageHandler();
        int FileSize=0;
        for(int i=0; i<cnt; i++) {
        	pageHandler.childLoop(file,filewriter,FileSize);
        }
        	//pageHandler.childLoop(file,filewriter,FileSize);
        filewriter.write(footer);
        
        	filewriter.close();
        	
        
        	  
            Instant end = Instant.now();
            System.out.println(Duration.between(start, end));
            

        } catch (Exception e) {
        	e.getMessage();
        } 
		/* -------------Main code END------------------ */
	}

}
/* -------------Helper code START------------------ */
class PageHandler extends DefaultHandler {
	FileWriter filewriter;
	StringBuilder sb_stmt;
	int fileSize;
	  
	  StringBuffer Buffer = new StringBuffer();
	 String qName;
		
	 static int i=4;
	 static int ID_count=0;
	// static int ID_check=0;
	 
	 	boolean Stmt_Open= false;
		boolean Stmt_Close= false;
		boolean Stmt_Default=false;
		
		static String displayText[] = new String[5000];
		static int numberLines = 0;
		static String indentation = ""; 
	
		static int stmt_count = 0;
		
		@Override
	    public void startDocument() {
		try {
			sb_stmt = new StringBuilder();
		}catch(Exception e) {}
		}
		public PageHandler() {
		}
		public PageHandler(FileWriter filewriter, int fileSize) {
			this.filewriter = filewriter;
			this.fileSize= fileSize;
		}
		
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	try {
    		this.qName= qName;
    		if(qName== "Stmt") {
    		Stmt_Open=true;
    		Stmt_Close=false;
    		ID_count++;
    		
				//filewriter= new FileWriter("D:\\Users\\mishsbn\\Bulk_Print_XMLs\\New_XMLs\\newfile_"+i+".xml");
    	}
    	
    	if(Stmt_Open) {
    	displayText[numberLines] = indentation;

        indentation += "    ";

        displayText[numberLines] += '<';
        displayText[numberLines] += qName;
    	 
    if (attributes != null) {
        int numberAttributes = attributes.getLength();

        for (int loopIndex = 0; loopIndex < numberAttributes; loopIndex++) {
           displayText[numberLines] += ' ';
           displayText[numberLines] += attributes.getQName(loopIndex);
           displayText[numberLines] += "=\"";
           displayText[numberLines] += attributes.getValue(loopIndex);
           displayText[numberLines] += '"';
           //if(ID_count>ID_check) {
           filewriter.write(PageHandler.displayText[loopIndex].toCharArray());
           //filewriter.write('\n');
           //}
           sb_stmt.append(PageHandler.displayText[loopIndex].toCharArray());
           sb_stmt.append('\n');
        }
        
        
     }
     displayText[numberLines] += '>';
     numberLines++;
     //System.out.println("-----------START-------------numberLines: "+numberLines);
    	
    	}
    	} catch (Exception e) {
			e.printStackTrace();
		}	
    }

     @Override
     public void endElement(String uri, String localName, String qName){
    	 if(qName== "Stmt") {
    		Stmt_Open=false;
    		Stmt_Close=true;
     	}
    	if((Stmt_Open || Stmt_Close) && !Stmt_Default) {
    	if(indentation.length()>4) {
    	 indentation = indentation.substring(0, indentation.length() - 4) ;
         displayText[numberLines] = indentation;
    	}else {
    		System.out.println("*******************************");
    		System.out.println(indentation.length());
    		indentation = indentation.substring(0, 4) ;
            displayText[numberLines] = indentation;
    	}
         displayText[numberLines] += "</";
         displayText[numberLines] += qName;
         displayText[numberLines] += '>';
         numberLines++;
         
         try {
        	 if(qName== "Stmt") {
 			for(int loopIndex = 0; loopIndex < PageHandler.numberLines; loopIndex++) {
 				//if(ID_count>ID_check) {
 	            filewriter.write(PageHandler.displayText[loopIndex].toCharArray());
 	            //filewriter.write('\n');
 	           sb_stmt.append(PageHandler.displayText[loopIndex].toCharArray());
 	          sb_stmt.append('\n');
 	           //System.out.println(PageHandler.displayText[loopIndex].toString());
 			//}
 	         }
 			//filewriter.write('\n');
 			//sb_stmt.append(PageHandler.displayText[loopIndex].toCharArray());
	         //  sb_stmt.append('\n');
 			
 			//i++;
 	        // filewriter.close();
        	 }
         //if(ID_count>ID_check) {
        	 filewriter.write('\n');
       //  }
     	} catch (Exception e) {
 			e.printStackTrace();
 		}
    }
    	 
    	// System.out.println("-----------END-------------numberLines: "+numberLines);
      if(Stmt_Close) {
    		 stmt_count++;
    		 System.out.println("-----------------------------"+numberLines);
    		 numberLines=0;
    		///// System.out.println("------------stmt_count---------------: "+stmt_count);
    		// System.out.println("-----------CLOSE-------------numberLines: "+numberLines);
    	    	 
    		 }
    }
     
     @Override
     public void characters(char[] ch, int start, int length){
    	 String characterData = (new String(ch, start, length)).trim();
    	 if(Stmt_Open || Stmt_Close) {
    		 if(qName== "Id" && characterData.length()>0) {
    			 characterData = characterData+"000"+ID_count;
    		 }
    	 
         
         if(characterData.indexOf("\n") < 0 && characterData.length() > 0) {
            displayText[numberLines] = indentation;
            displayText[numberLines] += characterData;
            numberLines++;
         }}
     }
     
        
     public void childLoop(File input, FileWriter filewriter,int fileSize) {
        // DefaultHandler handler = this;
    	 PageHandler handler= new PageHandler(filewriter,fileSize);
         SAXParserFactory factory = SAXParserFactory.newInstance();
         
         try {
            SAXParser saxParser = factory.newSAXParser();
            //for(int i=0; i<4; i++) {
            saxParser.parse(input, handler);
           // }
         } catch (Exception e) {
        	 e.printStackTrace();
         }
      }
     
     @Override
     public void endDocument() {
    	 try {
    		 //saxParser.parse(input, handler);
			//filewriter.close();
    		 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 //System.out.println(sb_stmt.toString());
    	 System.out.println(numberLines);
      }
}
/* -------------Helper code END------------------ */
