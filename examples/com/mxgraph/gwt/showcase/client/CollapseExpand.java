package com.mxgraph.gwt.showcase.client;

import java.util.Arrays;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.SimplePanel;
import com.mxgraph.gwt.client.handler.mxRubberband;
import com.mxgraph.gwt.client.model.mxCell;
import com.mxgraph.gwt.client.model.mxGraphModel;
import com.mxgraph.gwt.client.model.mxICell;
import com.mxgraph.gwt.client.util.mxLog;
import com.mxgraph.gwt.client.view.mxGraph;
import com.mxgraph.gwt.client.view.mxGraph.IsCellFoldableCallback;

public class CollapseExpand extends AbstractContentWidget
{

	public CollapseExpand(String name, Showcase showcase)
	{
		super(name, showcase);
		description = "";
	}

	@Override
	public void injectContent(SimplePanel panel)
	{
		final mxGraph graph = new mxGraph();
		
		panel.setWidget(graph);
		graph.getElement().getStyle().setBackgroundImage("url('images/grid.gif')");
		graph.getElement().getStyle().setHeight(100, Unit.PCT);
		Examples.loadDefaultStyle(graph);
		
		new mxRubberband(graph);

		mxGraphModel graphModel = graph.getModel();
		graph.setFoldingEnabled(true);
		graphModel.beginUpdate();
		
		mxLog.show();
		
		graph.setIsCellFoldableCallback(new IsCellFoldableCallback()
		{
			@Override
			public boolean invoke(mxICell cell, boolean collapse, IsCellFoldableCallback old)
			{
				boolean foldable = old.invoke(cell, collapse, null);
				mxLog.debug(cell.getValue() + " is " + (foldable ? "" : "not ") + "foldable");
				return foldable;
			}
		});
		
		mxICell cell = null;
		
		try {
			cell = graph.insertVertex(graph.getDefaultParent(), null, "Expanded Container", 20, 20, 150, 150, "shape=swimlane");
			cell = graph.insertVertex((mxCell)cell, null, "Child", 10, 10, 50, 50, "");
			
			cell = graph.insertVertex(graph.getDefaultParent(), null, "Collapsed container", 350, 20, 150, 150, "shape=swimlane");
			graph.foldCells(true, null, Arrays.asList(cell), null);
			cell = graph.insertVertex((mxCell)cell, null, "Child", 10, 10, 50, 50, "");
			

		} finally {
			graphModel.endUpdate();
		}
	}
}
