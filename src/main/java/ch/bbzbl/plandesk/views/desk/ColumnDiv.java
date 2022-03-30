package ch.bbzbl.plandesk.views.desk;

import java.util.List;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ColumnDiv extends VerticalLayout {
	
	private static final long serialVersionUID = 7454235139230030418L;
	
	private H2 header;
	
	
	public ColumnDiv(String title, List<ProcessDiv> processes) {
		header = new H2(title);
		header.getStyle().set("margin-top", "14px");	
		add(header);
		
		for(ProcessDiv process: processes) {
			add(process);
		}
		
		addClassName("columnDiv");
		setWidth("25%");
		setHeightFull();
	}
	
	

	
}
