package org.opaeum.uim.model;

import java.util.List;

import org.opaeum.ecore.EObject;
import org.opaeum.uim.IgnoredElement;
import org.opaeum.uim.Labels;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.editor.ActionBar;
import org.opaeum.uim.editor.EditorPage;
import org.opaeum.uim.editor.QueryResultPage;

public class QueryInvokerImpl implements QueryInvoker {
	private ActionBar actionBar;
	private RootUserInteractionConstraint editability;
	private List<IgnoredElement> ignoredElements;
	private Labels labelOverride;
	private String linkedUmlResource;
	private String name;
	private List<PageOrdering> pageOrdering;
	private List<EditorPage> pages;
	private QueryResultPage resultPage;
	private List<UserInterfaceRoot> superUserInterfaces;
	private String umlElementUid;
	private boolean underUserControl;
	private RootUserInteractionConstraint visibility;


	public EObject eContainer() {
		EObject result = null;
		
		return result;
	}
	
	public ActionBar getActionBar() {
		return this.actionBar;
	}
	
	public RootUserInteractionConstraint getEditability() {
		return this.editability;
	}
	
	public List<IgnoredElement> getIgnoredElements() {
		return this.ignoredElements;
	}
	
	public Labels getLabelOverride() {
		return this.labelOverride;
	}
	
	public String getLinkedUmlResource() {
		return this.linkedUmlResource;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<PageOrdering> getPageOrdering() {
		return this.pageOrdering;
	}
	
	public List<EditorPage> getPages() {
		return this.pages;
	}
	
	public QueryResultPage getResultPage() {
		return this.resultPage;
	}
	
	public List<UserInterfaceRoot> getSuperUserInterfaces() {
		return this.superUserInterfaces;
	}
	
	public String getUmlElementUid() {
		return this.umlElementUid;
	}
	
	public boolean getUnderUserControl() {
		return this.underUserControl;
	}
	
	public RootUserInteractionConstraint getVisibility() {
		return this.visibility;
	}
	
	public void isUnderUserControl(boolean underUserControl) {
		this.underUserControl=underUserControl;
	}
	
	public void setActionBar(ActionBar actionBar) {
		this.actionBar=actionBar;
	}
	
	public void setEditability(RootUserInteractionConstraint editability) {
		this.editability=editability;
	}
	
	public void setIgnoredElements(List<IgnoredElement> ignoredElements) {
		this.ignoredElements=ignoredElements;
	}
	
	public void setLabelOverride(Labels labelOverride) {
		this.labelOverride=labelOverride;
	}
	
	public void setLinkedUmlResource(String linkedUmlResource) {
		this.linkedUmlResource=linkedUmlResource;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPageOrdering(List<PageOrdering> pageOrdering) {
		this.pageOrdering=pageOrdering;
	}
	
	public void setPages(List<EditorPage> pages) {
		this.pages=pages;
	}
	
	public void setResultPage(QueryResultPage resultPage) {
		this.resultPage=resultPage;
	}
	
	public void setSuperUserInterfaces(List<UserInterfaceRoot> superUserInterfaces) {
		this.superUserInterfaces=superUserInterfaces;
	}
	
	public void setUmlElementUid(String umlElementUid) {
		this.umlElementUid=umlElementUid;
	}
	
	public void setVisibility(RootUserInteractionConstraint visibility) {
		this.visibility=visibility;
	}

}