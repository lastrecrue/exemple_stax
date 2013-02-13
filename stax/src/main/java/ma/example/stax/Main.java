package ma.example.stax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Main {

	private static final String TV = "tv";
	private static final String CHANNEL = "channel"; // id="11239" order="0"
														// slug="1-2-3-tv"
														// license="0">
	private static final String REGION = "region"; // lang="en">de</region>
	private static final String COUNTRY = "country"; // lang="en">de</country>
	private static final String CATEGORY = "category"; // lang="en">OTHER</category>
	private static final String DISPLAY_NAME = "display-name";// lang="de">1-2-3-tv</display-name>
	private static final String LOGO = "logo"; // src="http://epgs.com/imgs/logo/1-2-3-tv_big.png"
												// />
	private static final String ICON = "icon"; // src="http://epgs.com/imgs/logo/1-2-3-tv.png"
												// />
	private static final String XML_FEED = "xml-feed"; // src="http://www.epgs.com/feeds/xml/epg.php?channel=11239&amp;checksum=50db41a33fefc"
														// />

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XMLInputFactory factory = XMLInputFactory.newInstance();
		factory.setProperty("javax.xml.stream.isValidating", Boolean.FALSE);
		if (factory.isPropertySupported("javax.xml.stream.isValidating")) {
			factory.setProperty("javax.xml.stream.isValidating", Boolean.FALSE);
		}
		factory.setXMLReporter(new XMLReporter() {
			public void report(String message, String typeErreur,
					Object source, Location location) throws XMLStreamException {
				System.out.println("Erreur de type : " + typeErreur
						+ ", message : " + message);
			}
		});

		try {
			File file = new File("example.xml");
			Stack<String> flagList = new Stack<String>();
			XMLStreamReader reader = factory
					.createXMLStreamReader(new FileReader(file));
			while (reader.hasNext()) {
				int type = reader.next();

				switch (type) {
				case XMLStreamReader.START_ELEMENT:
					flagList.push(reader.getLocalName());
					break;
				case XMLStreamReader.END_ELEMENT:
					flagList.pop();
					break;
				case XMLStreamReader.PROCESSING_INSTRUCTION:

					break;
				case XMLStreamReader.CHARACTERS:

					break;
				case XMLStreamReader.COMMENT:

					break;
				case XMLStreamReader.SPACE:

					break;
				case XMLStreamReader.START_DOCUMENT:

					break;
				case XMLStreamReader.END_DOCUMENT:

					break;
				case XMLStreamReader.ENTITY_REFERENCE:

					break;
				case XMLStreamReader.DTD:

					break;
				case XMLStreamReader.CDATA:

					break;
				case XMLStreamReader.NAMESPACE:

					break;
				case XMLStreamReader.NOTATION_DECLARATION:

					break;
				case XMLStreamReader.ENTITY_DECLARATION:

					break;

				default:
					break;
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
