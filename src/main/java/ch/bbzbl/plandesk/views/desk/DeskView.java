package ch.bbzbl.plandesk.views.desk;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.vaadin.gatanaso.MultiselectComboBox;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import ch.bbzbl.plandesk.bl.desk.BoardLogic;
import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.SpaltenDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

@PWA(name = "PlanDesk", shortName = "PlanDesk", enableInstallPrompt = false)
@Theme(themeFolder = "plandesk")
@PageTitle("Desk")
@Route(value = "")
@CssImport("./themes/plandesk/styles.css")
public class DeskView extends VerticalLayout {

	private static final long serialVersionUID = -7532896783327421334L;

	// Components
	private VerticalLayout wrapper;
	private HorizontalLayout header;
	private HorizontalLayout addProcessWrapper;
	private HorizontalLayout main;
	private H2 title;
	private Button btnAddProcess;

	// Members
	List<MitarbeiterDto> users;
	private BoardLogic boardLogic;

	/**
	 * Constructor
	 */
	public DeskView() {
		// init the board
		boardLogic = new BoardLogic();
		boardLogic.initBoard();

		wrapper = new VerticalLayout();

		List<MitarbeiterDto> list = new ArrayList<>();
		list.add(new MitarbeiterDto(1, "test", "test"));
		new ProcessDiv("header", list, IViewConstants.URGENT, IViewConstants.BUGFIX);
	}

	@PostConstruct
	private void initUi() {
		if (wrapper.getChildren().findAny().isPresent())
			wrapper.removeAll();
		wrapper.setSizeFull();

		BoardDto board = boardLogic.getBoard();
		users = board.getMitarbeiter();

		header = new HorizontalLayout();
		header.setWidthFull();

		title = new H2(board.getName());
		title.getStyle().set("margin-top", "0");

		btnAddProcess = new Button("Vorgang hinzufügen", new Icon(VaadinIcon.PLUS));
		btnAddProcess.addClickListener(e -> createNewProcess());

		addProcessWrapper = new HorizontalLayout();
		addProcessWrapper.setWidthFull();
		addProcessWrapper.setJustifyContentMode(JustifyContentMode.END);
		addProcessWrapper.add(btnAddProcess);
		header.setWidthFull();

		header.add(title, addProcessWrapper);

		main = new HorizontalLayout();
		main.setHeight("90%");
		main.setWidthFull();

		
		//load data
		for (SpaltenDto spalte : board.getSpalten()) {
			ColumnDiv columnDiv = new ColumnDiv(spalte.getName());
			main.add(columnDiv);
			for (VorgangDto vorgang : spalte.getVorgaenge()) {
				ProcessDiv processDiv = new ProcessDiv(vorgang.getTitel(), vorgang.getMitarbeiter(),
						vorgang.getDringlichkeit(), vorgang.getType());
				processDiv.addClickListener(event -> openProcess(vorgang.getID()));
				columnDiv.add(processDiv);
			}
		}

		wrapper.add(header, main);

		setSpacing(true);
		setPadding(true);
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.END);

