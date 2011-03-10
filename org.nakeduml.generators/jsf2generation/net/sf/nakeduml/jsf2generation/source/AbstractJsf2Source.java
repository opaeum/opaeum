package net.sf.nakeduml.jsf2generation.source;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.el.Expression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIOutput;
import javax.faces.component.UIViewRoot;
import javax.faces.el.MethodBinding;

import net.sf.nakeduml.textmetamodel.TextSource;

import org.jboss.seam.ui.EntityConverter;
import org.jboss.seam.ui.component.html.HtmlDecorate;
import org.jboss.seam.ui.converter.EnumConverter;

@SuppressWarnings("deprecation")
public abstract class AbstractJsf2Source implements TextSource{
	private static final String ATTRIBUTES_THAT_ARE_SET_KEY = UIComponentBase.class.getName() + ".attributesThatAreSet";
	private UIViewRoot uiViewRoot;
	protected StringBuilder stringBuilder;
	private int depthFromRoot;
	private Properties namespaceProperties;
	public AbstractJsf2Source(int depthFromRoot,Properties namespaceProperties){
		this.depthFromRoot = depthFromRoot;
		this.stringBuilder = new StringBuilder();
		this.namespaceProperties = namespaceProperties;
	}
	public AbstractJsf2Source(UIViewRoot uiViewRoot,int depthFromRoot,Properties namespaceProperties){
		super();
		this.uiViewRoot = uiViewRoot;
		this.depthFromRoot = depthFromRoot;
		this.stringBuilder = new StringBuilder();
		this.namespaceProperties = namespaceProperties;
	}
	public char[] toCharArray(){
		startComposition();
		walkJsfTree(uiViewRoot, 0);
		endComposition();
		return stringBuilder.toString().toCharArray();
	}
	protected abstract void startComposition();
	protected abstract void endComposition();
	private void walkJsfTree(UIComponent component,Integer indent){
		Map<String,UIComponent> facets = component.getFacets();
		for(String facetName:facets.keySet()){
			UIComponent facet = facets.get(facetName);
			++indent;
			doBeforeFacet(facet, facetName, indent);
			++indent;
			doBefore(facet, indent);
			walkJsfTree(facet, indent);
			if(facet.getFacetsAndChildren().hasNext()){
				doAfter(facet, indent);
			}
			--indent;
			doAfterFacet(facet, indent);
			--indent;
		}
		for(UIComponent uiComponent:component.getChildren()){
			++indent;
			doBefore(uiComponent, indent);
			walkJsfTree(uiComponent, indent);
			if(uiComponent.getFacetsAndChildren().hasNext()){
				doAfter(uiComponent, indent);
			}
			--indent;
		}
	}
	private void doBeforeFacet(UIComponent facet,String facetName,Integer indent){
		indent(indent);
		stringBuilder.append("<f:facet name=\"");
		stringBuilder.append(facetName);
		stringBuilder.append("\" >\n");
	}
	private void doAfterFacet(UIComponent facet,Integer indent){
		indent(indent);
		stringBuilder.append("</f:facet>\n");
	}
	@SuppressWarnings({ "unchecked" })
	private void doBefore(UIComponent uiComponent,Integer indent){
		indent(indent);
		stringBuilder.append("<");
		stringBuilder.append(resolveTagName(uiComponent));
		List<String> attributes = (List<String>) uiComponent.getAttributes().get(ATTRIBUTES_THAT_ARE_SET_KEY);
		if(attributes != null){
			for(String attributeName:attributes){
				stringBuilder.append(" ");
				stringBuilder.append(attributeName);
				stringBuilder.append("=\"");
				ValueExpression ve = uiComponent.getValueExpression(attributeName);
				if(ve != null){
					stringBuilder.append(ve.getExpressionString());
				}else{
					Object obj = uiComponent.getAttributes().get(attributeName);
					if(obj instanceof Expression){
						stringBuilder.append(((Expression) obj).getExpressionString());
					}else if(obj instanceof MethodBinding){
						stringBuilder.append(((MethodBinding) obj).getExpressionString());
					}else{
						stringBuilder.append(obj);
					}
				}
				stringBuilder.append("\"");
			}
		}
		if(!uiComponent.getFacetsAndChildren().hasNext()){
			stringBuilder.append(" />\n");
		}else{
			stringBuilder.append(">\n");
		}
		// Little hard code for facelets ui:define
		if(uiComponent instanceof HtmlDecorate){
			HtmlDecorate htmlDecorate = (HtmlDecorate) uiComponent;
			if(htmlDecorate.getTemplate().endsWith("layout/edit.xhtml") || htmlDecorate.getTemplate().endsWith("layout/display.xhtml")){
				indent(indent);
				indent(indent);
				stringBuilder.append("<ui:define name=\"label\">\n");
			}
		}
		if(uiComponent.getParent() instanceof HtmlDecorate && uiComponent.getParent().getChildren().indexOf(uiComponent) == 0){
			HtmlDecorate htmlDecorate = (HtmlDecorate) uiComponent.getParent();
			if(htmlDecorate.getTemplate().endsWith("layout/edit.xhtml") || htmlDecorate.getTemplate().endsWith("layout/display.xhtml")){
				indent(indent);
				stringBuilder.append("</ui:define>\n");
			}
		}
	}
	private void indent(Integer indent){
		for(int i = 0;i < indent;i++){
			stringBuilder.append("    ");
		}
	}
	private void doAfter(UIComponent uiComponent,Integer indent){
		indent(indent);
		if(uiComponent instanceof UIOutput){
			UIOutput uiOutput = (UIOutput) uiComponent;
			if(uiOutput.getConverter() != null){
				indent(1);
				if(uiOutput.getConverter() instanceof EntityConverter){
					stringBuilder.append("<s:convertEntity />\n");
				}else if(uiOutput.getConverter() instanceof EnumConverter){
					stringBuilder.append("<s:convertEnum />\n");
				}else{
					throw new RuntimeException("unhandled");
				}
				indent(indent);
			}
		}
		stringBuilder.append("</");
		stringBuilder.append(resolveTagName(uiComponent));
		stringBuilder.append(">\n");
	}
	private String resolveTagName(UIComponent uiComponent){
		StringBuilder stringBuilder = new StringBuilder();
		String property = namespaceProperties.getProperty(uiComponent.getClass().getName());
		if(property == null){
			throw new IllegalStateException("No property defined for " + uiComponent.getClass().getName() + " in namespace.properties");
		}
		stringBuilder.append(property);
		return stringBuilder.toString();
	}
	protected String relativePathToRoot(){
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0;i < depthFromRoot;i++){
			stringBuilder.append("../");
		}
		return stringBuilder.toString();
	}
	public boolean hasContent(){
		return true;
	}
}
