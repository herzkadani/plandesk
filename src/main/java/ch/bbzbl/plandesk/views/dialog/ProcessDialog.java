package ch.bbzbl.plandesk.views.dialog;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.gatanaso.MultiselectComboBox;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;

import ch.bbzbl.plandesk.IApplicationConstants;
import ch.bbzbl.plandesk.bl.desk.BoardLogic;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;

/**
 * superclass, that generalizes all dialogs related with a process
 * @author Dani Herzka
 *
 */
public abstract class ProcessDialog extends Dialog {
	
	private static final long serialVersionUID = 2278115391309839406L;
	
	//Components
	VerticalLayout layDialog;
	Select<String> cmbStatus;
	TextField txtTitle;
	TextArea txtDescription;
	MultiselectComboBox<MitarbeiterDto> cmbUsers;
	Select<String> cmbPriority;
	Div layCustomVal;
	TextField txtEntryTicket;
	Checkbox chkApproved;
	TextField txtExistingFeature;
	HorizontalLayout layButtons;
	Button btnSave;
	Button btnCancel;
	HorizontalLayout layWapperButtonsRight;
	//Members
	ArrayList<MitarbeiterDto> selectedUsers;
	List<String> statusNames;
	
	/**
	 * Constructor
	 * @param boardLogic the controller of the board
	 * @param reloadEvent the event to reload the board
	 */
	public ProcessDialog(BoardLogic boardLogic, ComponentEventListener<ClickEvent<Button>> reloadEvent) {
		setModal(false);
		getElement().getStyle().set("max-height", "90%");
		setWidth("60%");
		
//		wrapper Layout
		layDialog = new VerticalLayout();
		layDialog.setAlignItems(Alignment.STRETCH);
		layDialog.setSpacing(false);
		
//		Status ComboBox
		cmbStatus = new Select<>();
		cmbStatus.setLabel("Status");
		statusNames = new ArrayList<>();
		boardLogic.getBoard().getSpalten().forEach(status->statusNames.add(status.getName()));
		cmbStatus.setItems(statusNames);
		
//		Title TextFied
		txtTitle = new TextField("Titel");

//		Description TextField
		txtDescription = new TextArea("Beschreibung");

//		Assigned Users MultiSelection ComboBox
		cmbUsers = new MultiselectComboBox<>();
		cmbUsers.setLabel("Mitarbeiter");
		cmbUsers.setPlaceholder("Mitarbeiter auswählen...");
		cmbUsers.setItems(boardLogic.getBoard().getMitarbeiter());
		cmbUsers.setItemLabelGenerator(user -> user.getVorname() + " " + user.getNachname());
		
		selectedUsers = new ArrayList<>();

//		Urgency ComboBox
		cmbPriority = new Select<>();
		cmbPriority.setLabel("Priorität");
		List<String> urgencies = new ArrayList<>();
		urgencies.add(IApplicationConstants.LOW);
		urgencies.add(IApplicationConstants.MEDIUM);
		urgencies.add(IApplicationConstants.HIGH);
		urgencies.add(IApplicationConstants.URGENT);
		cmbPriority.setItems(urgencies);
		
// custom value depending on process type
		layCustomVal = new Div();
		
		txtEntryTicket = new TextField("Meldeticket");
		txtEntryTicket.getStyle().set("width", "100%");
		
		chkApproved = new Checkbox();
		chkApproved.setLabel("Genehmigt");
		
		txtExistingFeature = new TextField("Verbesserte Funktion");
		txtExistingFeature.getStyle().set("width", "100%");
		
//		button layout
		layButtons = new HorizontalLayout();
		layButtons.getStyle().set("margin-top", "20px");
		
//		button to save process
		btnSave = new Button("Speichern", new Icon(VaadinIcon.CHECK));
		btnSave.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		
//		button to close dialog
		btnCancel = new Button("Abbrechen", new Icon(VaadinIcon.CLOSE));
		btnCancel.addClickListener(e -> close());
		
//		layout for buttons floating right
		layWapperButtonsRight = new HorizontalLayout();
		layWapperButtonsRight.add(btnSave, btnCancel);
		layWapperButtonsRight.setWidthFull();
		layWapperButtonsRight.setJustifyContentMode(JustifyContentMode.END);
		
	}
	
	/**
	 *open the process
	 */
	public void open() {
		super.open();
	}
	

}
