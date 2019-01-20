package ar.edu.ubp.das.mvc.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class ModuleConfigImpl {

	private static ModuleConfigImpl       instance;
	private ServletContext                context;	
	private Document                      configDoc;
	private Map<String, AliasConfig>      alias;
	private Map<String, DatasourceConfig> datasources;
	private Map<String, ForwardConfig>    forwards;
	
	private ModuleConfigImpl(ServletContext context) {
		this.context     = context;
		this.alias       = new HashMap<String, AliasConfig>();
		this.datasources = new HashMap<String, DatasourceConfig>();
		this.forwards    = new HashMap<String, ForwardConfig>();
	}
	
	public static void load(ServletContext context) throws RuntimeException {
		if(ModuleConfigImpl.instance == null) {
			ModuleConfigImpl.instance = new ModuleConfigImpl(context);
			ModuleConfigImpl.instance.loadDatasources();
			ModuleConfigImpl.instance.loadGlobalConfig();
		}	
	}
	
	private void loadDatasources() throws RuntimeException {
		try {
			String      filename = "/WEB-INF/datasources.xml";
			InputStream input    = context.getResourceAsStream(filename);
			if(input == null) {
				throw new RuntimeException("El archivo " + filename + " no existe");
			}

			SchemaFactory          schema   = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");	        
	        DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
	                               factory.setValidating(false);
	                               factory.setNamespaceAware(true);
	                               factory.setSchema(schema.newSchema(new Source[] {new StreamSource(this.context.getResourceAsStream("/WEB-INF/schema/datasources.xsd"))}));
	        DocumentBuilder        builder  = factory.newDocumentBuilder();        
			XPath                  xPath    =  XPathFactory.newInstance().newXPath();
			Document               document = builder.parse(this.context.getResourceAsStream(filename));
			
			NodeList datasources = NodeList.class.cast(xPath.compile("/datasources/datasource").evaluate(document, XPathConstants.NODESET));
			for (int i = 0, len = datasources.getLength(); i < len; i++) {
			    DatasourceConfig datasourceConfig = new DatasourceConfig();
			                     datasourceConfig.setId(String.valueOf(xPath.compile("@id").evaluate(datasources.item(i), XPathConstants.STRING)));
			                     datasourceConfig.setDriver(String.valueOf(xPath.compile("@driver").evaluate(datasources.item(i), XPathConstants.STRING)));
			                     datasourceConfig.setUrl(String.valueOf(xPath.compile("@url").evaluate(datasources.item(i), XPathConstants.STRING)));
			                     datasourceConfig.setUsername(String.valueOf(xPath.compile("@username").evaluate(datasources.item(i), XPathConstants.STRING)));
			                     datasourceConfig.setPassword(String.valueOf(xPath.compile("@password").evaluate(datasources.item(i), XPathConstants.STRING)));
                this.datasources.put(datasourceConfig.getId(), datasourceConfig);		
			}			
		} 
		catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
		    throw new RuntimeException(ex); 
		}	
	}
	
	private void loadGlobalConfig() throws RuntimeException {
		try {
			String      filename = "/WEB-INF/config.xml";
			InputStream input    = context.getResourceAsStream(filename);
			if(input == null) {
				throw new RuntimeException("El archivo " + filename + " no existe");
			}

			SchemaFactory          schema  = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");	        
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	                               factory.setValidating(false);
	                               factory.setNamespaceAware(true);
	                               factory.setSchema(schema.newSchema(new Source[] {new StreamSource(this.context.getResourceAsStream("/WEB-INF/schema/config.xsd"))}));
	        DocumentBuilder        builder = factory.newDocumentBuilder();        
		    XPath                  xPath   =  XPathFactory.newInstance().newXPath();
	        
	        this.configDoc = builder.parse(this.context.getResourceAsStream(filename));
			
			NodeList forwards = NodeList.class.cast(xPath.compile("/config/global-forwards/forward").evaluate(this.configDoc, XPathConstants.NODESET));
			for (int i = 0, len = forwards.getLength(); i < len; i++) {
			    ForwardConfig forwardConfig = new ForwardConfig();
			                  forwardConfig.setName(String.valueOf(xPath.compile("@name").evaluate(forwards.item(i), XPathConstants.STRING)));
			                  forwardConfig.setPath(String.valueOf(xPath.compile("@path").evaluate(forwards.item(i), XPathConstants.STRING)));
			                  forwardConfig.setRedirect(String.valueOf(xPath.compile("@redirect").evaluate(forwards.item(i), XPathConstants.STRING)));
			    this.forwards.put(forwardConfig.getName(), forwardConfig);			
			}	
		} 
		catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | IllegalArgumentException ex) {
		    throw new RuntimeException(ex); 
		}	
	}

	private AliasConfig getAlias(String name) throws RuntimeException {
		try {
			if(this.alias.containsKey(name)) {
				return this.alias.get(name);
			}
			
			AliasConfig aliasConfig = null;		
			if(this.configDoc != null) {
			    XPath xPath  =  XPathFactory.newInstance().newXPath();
				Node   alias = Node.class.cast(xPath.compile("/config/alias-mappings/alias[@name='" + name + "']").evaluate(this.configDoc, XPathConstants.NODE));
				if(alias != null) {
					String aliasName   = String.valueOf(xPath.compile("@name").evaluate(alias, XPathConstants.STRING));
					       aliasConfig = new AliasConfig(aliasName, this.context);
					this.alias.put(aliasName, aliasConfig);
				}	
			}
			return aliasConfig;
		} 
		catch (XPathExpressionException ex) {
		    throw new RuntimeException(ex); 
		}
	}
	
	private DatasourceConfig getDatasource(String id) {
		if(this.datasources.containsKey(id)) {
			return this.datasources.get(id);
		}
		return null;
	}
	
	private ForwardConfig getForward(String name) {
		if(this.forwards.containsKey(name)) {
			return this.forwards.get(name);
		}
		return null;
	}
	
	public static ServletContext getContext() {
		return ModuleConfigImpl.instance.context;
	}
	
	public static String getFwkPackage() {
		return ModuleConfigImpl.instance.context.getInitParameter("fwk.packages");
	}
	
	public static String getSrcPackage() {
		return ModuleConfigImpl.instance.context.getInitParameter("src.packages");
	}
	
	public static String getTplPath() {
		return ModuleConfigImpl.instance.context.getInitParameter("templates.path");
	}
	
	public static AliasConfig getAliasByName(String name) throws RuntimeException {
		return ModuleConfigImpl.instance.getAlias(name);
	}
	
	public static DatasourceConfig getDatasourceById(String id) {
		return ModuleConfigImpl.instance.getDatasource(id);
	}
	
	public static ForwardConfig getForwardByName(String name) {
		return ModuleConfigImpl.instance.getForward(name);
	}
	
	public static Map<String, ForwardConfig> getForwards() {
		return ModuleConfigImpl.instance.forwards;
	}
	
}
