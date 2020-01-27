package stax2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.xml.stream.events.Characters;

import org.codehaus.stax2.XMLEventReader2;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.evt.XMLEvent2;
public class ParseXML {

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
		File file = new File("E:\\STAX2_XML\\New_XMLs\\newfile_400000.xml");
		
        factory =  (XMLInputFactory2) XMLInputFactory2.newInstance();
		eventReader = (XMLEventReader2) factory.createXMLEventReader(new FileReader(file));
        xmlStreamReader =  (XMLStreamReader2) factory.createXMLStreamReader(new FileReader(file));
		StringBuilder sb1= new StringBuilder();
		StringBuilder sb2= new StringBuilder();
		final String COMMA=",";
		FileWriter writer = new FileWriter("E:\\STAX2_XML\\Output\\ParsedText.txt");
        
        
	    while ( eventReader.hasNext() )
	    {
				XMLEvent2 xmlEvent = (XMLEvent2) eventReader.nextEvent();
				
				if (xmlEvent.isStartElement()) {
					 //StartElement startElement = xmlEvent.asStartElement();
					 if("MsgId".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 MsgId = nameDataEvent.getData().trim();
						 sb1.append(MsgId);
						 sb1.append(COMMA);
						 //System.out.println(MsgId);
		                }
					 if("CreDtTm".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 CreDtTm = nameDataEvent.getData().trim();
						 sb1.append(CreDtTm);
						 sb1.append(COMMA);
		                }
					 if("Id".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 if(!nameDataEvent.isWhiteSpace()) {
	                     Id = nameDataEvent.getData().trim();
	                     sb2.append(Id);
						 sb2.append(COMMA);
	                     }
		                    
		                }
					 if("ElctrncSeqNb".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 cnt++;
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 ElctrncSeqNb = nameDataEvent.getData().trim();
						 sb2.append(ElctrncSeqNb);
						 sb2.append(COMMA);
		                }
					 if("LglSeqNb".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 LglSeqNb = nameDataEvent.getData().trim();
						 sb2.append(LglSeqNb);
						 sb2.append(COMMA);
		                }
					 if("FrDtTm".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 FrDtTm = nameDataEvent.getData().trim();
						 sb2.append(FrDtTm);
						 sb2.append(COMMA);
	                    
		                    
		                }
					 if("ToDtTm".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 ToDtTm = nameDataEvent.getData().trim();
						 sb2.append(ToDtTm);
						 sb2.append(COMMA);
		                }
					 if("IBan".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 IBan = nameDataEvent.getData().trim();
						 sb2.append(IBan);
						 sb2.append(COMMA);
		                }
					 if("StrtNm".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 StrtNm = nameDataEvent.getData().trim();
						 sb2.append(StrtNm);
						 sb2.append(COMMA);
		                }
					
					 if("PstCd".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 PstCd = nameDataEvent.getData().trim();
						 sb2.append(PstCd);
						 sb2.append(COMMA);
		                }
					 if("TwnNm".equalsIgnoreCase(xmlEvent.asStartElement().getName().getLocalPart())) {
						 Characters nameDataEvent = (Characters) eventReader.nextEvent();
						 TwnNm = nameDataEvent.getData().trim();
						 sb2.append(TwnNm);
		                }
				}
				if (xmlEvent.isEndElement()) {
					if("Stmt".equalsIgnoreCase(xmlEvent.asEndElement().getName().getLocalPart())) {
					String Header= MsgId+COMMA+CreDtTm+COMMA;
					writer.write(Header+sb2.toString());
					writer.write("\n");
					sb2.delete(0, sb2.length());
					}
				}
				
				if (xmlEvent.isEndDocument()) {
					writer.close();
				}
				
				
			} 
	}catch (Exception e) {
				e.printStackTrace();
			}
		/*---------------------------------MAIN code END-------------------------------*/
	    }

	}
	
	

