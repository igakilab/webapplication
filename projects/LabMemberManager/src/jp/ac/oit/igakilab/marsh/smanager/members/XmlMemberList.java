package jp.ac.oit.igakilab.marsh.smanager.members;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import jp.ac.oit.igakilab.marsh.util.DebugLog;

public class XmlMemberList extends MemberList{
	static DebugLog LOGGER = new DebugLog("XmlMemberList");

	static String getChildText(Node node){
		Node ntmp = node.getFirstChild();
		while( ntmp != null ){
			if( ntmp.getNodeType() == Node.TEXT_NODE ){
				return ntmp.getNodeValue();
			}
			ntmp = ntmp.getNextSibling();
		}
		return null;
	}

	static Member toMember(Node member_node){
		Member mem = new Member();
		Node ch = member_node.getFirstChild();
		String valtmp;

		while( ch != null ){
			if( ch.getNodeType() == Node.ELEMENT_NODE ){
				String n_name = ch.getNodeName();
				if( n_name.equals("name") ){
					valtmp = getChildText(ch);
					if( valtmp != null ) mem.setName(valtmp);
				}
				else if( n_name.equals("password") ){
					valtmp = getChildText(ch);
					if( valtmp != null ) mem.setPassword(valtmp);
				}
				else if( n_name.equals("idconv") ){
					valtmp = getChildText(ch);
					if( valtmp != null ) mem.addConvertId(valtmp);
				}
			}
			ch  = ch.getNextSibling();
		}

		return mem;
	}

	static Element toElement(Document doc, Member mem){
		Element mem_elem = doc.createElement("member");

		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(mem.getName()));
		mem_elem.appendChild(name);

		Element passwd = doc.createElement("password");
		passwd.appendChild(doc.createTextNode(mem.getPassword()));
		mem_elem.appendChild(passwd);

		String[] convs = mem.getConvertIds();
		for(int i=0; i<convs.length; i++){
			Element econv = doc.createElement("idconv");
			econv.appendChild(doc.createTextNode(convs[i]));
			mem_elem.appendChild(econv);
		}

		return mem_elem;
	}

	static Document loadXmlDocument(String file_name){
		DocumentBuilderFactory dbf;
		DocumentBuilder db;
		Document doc = null;

		dbf = DocumentBuilderFactory.newInstance();
		try{
			db = dbf.newDocumentBuilder();
			doc = db.parse(new FileInputStream(file_name));
		}catch(Exception e0){
			LOGGER.log("load", e0.getMessage());
		}

		return doc;
	}

	static Document createNewDocument(){
		DocumentBuilderFactory dbf;
		DocumentBuilder db;

		dbf = DocumentBuilderFactory.newInstance();
		try{
			db = dbf.newDocumentBuilder();
		}catch(Exception e0){
			LOGGER.log("create", e0.getMessage());
			return null;
		}

		return db.newDocument();
	}

	static void saveXmlDocument(Document doc, String file_name){
		TransformerFactory tff;
		Transformer tf;
		tff = TransformerFactory.newInstance();
		try{
			tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "Shift-JIS");
			tf.transform(new DOMSource(doc), new StreamResult(file_name));
		}catch(Exception e0){
			LOGGER.log("save", e0.getMessage());
		}
	}


/* ------- */
	public XmlMemberList(){
		super();
	}

	public XmlMemberList(String fn0){
		super();
		loadXmlFile(fn0);
	}

	public void loadXmlFile(String file_name){
		Document doc = loadXmlDocument(file_name);
		if( doc == null ){
			LOGGER.log("loadXmlFile", "LOAD ERROR");
			return;
		}

		Element root = doc.getDocumentElement();
		if( !root.getTagName().equals("members") ){
			LOGGER.log("loadXmlFile", "illigal format error");
			return;
		}

		Node child = root.getFirstChild();
		while( child != null ){
			if(
				child.getNodeType() == Node.ELEMENT_NODE
				&& child.getNodeName().equals("member")
			){
				addMember(toMember(child));
			}
			child = child.getNextSibling();
		}
	}

	public void saveXmlFile(String file_name){
		Document doc = createNewDocument();
		if( doc == null ){
			LOGGER.log("saveXmlFile", "SAVE ERROR");
			return;
		}

		Element root = doc.createElement("members");
		doc.appendChild(root);

		for(int i=0; i<size(); i++){
			Element mtmp = toElement(doc, getMemberByIdx(i));
			root.appendChild(mtmp);
		}

		saveXmlDocument(doc, file_name);
	}
}