		if (getChildren().findAny().isEmpty())
			add(wrapper);
	}

	/**
	 * open an existing process by the process id
	 * 
	 * @param processId the id of the process
	 */
	private void openProcess(int processId) {
		VorgangDto vorgang = boardLogic.getVorgangByID(processId);
		Dialog dialog = new Dialog();
		VerticalLayout dialogLayout = new VerticalLayout();

		dialogLayout.setAlignItems(Alignment.STRETCH);
		dialogLayout.setSpacing(false);
		dialog.setModal(true);
		dialog.setWidth("50%");
		dialog.setHeight("70%");

		ComboBox<String> column = new ComboBox<>("Spalte");
		List<String> columnNames = new ArrayList<>();
		boardLogic.getBoard().getSpalten().forEach(col->columnNames.add(col.getName()));
		column.setItems(columnNames);
		column.setValue(boardLogic.getSpalteByVorgangID(processId).getName());

		TextField processTitle = new TextField("Titel");
		if (vorgang.getTitel() != null)
			processTitle.setValue(vorgang.getTitel());

		TextArea processDescription = new TextArea("Beschreibung");
		if (vorgang.getBeschreibung() != null)
			processDescription.setValue(vorgang.getBeschreibung());

		MultiselectComboBox<MitarbeiterDto> userMultiselect = new MultiselectComboBox<>();
		userMultiselect.setLabel("Mitarbeiter");
		userMultiselect.setPlaceholder("Mitarbeiter auswählen...");
		userMultiselect.setItems(users);
		userMultiselect.select(vorgang.getMitarbeiter());
		userMultiselect.setItemLabelGenerator(user -> user.getVorname() + " " + user.getNachname());

		ComboBox<String> urgency = new ComboBox<>("Dringlichkeit");
		urgency.setItems(IViewConstants.LOW, IViewConstants.MEDIUM, IViewConstants.HIGH, IViewConstants.URGENT);
		urgency.setValue(vorgang.getDringlichkeit());
		
		// custom value
		Div customValDiv = new Div();
		if(vorgang.getType().equals(IViewConstants.BUGFIX)){
			TextField entryTicketTxt = new TextField("Meldeticket");
			entryTicketTxt.setValue(vorgang.getMeldeticket());
			entryTicketTxt.getStyle().set("width", "100%");
			customValDiv.add(entryTicketTxt);
		}else if(vorgang.getType().equals(IViewConstants.NEW_FEATURE)) {
			Checkbox approvedChk = new Checkbox();
			approvedChk.setValue(vorgang.isGenehmigt());
			approvedChk.setLabel("Genehmigt");
			customValDiv.add(approvedChk);
		}else if(vorgang.getType().equals(IViewConstants.IMPROVEMENT)) {
			TextField existingFeatureTxt = new TextField("Verbesserte Funktion");
			existingFeatureTxt.setValue(vorgang.getFunction());
			existingFeatureTxt.getStyle().set("width", "100%");
			customValDiv.add(existingFeatureTxt);
		}
		

		// dialog buttons

		HorizontalLayout buttonLayout = new HorizontalLayout();

		Button saveBtn = new Button("Speichern", new Icon(VaadinIcon.CHECK));
		saveBtn.addClickListener(e -> {
			ArrayList<MitarbeiterDto> selectedUsers = new ArrayList<>();
			selectedUsers.addAll(userMultiselect.getSelectedItems());
			
			if(vorgang.getType().equals(IViewConstants.BUGFIX)) {
				//update bugfix
				boardLogic.updateVorgang(processId,
						new VorgangDto(processId, processTitle.getValue(), processDescription.getValue(), selectedUsers,
								urgency.getValue(), vorgang.getType(), ((TextField)customValDiv.getChildren().findFirst().get()).getValue(), "", false));
			}else if(vorgang.getType().equals(IViewConstants.NEW_FEATURE)){
				//create new feature
				boardLogic.updateVorgang(processId,
				new VorgangDto(processId, processTitle.getValue(), processDescription.getValue(), selectedUsers,
						urgency.getValue(), vorgang.getType(), "", "", ((Checkbox)customValDiv.getChildren().findFirst().get()).getValue()));
			}else if(vorgang.getType().equals(IViewConstants.IMPROVEMENT)){
				//create improvement
				boardLogic.updateVorgang(processId,
						new VorgangDto(processId, processTitle.getValue(), processDescription.getValue(), selectedUsers,
								urgency.getValue(), vorgang.getType(), "", ((TextField)customValDiv.getChildren().findFirst().get()).getValue(), false));
			}
			
			boardLogic.updateSpaltenVorgangArray(processId, boardLogic.getBoard().getSpalten().stream().filter(spalte -> spalte.getName().equals(column.getValue())).findAny().get().getID());
			dialog.close();
		});
		saveBtn.addClickListener(e -> initUi());
		saveBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

		Button cancelBtn = new Button("Abbrechen", new Icon(VaadinIcon.CLOSE));
		cancelBtn.addClickListener(e -> dialog.close());

		Button deleteBtn = new Button("Löschen", new Icon(VaadinIcon.TRASH));

		deleteBtn.addClickListener(e -> {
			// open confirm dialog, ask the user if he really wants to delete this process
			Dialog confirmProcessDeleteDialog = new Dialog();

			Button confirmDeleteBtn = new Button("Löschen", new Icon(VaadinIcon.TRASH));
			confirmDeleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
			confirmDeleteBtn.getStyle().set("margin-right", "10px");

			// delete process
			confirmDeleteBtn.addClickListener(e2 -> {
				boardLogic.deleteVorgangByID(processId);
				dialog.close();
				confirmProcessDeleteDialog.close();
				initUi();
			});
			
			Button cancelDeleteBtn = new Button("Abbrechen", new Icon(VaadinIcon.CLOSE));
			cancelDeleteBtn.addClickListener(e2 -> confirmProcessDeleteDialog.close());

			confirmProcessDeleteDialog.add(new Paragraph(vorgang.getTitel() + " löschen?"), confirmDeleteBtn,
					cancelDeleteBtn);
			confirmProcessDeleteDialog.open();
		});
		
		deleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
		deleteBtn.setWidth("150px");

		HorizontalLayout rightButtonsWrapper = new HorizontalLayout();
		rightButtonsWrapper.add(saveBtn, cancelBtn);
		rightButtonsWrapper.setWidthFull();
		rightButtonsWrapper.setJustifyContentMode(JustifyContentMode.END);
		buttonLayout.add(deleteBtn, rightButtonsWrapper);

		dialogLayout.add(column, processTitle, processDescription, userMultiselect, urgency, customValDiv, buttonLayout);

		dialog.add(dialogLayout);
		dialog.open();
	}

	/**
	 * create a new process
	 */
	private void createNewProcess() {
		Dialog dialog = new Dialog();
		VerticalLayout dialogLayout = new VerticalLayout();

		dialogLayout.setAlignItems(Alignment.STRETCH);
		dialogLayout.setSpacing(false);
		dialog.setModal(true);
		dialog.setWidth("50%");
		dialog.setHeight("70%");

		Tabs typesTabs = new Tabs();
		Tab bugfixTab = new Tab(IViewConstants.BUGFIX);
		Tab newfeatureTab = new Tab(IViewConstants.NEW_FEATURE);
		Tab improvementTab = new Tab(IViewConstants.IMPROVEMENT);
		typesTabs.add(bugfixTab, newfeatureTab, improvementTab);
		typesTabs.addThemeVariants(TabsVariant.LUMO_CENTERED);
		

		ComboBox<String> column = new ComboBox<>("Spalte");
		List<String> columnNames = new ArrayList<>();
		boardLogic.getBoard().getSpalten().forEach(col->columnNames.add(col.getName()));
		column.setItems(columnNames);

		TextField processTitle = new TextField("Titel");

		TextArea processDescription = new TextArea("Beschreibung");

		MultiselectComboBox<MitarbeiterDto> userMultiselect = new MultiselectComboBox<>();
		userMultiselect.setLabel("Mitarbeiter");
		userMultiselect.setPlaceholder("Mitarbeiter auswählen...");
		userMultiselect.setItems(users);
		userMultiselect.setItemLabelGenerator(user -> user.getVorname() + " " + user.getNachname());

		ComboBox<String> urgency = new ComboBox<>("Dringlichkeit");

		List<String> urgencies = new ArrayList<>();

		urgencies.add(IViewConstants.LOW);
		urgencies.add(IViewConstants.MEDIUM);
		urgencies.add(IViewConstants.HIGH);
		urgencies.add(IViewConstants.URGENT);

		urgency.setItems(urgencies);

		// custom value
		Div customValDiv = new Div();
		TextField entryTicketTxtDefault = new TextField("Meldeticket");
		entryTicketTxtDefault.getStyle().set("width", "100%");
		customValDiv.add(entryTicketTxtDefault);
		
typesTabs.addSelectedChangeListener(event->{
			customValDiv.removeAll();
			if(event.getSelectedTab().getLabel().equals(IViewConstants.BUGFIX)) {
				TextField entryTicketTxt = new TextField("Meldeticket");
				entryTicketTxt.getStyle().set("width", "100%");
				customValDiv.add(entryTicketTxt);
			}else if(event.getSelectedTab().getLabel().equals(IViewConstants.NEW_FEATURE)){
				Checkbox approvedChk = new Checkbox();
				approvedChk.setLabel("Genehmigt");
				customValDiv.add(approvedChk);
			}else if(event.getSelectedTab().getLabel().equals(IViewConstants.IMPROVEMENT)){
				TextField existingFeatureTxt = new TextField("Verbesserte Funktion");
				existingFeatureTxt.getStyle().set("width", "100%");
				customValDiv.add(existingFeatureTxt);
			}
		});

		// dialog buttons

		HorizontalLayout buttonLayout = new HorizontalLayout();

		Button saveBtn = new Button("Speichern", new Icon(VaadinIcon.CHECK));
		saveBtn.addClickListener(e -> {
			ArrayList<MitarbeiterDto> selectedUsers = new ArrayList<>();
			selectedUsers.addAll(userMultiselect.getSelectedItems());
			if(typesTabs.getSelectedTab().getLabel().equals(IViewConstants.BUGFIX)) {
				//create bugfix
				boardLogic.createBugFix(processTitle.getValue(), processDescription.getValue(), urgency.getValue(), selectedUsers, boardLogic.getBoard().getSpalten().stream().filter(spalte -> spalte.getName().equals(column.getValue())).findAny().get().getID(), ((TextField)customValDiv.getChildren().findFirst().get()).getValue());
			}else if(typesTabs.getSelectedTab().getLabel().equals(IViewConstants.NEW_FEATURE)){
				//create new feature
				boardLogic.createNewFunction(processTitle.getValue(), processDescription.getValue(), urgency.getValue(), selectedUsers, boardLogic.getBoard().getSpalten().stream().filter(spalte -> spalte.getName().equals(column.getValue())).findAny().get().getID(), ((Checkbox)customValDiv.getChildren().findFirst().get()).getValue());
			}else if(typesTabs.getSelectedTab().getLabel().equals(IViewConstants.IMPROVEMENT)){
				//create improvement
				boardLogic.createVerbesserung(processTitle.getValue(), processDescription.getValue(), urgency.getValue(), selectedUsers, boardLogic.getBoard().getSpalten().stream().filter(spalte -> spalte.getName().equals(column.getValue())).findAny().get().getID(), ((TextField)customValDiv.getChildren().findFirst().get()).getValue());
			}
			
			initUi();
			dialog.close();
		});
		saveBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

		Button cancelBtn = new Button("Abbrechen", new Icon(VaadinIcon.CLOSE));
		cancelBtn.addClickListener(e -> dialog.close());

		HorizontalLayout rightButtonsWrapper = new HorizontalLayout();
		rightButtonsWrapper.add(saveBtn, cancelBtn);
		rightButtonsWrapper.setWidthFull();
		rightButtonsWrapper.setJustifyContentMode(JustifyContentMode.END);
		buttonLayout.add(rightButtonsWrapper);

		dialogLayout.add(typesTabs, column, processTitle, processDescription, userMultiselect, urgency, customValDiv,
				buttonLayout);

		dialog.add(dialogLayout);
		dialog.open();
	}

}
