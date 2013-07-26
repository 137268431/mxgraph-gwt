package com.mxgraph.gwt.client.util;

import com.google.gwt.core.client.JavaScriptObject;

public final class URL extends JavaScriptObject {
	
	public static native URL createInstance() /*-{
		$wnd.URL = $wnd.webkitURL || $wnd.URL;
		return new $wnd.URL;
	}-*/;
	
	protected URL() {
	}
	
	public static native String createObjectURL(Blob blob) /*-{
		$wnd.URL = $wnd.webkitURL || $wnd.URL;
		return $wnd.URL.createObjectURL(blob);
	}-*/;
}
