package com.mxgraph.gwt.showcase.client;

import java.util.Arrays;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.mxgraph.gwt.client.io.mxCodec;
import com.mxgraph.gwt.client.model.mxCell;
import com.mxgraph.gwt.client.model.mxGeometry;
import com.mxgraph.gwt.client.model.mxGraphModel;
import com.mxgraph.gwt.client.model.mxICell;
import com.mxgraph.gwt.client.util.Blob;
import com.mxgraph.gwt.client.util.URL;
import com.mxgraph.gwt.client.util.mxUtils;
import com.mxgraph.gwt.client.view.mxGraph;

public class PerformanceDemo extends AbstractContentWidget
{
	

	public PerformanceDemo(String name, Showcase showcase)
	{
		super(name, showcase);
		description = "Test case for panning and zooming peformance";
		
		
	}

	@Override
	public void injectContent(SimplePanel panel)
	{
		final mxGraph graph = new mxGraph();
		
		final HorizontalPanel hp = new HorizontalPanel();
		final Anchor downloadLink = new Anchor("Download graph xml");
		downloadLink.addClickHandler(new ClickHandler() {
			@Override public void onClick(ClickEvent event) {
				mxCodec codec = new mxCodec(mxUtils.createXmlDocument());
				Node doc = codec.encode(graph.getModel());
				((Element) doc).setAttribute("style", "default-style2");
				String data = mxUtils.getXml(doc, null);
				
				Element dlEl = downloadLink.getElement();
				dlEl.setAttribute("download", "graph.xml");

				String oUrl = URL.createObjectURL(Blob.createInstance(data));
				dlEl.setAttribute("href", oUrl);
				dlEl.setAttribute("data-downloadurl", "text/plain:" + "graph.xml" + ":" + oUrl);
			}
		});
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "resources/graph.xml");
		try
		{
			builder.sendRequest(null, new RequestCallback()
			{
				
				@Override
				public void onResponseReceived(Request request, Response response)
				{
					mxGraphModel model = graph.getModel();

					Document document = mxUtils.parseXml(response.getText());
					mxCodec codec = new mxCodec(document);
					Element docElement = document.getDocumentElement();
					
					String style = docElement.getAttribute("style");
					
					Element styleDocElement = mxUtils.load("resources/default-style2.xml").getDocumentElement();
					mxCodec styleCodec = new mxCodec(styleDocElement.getOwnerDocument());
					styleCodec.decode(styleDocElement, graph.getStylesheet());
					
					model.beginUpdate();
					try {
						
						codec.decode(docElement, model);
						
					} finally {
						model.endUpdate();
					}
				}
				
				@Override
				public void onError(Request request, Throwable exception)
				{
					System.out.println();
				}
			});
		}
		catch (RequestException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hp.add(downloadLink);

		FlowPanel fp = new FlowPanel();
		fp.add(hp);
		fp.add(graph);
		panel.setWidget(fp);
		
		Examples.loadDefaultStyle(graph);
		graph.getElement().getStyle().setBackgroundImage("url('images/grid.gif')");
		graph.getElement().getStyle().setHeight(100, Unit.PCT);
		graph.setFoldingEnabled(false);
		graph.setConstrainChildren(false);
		graph.setExtendParents(false);
		graph.setExtendParentsOnAdd(false);

		graph.getPanningHandler().setUseLeftButtonForPanning(true);
		graph.setPanning(true);
		//graph.getContainer().getStyle().setOverflow(Overflow.AUTO);

		/*for (int i = 0; i < 10; i++)
		{
			int x = 20;
			int y = 20;
			y = i * 70;
			for (int j = 0; j < 10; j++)
			{
				x = j * 70;
				createCompositeVertex(graph, x, y);
			}
		}*/

	}

	private mxICell createCompositeVertex(mxGraph graph, int xPos, int yPos)
	{
		mxICell composite3 = new mxCell("Composite #3", new mxGeometry(0, 0, 35, 55), "");
		composite3.setVertex(true);

		mxICell cell = new mxCell("Y", new mxGeometry(0, 0, 20, 20), "rounded;");
		cell.setVertex(true);

		mxICell imp = graph.importCells(Arrays.asList(composite3), xPos, yPos, graph.getDefaultParent()).get(0);
		int x = (int) imp.getGeometry().getX();
		int y = (int) imp.getGeometry().getY();
		int w = (int) imp.getGeometry().getWidth();
		int h = (int) imp.getGeometry().getHeight();

		graph.importCells(Arrays.asList(cell), x - 20, y + 35, imp);
		graph.importCells(Arrays.asList(cell), x - 20, y + h - 55, imp);
		graph.importCells(Arrays.asList(cell), x + w - 0, y + 35, imp);
		graph.importCells(Arrays.asList(cell), x + w - 0, y + h - 55, imp);

		return composite3;
	}
}
