package ch.bbzbl.plandesk.views.desk;

import java.util.List;

import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.avatar.AvatarGroup.AvatarGroupItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@CssImport("./themes/plandesk/styles.css")
public class ProcessDiv extends Div {

	private static final long serialVersionUID = -3132530837757922889L;
	private H3 header;
	private HorizontalLayout bottomWrapper;
	private AvatarGroup avatarGroup;
	private Image type;
	
	public ProcessDiv(String header, List<String> users, String urgency, String type) {
		this.header = new H3(header);
		bottomWrapper = new HorizontalLayout();
		avatarGroup = new AvatarGroup();
		this.type = new Image("icons/"+type+".svg", type);
		
		this.header.getStyle().set("margin-top", "5px");
		
		
		for(String user: users) {
			avatarGroup.add(new AvatarGroupItem(user));
		}
		
		this.type.setWidth("36px");
		
		bottomWrapper.setJustifyContentMode(JustifyContentMode.BETWEEN);
		bottomWrapper.setAlignItems(Alignment.CENTER);
		bottomWrapper.add(avatarGroup, this.type);
		
		add(this.header, bottomWrapper);
		
		
		setWidth("90%");
		addClassName(urgency);
		addClassName("processDiv");
	}
}
