package ch.bbzbl.plandesk.views.dialog;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.component.textfield.TextField;

import ch.bbzbl.plandesk.IApplicationConstants;
import ch.bbzbl.plandesk.bl.desk.BoardLogic;

/**
 * specializes the {@link ProcessDialog} as a dialog to create a new process
 * @author Dani Herzka
 *
 */
public class CreateProcessDialog extends ProcessDialog {

	private static final long serialVersionUID = -2631540219596841104L;

//	Components
	Tabs tabsType;
	/**
	 * Constructor
	 * @param boardLogic the controller of the board
	 * @param reloadEvent the event to reload the board
	 */
	public CreateProcessDialog(BoardLogic boardLogic, ComponentEventListener<ClickEvent<Button>> reloadEvent) {
		super(boardLogic, reloadEvent);
//		Process type tabs
		tabsType = new Tabs(new Tab(IApplicationConstants.BUGFIX), new Tab(IApplicationConstants.NEW_FEATURE),
				new Tab(IApplicationConstants.IMPROVEMENT));
		tabsType.addThemeVariants(TabsVariant.LUMO_CENTERED);
		
		cmbStatus.setValue(boardLogic.getBoard().getSpalten().get(0).getName());
		cmbPriority.setValue(IApplicationConstants.LOW);

		// custom value depending on process type
		layCustomVal.add(txtEntryTicket);

		tabsType.addSelectedChangeListener(event -> {
			layCustomVal.removeAll();
			if (event.getSelectedTab().getLabel().equals(IApplicationConstants.BUGFIX)) {
				layCustomVal.add(txtEntryTicket);
			} else if (event.getSelectedTab().getLabel().equals(IApplicationConstants.NEW_FEATURE)) {
				layCustomVal.add(chkApproved);
			} else if (event.getSelectedTab().getLabel().equals(IApplicationConstants.IMPROVEMENT)) {
				layCustomVal.add(txtExistingFeature);
			}
		});

		btnSave.addClickListener(e -> {
			selectedUsers.addAll(cmbUsers.getSelectedItems());
			if (tabsType.getSelectedTab().getLabel().equals(IApplicationConstants.BUGFIX)) {
				// create bugfix
				boardLogic.createBugFix(txtTitle.getValue(), txtDescription.getValue(), cmbPriority.getValue(),
						selectedUsers,
						boardLogic.getBoard().getSpalten().stream()
								.filter(spalte -> spalte.getName().equals(cmbStatus.getValue())).findAny().get()
								.getID(),
						((TextField) layCustomVal.getChildren().findFirst().get()).getValue());
			} else if (tabsType.getSelectedTab().getLabel().equals(IApplicationConstants.NEW_FEATURE)) {
				// create new feature
				boardLogic.createNewFunction(txtTitle.getValue(), txtDescription.getValue(), cmbPriority.getValue(),
						selectedUsers,
						boardLogic.getBoard().getSpalten().stream()
								.filter(spalte -> spalte.getName().equals(cmbStatus.getValue())).findAny().get()
								.getID(),
						((Checkbox) layCustomVal.getChildren().findFirst().get()).getValue());
			} else if (tabsType.getSelectedTab().getLabel().equals(IApplicationConstants.IMPROVEMENT)) {
				// create improvement
				boardLogic.createVerbesserung(txtTitle.getValue(), txtDescription.getValue(), cmbPriority.getValue(),
						selectedUsers,
						boardLogic.getBoard().getSpalten().stream()
								.filter(spalte -> spalte.getName().equals(cmbStatus.getValue())).findAny().get()
								.getID(),
						((TextField) layCustomVal.getChildren().findFirst().get()).getValue());
			}
		});
		btnSave.addClickListener(event -> close());
		btnSave.addClickListener(reloadEvent);

		layButtons.add(layWapperButtonsRight);

		layDialog.add(tabsType, cmbStatus, txtTitle, txtDescription, cmbUsers, cmbPriority, layCustomVal, layButtons);

		add(layDialog);

	}
}
