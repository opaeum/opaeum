package org.opaeum.eclipse.menu;

import org.eclipse.jface.action.IContributionItem;

public interface ICompoundContributionItem extends IContributionItem{
	IContributionItem[] getContributionItems();

}
