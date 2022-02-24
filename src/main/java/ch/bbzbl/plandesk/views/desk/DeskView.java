package ch.bbzbl.plandesk.views.desk;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

@PWA(name = "PlanDesk", shortName = "PlanDesk", enableInstallPrompt = false)
@Theme(themeFolder = "plandesk")
@PageTitle("Desk")
@Route(value = "")
@CssImport("./themes/plandesk/styles.css")
public class DeskView extends VerticalLayout {

	private static final long serialVersionUID = -7532896783327421334L;
	
	//Components
	private HorizontalLayout header;
	private HorizontalLayout addProcessWrapper;
	private HorizontalLayout main;
	private H2 title;
	private Button addProcess;
	
    public DeskView() {
    	header = new HorizontalLayout();
    	header.setWidthFull();
    	
    	title = new H2("PlanDesk");
    	title.getStyle().set("margin-top", "0");
    	
    	addProcess = new Button("Vorgang hinzufügen", new Icon(VaadinIcon.PLUS));
    	addProcessWrapper = new HorizontalLayout();
    	addProcessWrapper.setWidthFull();
    	addProcessWrapper.setJustifyContentMode(JustifyContentMode.END);
    	addProcessWrapper.add(addProcess);
    	
    	
    	header.setWidthFull();
    	
        header.add(title, addProcessWrapper);
        
        List<String> users = new ArrayList<String>();
        users.add("Dani Herzka");
        users.add("Iven Kuder");
        
        ProcessDiv proDiv = new ProcessDiv("Lorem ipsum dolor sit amet", users, IViewConstants.URGENT, IViewConstants.BUGFIX);
        proDiv.addClickListener(e-> event(926));
        
        List<ProcessDiv> procList = new ArrayList<ProcessDiv>();
        procList.add(proDiv);
     
        ColumnDiv colDiv1 = new ColumnDiv("colTitle1", procList);
        ColumnDiv colDiv2 = new ColumnDiv("colTitle2", new ArrayList<ProcessDiv>());
        ColumnDiv colDiv3 = new ColumnDiv("colTitle3", new ArrayList<ProcessDiv>());
        ColumnDiv colDiv4 = new ColumnDiv("colTitle4", new ArrayList<ProcessDiv>());
        
        main = new HorizontalLayout();
        main.setHeight("90%");
        main.setWidthFull();
        main.add(colDiv1, colDiv2, colDiv3, colDiv4);
        
        add(header, main);
        
        setSpacing(true);
        setPadding(true);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.END);
    }

	private void event(int processId) {
		Dialog dialog = new Dialog();
		dialog.setModal(true);
		dialog.setWidth("50%");
		dialog.setHeight("70%");
		
		TextField processTitle = new TextField("Titel");
		TextArea processDescription = new TextArea("Beschreibung");
		
		processTitle.setValue("Lorem ipsum dolor sit amet");
		processDescription.setValue("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et");
		
		dialog.add(processTitle, processDescription);
		dialog.open();
		Notification not = new Notification("process "+Integer.toString(processId)+ " clicked", 3000, Position.MIDDLE);
		not.open();
	}

}
