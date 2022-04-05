package ch.bbzbl.plandesk.views.dialog;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;

import ch.bbzbl.plandesk.IApplicationConstants;
import ch.bbzbl.plandesk.bl.desk.BoardLogic;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

/**
 * specializes the {@link ProcessDialog} as a dialog to display and edit a process
 * @author Dani Herzka
 *
 */
public class EditProcessDialog extends ProcessDialog {

	private static final long serialVersionUID = 1233151033646130071L;
	
//	Components
	Button btnDelete;
	Dialog confirmProcessDeleteDialog;
	Button confirmDeleteBtn;
	Button btnCancelDelete;
//	Members
	VorgangDto process;

	/**
	 * Constructor
	 * @param processId the id of the process to be opened
	 * @param boardLogic the controller of the board
	 * @param reloadEvent the event to reload the board
	 */
	public EditProcessDialog(int processId, BoardLogic boardLogic,
			ComponentEventListener<ClickEvent<Button>> reloadEvent) {
		super(boardLogic, reloadEvent);

		process = boardLogic.getVorgangByID(processId);

		cmbStatus.setValue(boardLogic.getSpalteByVorgangID(processId).getName());

		if (process.getTitel() != null)
			txtTitle.setValue(process.getTitel());

		if (process.getBeschreibung() != null)
			txtDescription.setValue(process.getBeschreibung());

		cmbUsers.select(process.getMitarbeiter());

		cmbPriority.setValue(process.getDringlichkeit());

		// custom value
		if (process.getType().equals(IApplicationConstants.BUGFIX)) {
			txtEntryTicket.setValue(process.getMeldeticket());
			layCustomVal.add(txtEntryTicket);
		} else if (process.getType().equals(IApplicationConstants.NEW_FEATURE)) {
			chkApproved.setValue(process.isGenehmigt());
			layCustomVal.add(chkApproved);
		} else if (process.getType().equals(IApplicationConstants.IMPROVEMENT)) {
			txtExistingFeature.setValue(process.getFunction());
			layCustomVal.add(txtExistingFeature);
		}

		// dialog buttons
		btnSave.addClickListener(e -> {
			selectedUsers.addAll(cmbUsers.getSelectedItems());

			if (process.getType().equals(IApplicationConstants.BUGFIX)) {
				// update bugfix
				boardLogic.updateVorgang(processId,
						new VorgangDto(processId, txtTitle.getValue(), txtDescription.getValue(), selectedUsers,
								cmbPriority.getValue(), process.getType(),
								((TextField) layCustomVal.getChildren().findFirst().get()).getValue(), "", false));
			} else if (process.getType().equals(IApplicationConstants.NEW_FEATURE)) {
				// create new feature
				boardLogic.updateVorgang(processId,
						new VorgangDto(processId, txtTitle.getValue(), txtDescription.getValue(), selectedUsers,
								cmbPriority.getValue(), process.getType(), "", "",
								((Checkbox) layCustomVal.getChildren().findFirst().get()).getValue()));
			} else if (process.getType().equals(IApplicationConstants.IMPROVEMENT)) {
				// create improvement
				boardLogic.updateVorgang(processId,
						new VorgangDto(processId, txtTitle.getValue(), txtDescription.getValue(), selectedUsers,
								cmbPriority.getValue(), process.getType(), "",
								((TextField) layCustomVal.getChildren().findFirst().get()).getValue(), false));
			}

			boardLogic.updateSpaltenVorgangArray(processId, boardLogic.getBoard().getSpalten().stream()
					.filter(spalte -> spalte.getName().equals(cmbStatus.getValue())).findAny().get().getID());
			close();
		});
		btnSave.addClickListener(reloadEvent);

		btnDelete = new Button("Löschen", new Icon(VaadinIcon.TRASH));

		btnDelete.addClickListener(e -> {
			// open confirm dialog, ask the user if he really wants to delete this process
			confirmProcessDeleteDialog = new Dialog();

			confirmDeleteBtn = new Button("Löschen", new Icon(VaadinIcon.TRASH));
			confirmDeleteBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
			confirmDeleteBtn.getStyle().set("margin-right", "10px");

			// delete process
			confirmDeleteBtn.addClickListener(e2 -> {
				boardLogic.deleteVorgangByID(processId);
				close();
				confirmProcessDeleteDialog.close();
			});
			confirmDeleteBtn.addClickListener(reloadEvent);

			btnCancelDelete = new Button("Abbrechen", new Icon(VaadinIcon.CLOSE));
			btnCancelDelete.addClickListener(e2 -> confirmProcessDeleteDialog.close());

			confirmProcessDeleteDialog.add(new Paragraph(process.getTitel() + " löschen?"), confirmDeleteBtn,
					btnCancelDelete);
			confirmProcessDeleteDialog.open();
		});

		btnDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		btnDelete.setWidth("150px");

		layButtons.add(btnDelete, layWapperButtonsRight);

		layDialog.add(cmbStatus, txtTitle, txtDescription, cmbUsers, cmbPriority, layCustomVal, layButtons);

		add(layDialog);
	}

}
