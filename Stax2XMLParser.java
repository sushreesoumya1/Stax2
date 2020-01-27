
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.SequenceInputStream;

import javax.xml.stream.*;
import org.codehaus.stax2.*;
import org.codehaus.stax2.XMLEventReader2;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.evt.XMLEvent2;
public class Stax2XMLParser {

	public static void main(String[] args) {
		/*------------------------------Main Code start-------------------------*/
		  XMLEventReader2 eventReader = null;
		  XMLInputFactory2 factory;
		  XMLStreamReader2 xmlStreamReader = null;
		  int cnt=0;
		  
		  /*-------------O/P ports-----------*/
		   String MsgId="";
		   String CreDtTm="";
		   String Id="";
		   String ElctrncSeqNb="";
		   String LglSeqNb="";
		   String FrDtTm="";
		   String ToDtTm="";
		   String IBan="";
		   String StrtNm="";
		   String TwnNm="";
		   String PstCd="";
		   int Count;
		   /*------------------------*/
		try {
		File file = new File("D:\\Users\\mishsbn\\Bulk_Print_XMLs\\New_XMLs\\newfile_5000.xml");
		
		
        factory =  (XMLInputFactory2) XMLInputFactory2.newInstance();
		eventReader = (XMLEventReader2) factory.createXMLEventReader(new FileReader(file));
        xmlStreamReader =  (XMLStreamReader2) factory.createXMLStreamReader(new FileReader(file));
		
        
        
	    while ( eventReader.hasNext() )
	    {
				XMLEvent2 xmlEvent = (XMLEvent2) eventReader.nextEvent();
				
				if (xmlEvent.isStartElement()) {
					 //StartElement startElement = xmlEvent.asStartElement();
					 if("MsgId".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 //Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 //MsgId = nameDataEvent.getData().trim();
		                }
					 /*if("CreDtTm".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 CreDtTm = nameDataEvent.getData().trim();
		                }
					 if("Id".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 if(!nameDataEvent.isWhiteSpace()) {
	                     Id = nameDataEvent.getData().trim();
	                     }
		                    
		                }
					 if("ElctrncSeqNb".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 cnt++;
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 ElctrncSeqNb = nameDataEvent.getData().trim();
		                }
					 if("LglSeqNb".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 LglSeqNb = nameDataEvent.getData().trim();
		                }
					 if("FrDtTm".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 FrDtTm = nameDataEvent.getData().trim();
	                    
		                    
		                }
					 if("ToDtTm".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 ToDtTm = nameDataEvent.getData().trim();
		                }
					 if("IBan".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 IBan = nameDataEvent.getData().trim();
		                }
					 if("StrtNm".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 StrtNm = nameDataEvent.getData().trim();
		                }
					 if("TwnNm".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 TwnNm = nameDataEvent.getData().trim();
		                }
					 if("PstCd".equalsIgnoreCase(startElement.getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 PstCd = nameDataEvent.getData().trim();
		                }
				}
				if (xmlEvent.isEndElement()) {
					 EndElement endElement = xmlEvent.asEndElement();
					 if("Stmt".equalsIgnoreCase(endElement.getName().getLocalPart())) {
		                     //generateRow();
		                }*/
				}
				
				if (xmlEvent.isEndDocument()) {
					Count= cnt;
					System.out.println(cnt);
				}
				
				
			} 
	}catch (Exception e) {
				e.printStackTrace();
			}
		/*---------------------------------MAIN code END-------------------------------*/
	    }

	}
	
	

