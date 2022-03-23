package ch.bbzbl.plandesk.views.desk;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
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
	
	//Members
	List<String> users;
	
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
        
        users = new ArrayList<String>();
        users.add("Dani Herzka");
        users.add("Iven Kuder");
        
        ProcessDiv proDiv = new ProcessDiv("Lorem ipsum dolor sit amet", users, IViewConstants.URGENT, IViewConstants.BUGFIX);
        proDiv.addClickListener(e-> openProcess(926));
        
        List<ProcessDiv> procList = new ArrayList<ProcessDiv>();
        procList.add(proDiv);
     
        ColumnDiv colDiv1 = new ColumnDiv("Backlog", procList);
        ColumnDiv colDiv2 = new ColumnDiv("In Progress", new ArrayList<ProcessDiv>());
        ColumnDiv colDiv3 = new ColumnDiv("On Hold", new ArrayList<ProcessDiv>());
        ColumnDiv colDiv4 = new ColumnDiv("Done", new ArrayList<ProcessDiv>());
        
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
    
    
	private void openProcess(int processId) {
		Dialog dialog = new Dialog();
		VerticalLayout dialogLayout = new VerticalLayout();
		
		dialogLayout.setAlignItems(Alignment.STRETCH);
		dialogLayout.setSpacing(false);
		dialog.setModal(true);
		dialog.setWidth("50%");
		dialog.setHeight("70%");
		
		TextField processTitle = new TextField("Titel");
		processTitle.setValue("Lorem ipsum dolor sit amet");
		
		TextArea processDescription = new TextArea("Beschreibung");
		processDescription.setValue("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et");
		
		VerticalLayout userListLayout = new VerticalLayout();
		userListLayout.add(new Paragraph("Mitarbeiter"));
		for(String user : users) {
			HorizontalLayout userLayout = new HorizontalLayout();
			userLayout.setAlignItems(Alignment.CENTER);
			userLayout.getStyle().set("margin-top", "5px");
			userLayout.add(new Checkbox(), new Avatar(user), new Paragraph(user));
			userListLayout.add(userLayout);
		}
		
		
		ComboBox<String> urgency = new ComboBox<>("Dringlichkeit");

		List<String> urgencies = new ArrayList<>();
		
		urgencies.add(IViewConstants.LOW);
		urgencies.add(IViewConstants.MEDIUM);
		urgencies.add(IViewConstants.HIGH);
		urgencies.add(IViewConstants.URGENT);
		
		urgency.setItems(urgencies);
		
		
		//dialog buttons
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		
		Button saveBtn = new Button("Speichern", new Icon(VaadinIcon.CHECK));
//		saveBtn.addClickListener(e -> updateProcess(processId));
		saveBtn.addClickListener(e-> UI.getCurrent().getPage().reload());
		saveBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		
		Button cancelBtn = new Button("Abbrechen", new Icon(VaadinIcon.CLOSE));
		cancelBtn.addClickListener(e-> dialog.close());
		
		Button deleteBtn = new Button("Löschen", new Icon(VaadinIcon.TRASH));
//		deleteBtn.addClickListener(e -> updateProcess(processId));
		
		deleteBtn.addClickListener(e-> {
			//open confirm dialog, ask the user if he really wants to delete this process
			Dialog confirmProcessDeleteDialog = new Dialog();
			
			Button confirmDeleteBtn = new Button("Löschen", new Icon(VaadinIcon.TRASH));
			confirmDeleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
			confirmDeleteBtn.getStyle().set("margin-right", "10px");
			
			//TODO implement delete process on controller
			confirmDeleteBtn.addClickListener(e2-> UI.getCurrent().getPage().reload());
			
			Button cancelDeleteBtn = new Button("Abbrechen", new Icon(VaadinIcon.CLOSE));
			cancelDeleteBtn.addClickListener(e2 -> confirmProcessDeleteDialog.close());
			
			//TODO add process title
			confirmProcessDeleteDialog.add(new Paragraph("lorem ipsum"+" löschen?"), confirmDeleteBtn, cancelDeleteBtn);
			confirmProcessDeleteDialog.open();
		});
		deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
		deleteBtn.setWidth("150px");
		
		HorizontalLayout rightButtonsWrapper = new HorizontalLayout();
		rightButtonsWrapper.add(saveBtn, cancelBtn);
		rightButtonsWrapper.setWidthFull();
		rightButtonsWrapper.setJustifyContentMode(JustifyContentMode.END);
		buttonLayout.add(deleteBtn, rightButtonsWrapper);
		
		
		
		dialogLayout.add(processTitle, processDescription, userListLayout, urgency, buttonLayout);
		
		dialog.add(dialogLayout);
		dialog.open();
		Notification not = new Notification("process "+Integer.toString(processId)+ " clicked", 3000, Position.MIDDLE);
		not.open();
	}
	
	private void createNewProcess() {
		Dialog dialog = new Dialog();
		
	}

	

	

}
