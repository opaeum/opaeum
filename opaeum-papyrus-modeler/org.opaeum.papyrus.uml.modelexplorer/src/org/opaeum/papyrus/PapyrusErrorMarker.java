package org.opaeum.papyrus;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.validation.internal.modeled.model.validation.SeverityEnum;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.services.decoration.DecorationService;
import org.eclipse.papyrus.infra.services.decoration.util.Decoration;
import org.opaeum.eclipse.OpaeumErrorMarker;
import org.opaeum.metamodel.validation.BrokenElement;
import org.opaeum.metamodel.validation.BrokenRule;

public class PapyrusErrorMarker extends OpaeumErrorMarker{
	private DecorationService decorationService;
	public PapyrusErrorMarker(){
	}
	@Override
	protected Set<BrokenRule> getBrokenRulesToAdd(BrokenElement entry){
		return new HashSet<BrokenRule>(entry.getBrokenRules().values());
	}
	@Override
	protected void calcExistingMarkers(Set<String> brokenUris) throws CoreException{
		// TODO Auto-generated method stub
		super.calcExistingMarkers(brokenUris);
		if(decorationService != null){
			// Might have loaded directory but no models
			for(Decoration decoration:new HashSet<Decoration>(decorationService.getDecorations().values())){
				// if(decoration.getId().startsWith("opaeum")){
				decorationService.removeDecoration(decoration.getId());
				// }
			}
		}
	}
	@Override
	protected void maybeMarkFile(EObject o,BrokenRule brokenRule,String message) throws CoreException{
		IFile file = findUmlFile(o);
		if(file != null){
			decorationService.addDecoration("opaeum" + markerKey(file, o, brokenRule.getRule()), o, SeverityEnum.ERROR_VALUE, message);
			// IMarker[] mrks = file.findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE);
			// if(mrks.length == 0){
			// IMarker marker = file.createMarker(EValidator.MARKER);
			// marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			// marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			// marker.setAttribute(IMarker.MESSAGE, "Opaeum validation failed. Please open file for more details");
			// }
		}
	}
	public void setServiceRegistry(ServicesRegistry servicesRegistry){
		try{
			this.decorationService = servicesRegistry.getService(DecorationService.class);
		}catch(ServiceException e){
			throw new RuntimeException(e);
		}
	}
}
