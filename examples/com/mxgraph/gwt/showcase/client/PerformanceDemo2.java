package com.mxgraph.gwt.showcase.client;

import com.google.gwt.user.client.ui.SimplePanel;
import com.mxgraph.gwt.client.model.mxCell;
import com.mxgraph.gwt.client.util.mxLog;
import com.mxgraph.gwt.client.view.mxGraph;

public class PerformanceDemo2 extends AbstractContentWidget
{

	public PerformanceDemo2(String name, Showcase showcase)
	{
		super(name, showcase);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void injectContent(SimplePanel panel)
	{
		final mxGraph graph = new mxGraph();
		
		panel.setWidget(graph);

		graph.getPanningHandler().setUseLeftButtonForPanning(true);
		graph.setPanning(true);
		
		mxLog.show();
		int t0 = mxLog.enter("insert");
		mxCell parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();
		try
		{
			for (int i = 0; i < 15; i++)
			{
				for (int j = 0; j < 20; j++)
				{
					graph.insertVertex(parent, null, j + "/**--++//- -564 654 654465465  654654654 ++/" + i, 100 * i, 40 * j,
						80, 30, "align=left;");							
				}	
			}
		}
		finally
		{
			// Updates the display
			graph.getModel().endUpdate();
		}
		
		mxLog.leave("insert", t0);
	}

	
}
