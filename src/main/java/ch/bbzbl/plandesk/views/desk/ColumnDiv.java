package ch.bbzbl.plandesk.views.desk;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * the layout of a column
 * @author Dani Herzka
 *
 */
public class ColumnDiv extends VerticalLayout {
	
	private static final long serialVersionUID = 7454235139230030418L;
	
	private H2 header;
	
	/**
	 * Constructor
	 * @param title the title of the column
	 */
	public ColumnDiv(String title) {
		header = new H2(title);
		header.getStyle().set("margin-top", "14px");	
		header.getStyle().set("margin-left", "10px");	
		add(header);
		
		addClassName("columnDiv");
		setWidth("25%");
		setHeightFull();
	}
	
	

	
}
