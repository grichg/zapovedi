package com.grisoft.zapovedi.client.panel;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.grisoft.zapovedi.client.model.ZapovedShort;

public class PanelZapovediShort extends Composite {

	public PanelZapovediShort() {
		
		DockPanel dockPanel = new DockPanel();
		initWidget(dockPanel);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		dockPanel.add(scrollPanel, DockPanel.CENTER);
		scrollPanel.setSize("100%", "100%");
		
		CellList<ZapovedShort> cellList = new CellList<ZapovedShort>(new AbstractCell<ZapovedShort>(){
			@Override
			public void render(Context context, ZapovedShort value, SafeHtmlBuilder sb) {
				 sb.append(SafeHtmlUtils.fromSafeConstant(value.id + value.name));
			}
		});
		scrollPanel.setWidget(cellList);
		cellList.setSize("100%", "100%");
	}

}
